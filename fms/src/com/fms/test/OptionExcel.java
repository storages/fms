package com.fms.test;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;

import com.fms.utils.ReadExcelUtil;

/**
 * 测试读取Excel
 * 
 * @author guodacai 2014-8-13 下午5:38:53
 * 
 */
public class OptionExcel {

	public static void main(String[] args) {
		readExcel();//读取excel
		writeExcel();//写入excel
	}

	/**
	 * 读取excel内容
	 * 
	 * @throws Exception
	 */
	public static void readExcel() {

		try {
			ReadExcelUtil excelUtil = new ReadExcelUtil();

			JFileChooser jfc = new JFileChooser();

			jfc.showOpenDialog(null);

			File selectFile = jfc.getSelectedFile();

			String[][] result = excelUtil.getData(selectFile, 1);

			int rowLength = result.length;

			for (int i = 0; i < rowLength; i++) {

				for (int j = 0; j < result[i].length; j++) {

					System.out.print(result[i][j] + "\t\t");

				}

				System.out.println();

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
	public static void writeExcel(){
		try{
			
		}catch(Exception e){
			
		}
	}
}
