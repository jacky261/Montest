package com.trans.tct.monkey.tool;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class cvs {

	@SuppressWarnings("unused")
	private static String[] headers;

	public static void main(String[] args) throws Exception {
		StringBuffer sb1 = new StringBuffer();
		
//		readerCsv("C:\\id_en.csv");
		ArrayList<HashMap<String, String>> csvdata = CSVReader_.ReadCSV();
		for(int i = 0; i < csvdata.size(); i++){
			System.out.println("row == " + csvdata.get(i).get("row0") + ":" + csvdata.get(i).get("row1"));
		}
		System.out.println("sb1 == " + sb1.toString());
	}

	/**
	 * read csv files
	 * 
	 * @param csvFilePath
	 * @throws Exception
	 */
	public static void readerCsv(String csvFilePath) throws Exception {

		CsvReader reader = new CsvReader(csvFilePath, ',',
				Charset.forName("GBK"));// shift_jis日语字体,utf-8
		reader.readHeaders();
		headers = reader.getHeaders();
		List<Object[]> list = new ArrayList<Object[]>();
		while (reader.readRecord()) {
			list.add(reader.getValues());
		}
		Object[][] datas = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			datas[i] = list.get(i);
		}


		for (int i = 0; i < datas.length; i++) {
			Object[] data = datas[i]; // 取出一组数据
			for (int j = 0; j < data.length; j++) {
				Object cell = data[j];
				if(cell.equals("zus")){
					System.out.println(data[j + 1]);
				}
			}
			System.out.println("");
		}
	}

	/**
	 * 写入csv
	 * 
	 * @param csvFilePath文件名路径
	 *            +文件名字
	 * @param data数据项
	 */
	public static void writerCsv(String csvFilePath, String[][] data) {

		CsvWriter writer = null;
		try {
			writer = new CsvWriter(csvFilePath, ',', Charset.forName("GBK"));// shift_jis日语字体,utf-8

			for (int i = 0; i < data.length; i++) {
				writer.writeRecord(data[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

}
