package com.fms.test;

import java.io.File;

import javax.swing.JFileChooser;

import com.fms.utils.ExcelUtil;



/**
 * 测试读取Excel
 * @author guodacai 2014-8-13 下午5:38:53
 *
 */
public class ReadExcel {

	public static void main(String[] args) throws Exception {
		
		ExcelUtil excelUtil = new ExcelUtil();
		
		JFileChooser jfc = new JFileChooser();
		
		jfc.showOpenDialog(null);  
		
		File selectFile = jfc.getSelectedFile(); 

		String[][] result = excelUtil.readExcel(selectFile, 1);

		int rowLength = result.length;

		for (int i = 0; i < rowLength; i++) {

			for (int j = 0; j < result[i].length; j++) {

				System.out.print(result[i][j] + "\t\t");

			}

			System.out.println();

		}

	}
}
