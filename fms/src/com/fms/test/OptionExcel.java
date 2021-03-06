package com.fms.test;

import java.awt.HeadlessException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


import com.fms.core.entity.AclUser;
import com.fms.core.entity.Stock;
import com.fms.logic.StockLogic;
import com.fms.utils.ExcelUtil;

/**
 * 测试读取Excel
 * 
 * @author guodacai 2014-8-13 下午5:38:53
 * 
 */
public class OptionExcel {
	StockLogic logic;
	public static void main(String[] args) {
		//readExcel();// 读取excel
		 //new ExcelUtil().createPDF();//生成PDF文档
		
		//准备模拟数据
		List list = new ArrayList();
		AclUser user = new AclUser();
		user.setUserName("张三");
		user.setPassword("123456");
		user.setUserFlag("正常");
		list.add(user);
		AclUser user2 = new AclUser();
		user2.setUserName("李四");
		user2.setPassword("abcdef");
		user2.setUserFlag("正常2");
		list.add(user2);
		
		//导出到excel模板(这个模板是我们提前做好后放在一个指定的目录)
		//这个路径是我在没有启动tomcat时临时测试用的，如果项目中要用到，那我们就要在WEB-INF下建一个专用文件夹
		String path = OptionExcel.class.getResource("/").getPath();
		String mess = exportDataToTemplate(new File(path+"test.xls"),list);
		if(StringUtils.isNotBlank(mess)){
			System.out.println(mess);
		}
	}

	
	/**
	 * 导出数据到系统模板
	 */
	public static String exportDataToTemplate(File excelTemplate,List exportData){
		String mess = "";
		byte[] templateData = ExcelUtil.downloadTemplate(excelTemplate);
		if(templateData==null || templateData.length<=0){
			//System.out.println("模板不存在!");
			mess =  "模板不存在!";
		}
		try {
			ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
			Workbook wb = new HSSFWorkbook(new ByteArrayInputStream(templateData));
			Sheet sheet = wb.getSheet("For Check");
			if (sheet == null) {
				//System.out.println("模板不正确!");
				mess = "模板不正确!";
			}else{
				Row row = null;
				for(int i=0;i<exportData.size();i++){
					 row = sheet.createRow(i + 1);
			        AclUser user = (AclUser) exportData.get(i);
			         ExcelUtil.writeCell(row, user.getUserName(), 0);
			         ExcelUtil.writeCell(row, user.getPassword(), 1);
			         ExcelUtil.writeCell(row, user.getUserFlag(), 2);
				}
				wb.setForceFormulaRecalculation(true);
				 wb.write(outBuffer);
				 FileUtils.writeByteArrayToFile(excelTemplate, outBuffer.toByteArray());
				 Runtime.getRuntime().exec("cmd  /c start " + excelTemplate.getAbsolutePath() + "");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mess;
	}
	
	
	/**
	 * 读取excel内容
	 * 
	 * @throws Exception
	 */
	public static void readExcel() {

		try {
			ExcelUtil excelUtil = new ExcelUtil();

			JFileChooser jfc = new JFileChooser();

			// jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);// 只能选择目录

			int value = jfc.showOpenDialog(null);

			if (value == JFileChooser.APPROVE_OPTION) {

				File selectFile = jfc.getSelectedFile();

				String[][] result = excelUtil.readExcel(selectFile, 1);

				int rowLength = result.length;

				List<Stock> stocks = new ArrayList<Stock>();

				for (int i = 0; i < rowLength; i++) {

					Stock stock = new Stock();

					stock.setCode(result[i][0]);
					stock.setName(result[i][1]);
					stock.setNote(result[i][2]);
					stocks.add(stock);
				}
				for (Stock s : stocks) {
					System.out.println(s.getCode() + " " + s.getName() + " "+ s.getNote());
				}
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 写入excel
	 * 
	 * @throws Exception
	 */
	public static void writeExcel() {

		JFileChooser jfc = new JFileChooser();

		jfc.showSaveDialog(null);

		String path = jfc.getSelectedFile().getAbsolutePath();

		List dataList = new ArrayList();

		for (int i = 0; i < 100; i++) {

			dataList.add(i);
		}

		// 创建Excel文档
		HSSFWorkbook wb = new HSSFWorkbook();
		// sheet 对应一个工作页
		HSSFSheet sheet = wb.createSheet("student表中的数据");
		HSSFRow firstrow = sheet.createRow(0); // 下标为0的行开始
		HSSFCell[] firstcell = new HSSFCell[dataList.size()];
		String[] names = new String[dataList.size()];
		names[0] = "ID";
		names[1] = "学号";
		names[2] = "姓名";
		names[3] = "性别";
		names[4] = "班级";
		for (int j = 0; j < names.length; j++) {
			firstcell[j] = firstrow.createCell((short) j);
			firstcell[j].setCellValue(new HSSFRichTextString(names[j]));
		}
		for (int i = 0; i < dataList.size(); i++) {
			// 创建电子表格的一行
			HSSFRow row = sheet.createRow(i + 1); // 下标为1的行开始
			for (int k = 0; k < dataList.size(); k++) {
				// 在一行内循环
				HSSFCell cell = row.createCell((short) k);
				// 设置表格的编码集，使支持中文
				// // 先判断数据库中的数据类型
				// 将结果集里的值放入电子表格中
				cell.setCellValue(Double
						.parseDouble(dataList.get(k).toString()));
			}
			i++;
		}
		// 创建文件输出流，准备输出电子表格
		try {
			OutputStream out = new FileOutputStream(path);
			wb.write(out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("数据库导出成功");
	}

	/**
	 * 设置样式
	 * 
	 * @param wb
	 * @return
	 */
	private static HSSFCellStyle setStyle(HSSFWorkbook wb, HSSFSheet sheet) {

		HSSFCellStyle style = wb.createCellStyle();

		// 一、设置背景色：

		style.setFillForegroundColor((short) 13);// 设置背景色
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// 二、设置边框:

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

		// 三、设置居中:

		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中

		// 四、设置字体:

		HSSFFont font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 16);// 设置字体大小

		HSSFFont font2 = wb.createFont();
		font2.setFontName("仿宋_GB2312");
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		font2.setFontHeightInPoints((short) 12);

		style.setFont(font);// 选择需要用到的字体格式

		// 五、设置列宽:

		sheet.setColumnWidth(0, 3766); // 第一个参数代表列id(从0开始),第2个参数代表宽度值

		return style;
	}
	
	private void validata(Object[][] data){
		
	/*	List<Stock> stocks = logic.findAllStock(null, -1, -1);*/
		for(int i =0;i<data.length;i++){
			Object obj = data[i][0];//编码
			
		}
	}

	public StockLogic getLogic() {
		return logic;
	}

	public void setLogic(StockLogic logic) {
		this.logic = logic;
	}
}
