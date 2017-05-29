package com.trans.tct.monkey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class Report {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(readTxtFile("C://report.txt"));
	}

	public static String readTxtFile(String filePath) {
		String result = null;
		int i = 0;
		int j = 0;
		try {
			String encoding = "GBK";
			// String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					if (lineTxt.contains("成功")) {
						i++;
					} else if (lineTxt.contains("失败")) {
						j++;
					}
				}
				result = "成功:" + i + " 失败:" + j;
				read.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
