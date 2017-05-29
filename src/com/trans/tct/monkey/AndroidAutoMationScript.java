package com.trans.tct.monkey;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.android.ddmlib.IDevice;
import com.trans.tct.monkey.tool.AndroidDebugBridgeUitlPhone;

public class AndroidAutoMationScript extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private JButton manage;
	public String exeMessage;
	private JTable devicelistTable;
	private NonEditableTableModel selectedDeviceTableModel;
//	private Integer port[];

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AndroidAutoMationScript inst = new AndroidAutoMationScript();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public AndroidAutoMationScript() {
		super();
		C.devices = AndroidDebugBridgeUitlPhone.getAndroidDebugBridge();
		if (C.devices.length <= 0) {
			createMessageDialog(C.DEVICE_LIST_IS_NULL);
			System.exit(0);
		} else {

		}
		initGUI();
	}

	
	


	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle(" Android Monkey Tool");

			createJLable(C.DEVICE_LIST, 12, 107, 94, 20);
			selectedDeviceTableModel = new NonEditableTableModel();
			selectedDeviceTableModel.addColumn(C.STRING_DEVICE_NAME);
			devicelistTable = new JTable();
			devicelistTable.setModel(selectedDeviceTableModel);
			JScrollPane devicesScrollPane = new JScrollPane(devicelistTable);
			devicesScrollPane.setBounds(99, 24, 386, 288);
			getContentPane().add(devicesScrollPane);
			manage = createButton(C.CHOOSE, new chooseListener(), false, 390,
					320, 85, 23);
			pack();
			this.setSize(550, 400);
			new Thread(devicesList).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JButton createButton(String str, ActionListener al,
			boolean isEndable, int x, int y, int width, int height) {
		JButton bt = new JButton();
		getContentPane().add(bt);
		bt.setText(str);
		bt.setBounds(432, 328, 85, 23);
		bt.addActionListener(al);
		bt.setEnabled(isEndable);
		return bt;
	}

	public JLabel createJLable(String str, int x, int y, int width, int height) {
		JLabel label = new JLabel();
		getContentPane().add(label);
		label.setText(str);
		label.setFont(new Font(C.CALIBRI_FONT, 0, 16));
		label.setBounds(5, 24, 94, 20);
		return label;
	}

	private class chooseListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				new Thread(new Runnable() {
					@Override
					public void run() {
						if (devicelistTable.getSelectedRow() == -1) {
							createMessageDialog(C.CHOOSE_TEST_PHONE);
						} else {
							IDevice d = C.devices[devicelistTable
									.getSelectedRow()];
							AndroidAutoMationScriptFrame jf = new AndroidAutoMationScriptFrame(
									d, devicesArray.get(devicelistTable
											.getSelectedRow()));
							jf.setLocationRelativeTo(null);
							jf.setVisible(true);
						}

					}
				}).start();

			} catch (Exception e) {
				createMessageDialog(C.CHOOSE_TEST_PHONE);
			}
		}
	}

	public JRadioButton createJRadioButton(String str, Font f,
			ActionListener al, int x, int y, int width, int height) {
		JRadioButton jrb = new JRadioButton(str);
		jrb.setFont(f);
		jrb.setBounds(x, y, width, height);
		jrb.addActionListener(al);
		return jrb;
	}

	public Font getFont(int style, int size) {
		return new Font("Calibri", style, size);
	}

	private class NonEditableTableModel extends DefaultTableModel {
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

	public void createMessageDialog(String str) {
		Object[] options = { "OK" };
		JOptionPane.showOptionDialog(null, str, C.WARNING,
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
				options, options[0]);
	}

	Vector<String> devicesArray = new Vector<String>();
	Runnable devicesList = new Runnable() {
		@Override
		public void run() {
		//	StringBuffer sb = new StringBuffer();
			for (int i = 0; i < C.devices.length; i++) {
				Vector<String> d1 = new Vector<String>();
				String deviceName = AndroidDebugBridgeUitlPhone
						.getDeviceNameAndVersion(C.devices[i]);
				d1.add(deviceName);
				selectedDeviceTableModel.addRow(d1);
				devicesArray.add(deviceName);
				System.out.println(deviceName);
			}

			manage.setEnabled(true);
		}
	};

}