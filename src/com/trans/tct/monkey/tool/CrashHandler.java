package com.trans.tct.monkey.tool;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrashHandler implements UncaughtExceptionHandler {

	public static CrashHandler hadlers;

	@Override
	public void uncaughtException(Thread arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		if (hadlers != null) {

			try {
				SimpleDateFormat format = new SimpleDateFormat("%YYYY%MM%DD");
				Date curdate = new Date();
				String dateName = format.format(curdate);
				FileOutputStream fileOutputStream = new FileOutputStream(
						"/mnt/sdcard/" + dateName + ".txt", true);
				PrintStream printStream = new PrintStream(fileOutputStream);
				arg1.printStackTrace(printStream);
				printStream.flush();
				printStream.close();
				fileOutputStream.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void init() {
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	public static CrashHandler instance() {
		if (hadlers == null) {
			synchronized (hadlers) {
				hadlers = new CrashHandler();
			}
		}
		return hadlers;
	}

}
