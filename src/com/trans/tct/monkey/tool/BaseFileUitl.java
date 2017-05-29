package com.trans.tct.monkey.tool;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.CollectingOutputReceiver;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.ShellCommandUnresponsiveException;
import com.android.ddmlib.TimeoutException;

public class BaseFileUitl {
	public final static String BASEPATH = "C:\\Android_Monkey";
	public final static String BASECASEPATH = "C:\\Android_Automation\\case\\";
	private final static SimpleDateFormat sdf3 = new SimpleDateFormat(
			"yyyy-MM-dd_hh-mm-ss");

	public static void main(String agrs[]) {
	}

	public static String readLog(ArrayList<File> logFileNames, String value,
			String mailto) {
		StringBuffer res = new StringBuffer();
		res.append(value);
		int failurenumber = 0;
		int total = 0;
		long runTime = 0;
		try {
			String encoding = "GBK";
			for (File f : logFileNames) {
				res.append(f.getName() + "\t");
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(f), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {

					if (lineTxt.contains("---Failure")
							|| lineTxt.contains("ErrorScreenShot")) {// 检查是否有失败的
						if (lineTxt.contains("ErrorScreenShot")) {
							res.append("---Failure---\n");
						}

						if (lineTxt.contains("ErrorScreenShot")) {
							failurenumber++;
						}
						res.append(lineTxt + "\n");
					}
					if (lineTxt.contains("--Success--")) {
						res.append("---Success---\n");
					}
					if (lineTxt.contains("milliseconds")) {
						String timeDiff = lineTxt.substring(
								lineTxt.indexOf(":") + 1,
								lineTxt.lastIndexOf("milliseconds")).trim();
						if (timeDiff.matches("[0-9]+"))
							runTime += Long.parseLong(timeDiff);
					}
				}

				res.append("\n");
				read.close();
				read = null;
				total++;
			}
			res.append("Total:" + total + " Failure:" + failurenumber
					+ " Success: " + (total - failurenumber) + " Runtime: "
					+ runTime / 1000 + "s");

			FileWriter fr = new FileWriter(logFileNames.get(0).getParent()
					+ "\\" + System.currentTimeMillis() + "_Report.txt", true);
			fr.write(res.toString());
			fr.flush();
			fr.close();

			if (mailto.trim().length() > 0) {
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res.toString();

	}

	public static ArrayList<String> getFileNameBySuffix(String suffix,
			String path) {
		ArrayList<String> caseslist = new ArrayList<String>();
		File files = new File(path);
		File fs[] = files.listFiles();
		for (File f : fs) {
			if (f.getName().endsWith(suffix)) {
				caseslist.add(f.getName());
			}
		}
		return caseslist;
	}

	public static String CreateFolder(String folder, boolean isdelete) {
		File saveFile = new File(folder.trim());
		if (!saveFile.exists() && !saveFile.isDirectory()) {
			saveFile.mkdirs();
		} else {
			if (isdelete) {
				deleteFolder(folder);
				saveFile.mkdirs();
			}
		}
		return folder + "\\";
	}

	public static void deleteFolder(String folder) {
		try {
			File saveFile = new File(folder);
			if (saveFile.exists() && saveFile.isDirectory()) {
				File files[] = saveFile.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
				saveFile.delete();
			}
		} catch (Exception e) {

		}
	}

	public static String createCaseToFile(String message, String caseName) {
		// FileWriter fr = null;
		File f = null;
		try {
			CreateFolder(BASECASEPATH, false);
			f = new File(BASECASEPATH + caseName + sdf3.format(new Date())
					+ ".case");
			if (false == f.exists()) {
				f.createNewFile();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return f.getPath();
	}

	public static void writeCaseToFile(String message, String fileName) {
		FileWriter fr = null;
		try {
			fr = new FileWriter(fileName, false);
			fr.write(message);
			fr.flush();
			fr.close();
		} catch (Exception e) {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		}
	}

	public static ArrayList<String> readTxtFile(String filePath) {
		ArrayList<String> res = new ArrayList<String>();
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
					// System.out.println(lineTxt);
					res.add(lineTxt);
				}
				read.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public static String splitException(String filePath) {
		StringBuffer sb = new StringBuffer();
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					if(lineTxt.contains("** No activities found to run")){
						sb.append("Not found PackageName");
						break;
					}
					if (lineTxt.contains("// java.lang")) {
						sb.append(lineTxt + "\n");
					}
					if (lineTxt.contains("// 	at")) {
						sb.append(lineTxt + "\n");
					}
				}
				sb.append("\n----------finish---------\n");
				if ("".equals(sb)) {
					sb.append("暂无内容");
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		if ("".equals(sb.toString())) {
			return "程序没有异常";
		} else {
			return sb.toString();
		}
	}

	public static ArrayList<String> readTxtFile(String filePath, String casefile) {
		ArrayList<String> res = new ArrayList<String>();
		try {
			String encoding = "GBK";
			// String encoding = "UTF-8";
			File file = new File(casefile + "\\" + filePath);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					res.add(lineTxt);
				}
				read.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public static String WriteImageToDisk(BufferedImage image, String savePath) {
		String filepath = savePath + "\\" + System.currentTimeMillis() + ".png";
		try {
			File f = new File(filepath);
			if (!f.exists()) {
				f.createNewFile();
			}
			boolean temp = ImageIO.write(image, "png", new File(filepath));
			if (false == temp) {
				throw new IOException("WRITER_PNG_FAILED");
			} else {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filepath;
	}

	public static File WriteLog(String message, String fileName) {
		FileWriter fr = null;
		fileName = fileName + System.currentTimeMillis() + "_LOG.txt";
		try {
			fr = new FileWriter(fileName, true);
			fr.write(message + "\n");
			fr.flush();
			fr.close();
		} catch (Exception e) {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
		return new File(fileName);
	}

	public static void WriteReport(String message, String path) {
		FileWriter fr = null;
		try {
			File file = new File(path);
			if (file.exists()) {
				file.createNewFile();
			}
			fr = new FileWriter(path, true);
			fr.write(message + "\n");
			fr.flush();
			fr.close();
		} catch (Exception e) {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
	}

	public static String exceShell(IDevice idevice, String command)
			throws TimeoutException, AdbCommandRejectedException,
			ShellCommandUnresponsiveException, IOException {
		CollectingOutputReceiver receiver = new CollectingOutputReceiver();
		idevice.executeShellCommand(command, receiver);
		String value = receiver.getOutput().trim();
		if (value.isEmpty()) {
			return null;
		}
		return value;
	}
}
