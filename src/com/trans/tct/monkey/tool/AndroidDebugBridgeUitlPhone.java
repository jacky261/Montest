/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * 
 * Description : ScreenShotUitl
 * 
 *  Date  		   Author     Operation
 *  -------------------------------------------
 *  2011-06-08     v-miqi     Create
 *  2011-06-08	   v-xingez   Update
 *  
 */
package com.trans.tct.monkey.tool;

import java.io.File;

import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.CollectingOutputReceiver;
import com.android.ddmlib.IDevice;

public class AndroidDebugBridgeUitlPhone {

	public static IDevice[] getAndroidDebugBridge() {
		AndroidDebugBridge bridge = AndroidDebugBridge.getBridge();
		String adbLocation = System.getProperty("adb");
		if (adbLocation != null && adbLocation.length() != 0) {
			adbLocation += File.separator + "adb";
		} else {
			adbLocation = "adb";
		}
		if (null == bridge) {
			AndroidDebugBridge.init(false);
		}
		int count = 0;
		bridge = AndroidDebugBridge.createBridge(adbLocation, true);
		while (null != bridge && bridge.hasInitialDeviceList() == false) {
			try {
				Thread.sleep(100);
				count++;
			} catch (InterruptedException e) {
				// pass
			}
			// let's not wait > 10 sec.
			if (count > 100) {
				System.err.println("Timeout getting device list!");

			}
		}
		IDevice[] devices = bridge.getDevices();
		return devices;
	}

	private final static int INSTALL_TIMEOUT = 2 * 60 * 1000; // 2min

	@SuppressWarnings("deprecation")
	public static String getDeviceNameAndVersion(IDevice device) {
		StringBuffer res = new StringBuffer();
		try {
			CollectingOutputReceiver receiver1 = new CollectingOutputReceiver();
			CollectingOutputReceiver receiver2 = new CollectingOutputReceiver();
			device.executeShellCommand("getprop ro.build.version.release",
					receiver1, INSTALL_TIMEOUT);
			device.executeShellCommand("getprop persist.sys.screen.size",
					receiver2, INSTALL_TIMEOUT);

			System.out.println(receiver1.getOutput().toString());

			res.append(device.getSerialNumber().trim() + "_");
			res.append(receiver1.getOutput().toString().trim() + "_");
			res.append(receiver2.getOutput().toString().trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res.toString();
	}

}
