package com.trans.tct.monkey.tool;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessPrinter {
	public static synchronized String print(Process p, boolean print) {
		StringBuffer res = new StringBuffer();
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		try {
			String lines;
			while ((lines= stdInput.readLine()) != null) {
				res.append(lines + "\n");
			}
			if (print) {
				System.out.println(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				p.destroy();
				stdInput.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return res.toString();
	}

	public static synchronized String exeProcess(String str, boolean b)
			throws Exception {
		Process p = Runtime.getRuntime().exec(str);
		String s = print(p, b);
		p.destroy();
		return s;
	}
}
