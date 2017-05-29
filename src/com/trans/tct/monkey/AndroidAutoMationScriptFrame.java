package com.trans.tct.monkey;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.android.ddmlib.IDevice;
import com.trans.tct.mail.tools.Regex;
import com.trans.tct.mail.tools.SimpleMailSender;
import com.trans.tct.monkey.tool.BaseFileUitl;
import com.trans.tct.monkey.tool.ProcessPrinter;

public class AndroidAutoMationScriptFrame extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField jTextFieldPassword;
	private JTextField jTextFieldInputUserName;
	private JTextField jTextFieldCount;
	private JTextField jTextFieldPackage;
	private JTextField jTextFieldCopyMailAddr;
	private JLabel jLabelPassword;
	private JLabel jLabelUserName;
	private JLabel jLabelCount;
	private JLabel jLabelUserPackageName;
	private JLabel jLabelSendMail;
	private JLabel jLabelMailAddr;
	private JLabel jLabelCopyMailAddr;
	private JLabel jLabelPrompt;
	private JButton jButtonRun;
	private IDevice device;
	private String title;
	@SuppressWarnings("rawtypes")
	private JComboBox jComboBoxYear;
	private String packagename = null;
	String array[];
	String mailarray[] = { "tsghk@126.com" };
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String data = format.format(new Date());

	public AndroidAutoMationScriptFrame(IDevice d, String t) {
		super();
		device = d;
		title = t + "_";
		array = getPackge();
		initGUI();
	}

	private String[] getPackge() {
		/*
		 * check packages from android
		 */

		String cmdStr = "adb shell pm list package";
		ArrayList<String> packages = new ArrayList<>();
		String[] pack = null;
		try {
			Process p = Runtime.getRuntime().exec(cmdStr);
			InputStreamReader ISR = new InputStreamReader(p.getInputStream(),
					"utf-8");
			BufferedReader Br = new BufferedReader(ISR);
			String tmp = null;
			while ((tmp = Br.readLine()) != null) {
				if (!tmp.isEmpty())
					packages.add(tmp.trim().split(":")[1]);

			}
			packages.add("添加包名");

			Br.close();
			ISR.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pack = new String[packages.size()];

		// convert ArrayList to array of Strings
		for (int num = 0; num < packages.size(); num++) {
			pack[num] = packages.get(num);

		}

		return pack;

	}

	public static int createMessageDialogPrompt(String str) {
		Object[] options = { "OK" };
		return JOptionPane.showOptionDialog(null, str, C.WARNING,
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
				options, options[0]);
	}

	public static void main(String[] args) {

	}

	private void initGUI() {
		try {

			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle(title);
			{
				jLabelUserName = new JLabel();
				getContentPane().add(jLabelUserName);
				jLabelUserName.setText("随机事件种子值 : ");
				jLabelUserName.setBounds(22, 41, 120, 17);
			}
			{
				jLabelCount = new JLabel();
				getContentPane().add(jLabelCount);
				jLabelCount.setText("次数 : ");
				jLabelCount.setBounds(22, 160, 120, 17);
			}
			{
				jLabelPassword = new JLabel();
				getContentPane().add(jLabelPassword);
				jLabelPassword.setText("产生事件个数 : ");
				jLabelPassword.setBounds(22, 64, 120, 17);
			}
			{
				jLabelUserPackageName = new JLabel();
				getContentPane().add(jLabelUserPackageName);
				jLabelUserPackageName.setText("选择测试App包名 : ");
				jLabelUserPackageName.setBounds(22, 100, 120, 17);
			}
			{
				jLabelSendMail = new JLabel();
				getContentPane().add(jLabelSendMail);
				jLabelSendMail.setText("发送邮件 : ");
				jLabelSendMail.setBounds(22, 130, 120, 17);
			}
			{
				jTextFieldInputUserName = new JTextField();
				getContentPane().add(jTextFieldInputUserName);
				jTextFieldInputUserName.setText("500");
				jTextFieldInputUserName.setBounds(140, 38, 409, 24);
			}
			{
				jTextFieldPassword = new JTextField();
				getContentPane().add(jTextFieldPassword);
				jTextFieldPassword.setText("10000");
				jTextFieldPassword.setBounds(140, 64, 409, 24);
			}
			{
				jTextFieldCount = new JTextField();
				getContentPane().add(jTextFieldCount);
				jTextFieldCount.setText("1000");
				jTextFieldCount.setBounds(140, 160, 409, 24);
			}
			{
				jTextFieldCopyMailAddr = new JTextField();
				getContentPane().add(jTextFieldCopyMailAddr);
				jTextFieldCopyMailAddr.setBounds(330, 130, 300, 24);
			}
			{
				jLabelPrompt = new JLabel();
				jLabelPrompt.setText("(多个帐号用英文分号分开)");
				getContentPane().add(jLabelPrompt);
				jLabelPrompt.setBounds(630, 130, 300, 24);
			}
			{
				jTextFieldPackage = new JTextField();
				getContentPane().add(jTextFieldPackage);
				jTextFieldPackage.setBounds(360, 100, 200, 24);
				jTextFieldPackage.setVisible(false);
			}
			{
				jLabelMailAddr = new JLabel();
				getContentPane().add(jLabelMailAddr);
				jLabelMailAddr.setText(mailarray[0]);
				jLabelMailAddr.setBounds(140, 130, 200, 24);
			}
			{
				jLabelCopyMailAddr = new JLabel();
				getContentPane().add(jLabelCopyMailAddr);
				jLabelCopyMailAddr.setText("抄送:");
				jLabelCopyMailAddr.setBounds(300, 130, 200, 24);
			}
			{
				jButtonRun = new JButton();
				getContentPane().add(jButtonRun);
				jButtonRun.setText("Start");
				jButtonRun.setBounds(635, 160, 100, 24);
				jButtonRun.addActionListener(new RunListener());
			}

			{
				getContentPane().add(getJComboBoxYear());
			}
			pack();
			this.setSize(816, 554);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox getJComboBoxYear() {
		if (jComboBoxYear == null) {
			ComboBoxModel jComboBoxYearModel = new DefaultComboBoxModel(array);
			jComboBoxYear = new JComboBox();
			jComboBoxYear.setEnabled(true);
			jComboBoxYear.setModel(jComboBoxYearModel);
			jComboBoxYear.addActionListener(new jComboBoxYearListener());
			jComboBoxYear.setBounds(140, 100, 200, 24);
		}
		return jComboBoxYear;
	}

	private class jComboBoxYearListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			packagename = jComboBoxYear.getSelectedItem().toString().trim();
			if (packagename.equals("添加包名")) {
				jTextFieldPackage.setVisible(true);
				packagename = "";
			}
			if (packagename.equals("com.android.mms")) {
				jLabelMailAddr.setText(mailarray[0]);
			} else if (packagename.equals("com.android.laucher3")) {
				jLabelMailAddr.setText(mailarray[0]);
			} else if (packagename.equals("com.android.deskclock")) {
				jLabelMailAddr.setText(mailarray[0]);
			} else if (packagename.equals("com.android.settings")) {
				jLabelMailAddr.setText(mailarray[0]);
			} else if (packagename.equals("com.android.dialer")) {
				jLabelMailAddr.setText(mailarray[0]);
			} else {
				jLabelMailAddr.setText("无");
			}

			System.out.println(jLabelMailAddr.getText().trim());

		}

	}

	String username = null;
	String password = null;
	String setTime = null;// 用户指定的 运行时间
	String count = null;
	String mailaddr = null;
	String copymailaddr = null;

	private class RunListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				username = jTextFieldInputUserName.getText().trim();
				password = jTextFieldPassword.getText().trim();
				count = jTextFieldCount.getText().trim();
				mailaddr = jLabelMailAddr.getText().trim();
				copymailaddr = jTextFieldCopyMailAddr.getText().trim();

				new Thread(new Runnable() {
					@Override
					public void run() {
						if (copymailaddr.length() > 0) {
							String[] mailaddr = copymailaddr.split(";");
							for (String m : mailaddr) {
								if (!Regex.regexMail(m)) {
									createMessageDialogPrompt("Wrong email address, please check!");
									break;
								}
							}
							init();
						} else {
							init();
						}

					}
				}).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private boolean init() {
			try {
				System.out.println(device);
				if (packagename == null) {
					packagename = "No  packages";
				}
				if ("".equals(packagename)) {
					packagename = jTextFieldPackage.getText().toString().trim();
				}

				String logPath = BaseFileUitl.BASEPATH + "\\log\\" + title
						+ "_" + data + "\\" + packagename + "-"
						+ System.currentTimeMillis();

				BaseFileUitl.CreateFolder(logPath, false);

				java.awt.Desktop.getDesktop().open(new File(logPath));
				String reportPath = logPath + "//report_" + packagename + "_"
						+ data + ".txt";
				int n = Integer.parseInt(count);
				System.out.println("n == " + n);
				for (int i = 0; i < n; i++) {
					System.out.println("----------run " + (i + 1)
							+ "----------");
					String cmd = "cmd /c adb -s "
							+ device
							+ " shell monkey -p "
							+ packagename
							+ " -s "
							+ username
							+ " --pct-touch 95  --pct-anyevent 5 --ignore-crashes --ignore-timeouts --monitor-native-crashes -v -v "
							+ password + " > " + logPath + "/monkey_log" + i
							+ ".txt";

					ProcessPrinter.exeProcess(cmd, false);

					Thread.sleep(3000);
					String result = BaseFileUitl.splitException(
							logPath + "//monkey_log" + i + ".txt").toString();
					BaseFileUitl.WriteReport(result, reportPath);
					Thread.sleep(3000);
					if (result.contains("Not found PackageName")) {
						System.out.println("Not found PackageName");
						break;
					}
				}
				if (mailaddr.equals("无")) {
					if (copymailaddr.equals("")) {

					} else {
						String[] str = copymailaddr.split(";");
						String[] mailtos = new String[str.length];
						for (int i = 0; i < str.length; i++) {
							mailtos[i] = str[i];
						}
						SimpleMailSender.sendMail(reportPath, mailtos);
					}
				} else {
					if (copymailaddr.equals("")) {
						String[] mailtos = { mailaddr };
						SimpleMailSender.sendMail(reportPath, mailtos);
					} else {
						String[] str = copymailaddr.split(";");
						String[] mailtos = new String[str.length + 1];
						for (int i = 0; i < str.length; i++) {
							mailtos[i] = str[i];
						}
						mailtos[str.length] = mailaddr;
						SimpleMailSender.sendMail(reportPath, mailtos);
					}
				}

				System.out.println("finish");
				jButtonRun.setEnabled(true);
				// }

			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
	}

}