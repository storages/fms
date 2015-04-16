package com.fms.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 读取Excel
 * 
 * @author guodacai 2014-8-13 下午5:41:43
 */
public class ExcelUtil {

	/**
	 * @param 居然不支持office2007
	 *            ?蛋疼
	 * 
	 * @param 读取Excel的内容
	 *            ，第一维数组存储的是一行中列的值，二维数组存储的是多少个行
	 * 
	 * @param file
	 *            读取数据的源Excel
	 * 
	 * @param ignoreRows
	 *            读取数据忽略的行数，比如第一行是标题就忽略;为0表示读取第一行标题
	 * 
	 * @return 读出的Excel中数据的内容
	 * 
	 * @throws FileNotFoundException
	 * 
	 * @throws IOException
	 */

	public static String[][] readExcel(File file, int ignoreRows)

	throws FileNotFoundException, IOException {

		List<String[]> result = new ArrayList<String[]>();

		int rowSize = 0;

		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

		// 打开HSSFWorkbook

		POIFSFileSystem fs = new POIFSFileSystem(in);

		HSSFWorkbook wb = new HSSFWorkbook(fs);

		HSSFCell cell = null;

		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {

			HSSFSheet st = wb.getSheetAt(sheetIndex);

			// 如果ignoreRows为1表示第一行为标题，不读取，跳过

			for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {

				HSSFRow row = st.getRow(rowIndex);

				if (row == null) {

					continue;

				}

				int tempRowSize = row.getLastCellNum() + 1;

				if (tempRowSize > rowSize) {

					rowSize = tempRowSize;

				}

				String[] values = new String[rowSize];

				Arrays.fill(values, "");

				boolean hasValue = false;

				for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {

					String value = "";

					cell = row.getCell(columnIndex);

					if (cell != null) {

						// 注意：一定要设成这个，否则可能会出现乱码

						// cell.setEncoding(HSSFCell.ENCODING_UTF_16);

						switch (cell.getCellType()) {

						case HSSFCell.CELL_TYPE_STRING:

							value = cell.getStringCellValue();

							break;

						case HSSFCell.CELL_TYPE_NUMERIC:

							if (HSSFDateUtil.isCellDateFormatted(cell)) {

								Date date = cell.getDateCellValue();

								if (date != null) {

									value = new SimpleDateFormat("yyyy-MM-dd")

									.format(date);

								} else {

									value = "";

								}

							} else {
								value = cell.getNumericCellValue() + "";
								if (value != null && !"".equals(value)) {
									if (value.length() > 6) {
										value = new DecimalFormat("0").format(cell.getNumericCellValue());
									}
								}

							}

							break;

						case HSSFCell.CELL_TYPE_FORMULA:

							// 导入时如果为公式生成的数据则无值

							if (!cell.getStringCellValue().equals("")) {

								value = cell.getStringCellValue();

							} else {

								value = cell.getNumericCellValue() + "";

							}

							break;

						case HSSFCell.CELL_TYPE_BLANK:

							break;

						case HSSFCell.CELL_TYPE_ERROR:

							value = "";

							break;

						case HSSFCell.CELL_TYPE_BOOLEAN:

							value = (cell.getBooleanCellValue() == true ? "Y" : "N");

							break;

						default:

							value = "";

						}

					}
					HSSFCell cell1 = row.getCell(0);
					HSSFCell cell2 = row.getCell(1);
					if (columnIndex == 0 && value.trim().equals("") && null == cell1 && null == cell2) {

						break;

					}

					values[columnIndex] = rightTrim(value);

					hasValue = true;

				}

				if (hasValue) {

					result.add(values);

				}

			}

		}

		in.close();

		String[][] returnArray = new String[result.size()][rowSize];

		for (int i = 0; i < returnArray.length; i++) {

			returnArray[i] = (String[]) result.get(i);

		}

		return returnArray;

	}

	/**
	 * 
	 * 去掉字符串右边的空格
	 * 
	 * @param str
	 *            要处理的字符串
	 * 
	 * @return 处理后的字符串
	 */

	public static String rightTrim(String str) {

		if (str == null) {

			return "";

		}

		int length = str.length();

		for (int i = length - 1; i >= 0; i--) {

			if (str.charAt(i) != 0x20) {

				break;

			}

			length--;

		}

		return str.substring(0, length);

	}

	/**
	 * 导入到PDF，还没有找到不显示中文的方法
	 */
	public static void createPDF() {
		Document document = new Document(PageSize.A4, 10, 10, 10, 10);
		try {
			PdfWriter.getInstance(document, new FileOutputStream("c://123.pdf"));
			document.open();
			// BaseFont bfChinese =
			// BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);//设置中文字体
			Paragraph title1 = new Paragraph("asc", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new CMYKColor(0, 0, 0, 255)));
			title1.setAlignment(Element.ALIGN_CENTER);
			document.add(title1);

			PdfPTable t = new PdfPTable(3);
			t.setSpacingBefore(25);

			t.setSpacingAfter(25);

			PdfPCell c1 = new PdfPCell(new Phrase("Header1"));

			t.addCell(c1);

			PdfPCell c2 = new PdfPCell(new Phrase("Header2"));

			t.addCell(c2);

			PdfPCell c3 = new PdfPCell(new Phrase("Header3"));

			t.addCell(c3);

			t.addCell("1.1");

			t.addCell("1.2");

			t.addCell("1.3");
			document.add(t);
			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	/**
     * 写单元格
     *
     * @param row
     * @param value
     * @param index
     */
    public static void writeCell(Row row, Object value, int index) {
        Workbook workbook = row.getSheet().getWorkbook();
        Cell cell = row.createCell(index);
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        if (value != null) {
            if (value instanceof Date) {
                DataFormat format = workbook.createDataFormat();
                style.setDataFormat(format.getFormat("yyyy-MM-dd"));
                cell.setCellValue((Date) value);
            } else if (value instanceof Calendar) {
                cell.setCellValue((Calendar) value);
                DataFormat format = workbook.createDataFormat();
                style.setDataFormat(format.getFormat("yyyy-MM-dd"));
            } else if (value instanceof Double) {
                cell.setCellValue((Double) value);
            } else if (value instanceof Short) {
                cell.setCellValue((Short) value);
            } else if (value instanceof Integer) {
                cell.setCellValue((Integer) value);
            } else if (value instanceof Float) {
                cell.setCellValue((Float) value);
            } else if (value instanceof String) {
                cell.setCellValue((String) value);
            } else if (value instanceof StringBuilder) {
                cell.setCellValue(value.toString());
            } else if (value instanceof BigDecimal) {
                cell.setCellValue(((BigDecimal) value).doubleValue());
            }
        }
        cell.setCellStyle(style);
    }
	
	
	/**
	 * 下载文件模板
	 * @param file
	 * @return
	 */
	public static byte[] downloadTemplate(File file){
		try{
			if(null!=file && !file.exists()){
				return null;
			}
			return FileUtils.readFileToByteArray(file);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
