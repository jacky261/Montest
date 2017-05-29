package com.trans.tct.monkey;

import java.text.SimpleDateFormat;
import javax.swing.border.TitledBorder;
import com.android.ddmlib.IDevice;

public class C {
	public static int Tag_version=0;
	/*****************************AndroidDebugBridgeUitlPhone***********************************/
	public final static String TITLE="Android Automation Script Play Tool";
	public final static String DEVICE_LIST_IS_NULL="Device List Is Null!";
	public final static String TESTDRIVER_PHONE="com_microsoft_lync_automation_android_testdriver_phone";
	public final static String START_TESTDRIVER="Start "+TESTDRIVER_PHONE;
	public final static String STRING_VERSION="version:10/05/2016/00001";
	public final static String STRING_LYNCVERSION="Lync Version:";
	public final static String STRING_VERSION_LYNC2010="Lync 2010";
	public final static String STRING_VERSION_lync15="Lync 2013";
	public final static String DEVICE_LIST="DEVICE LIST:";
	public final static String STRING_DEVICE_NAME="Device Name";
	public final static String CHOOSE="Choose";
	public final static String CALIBRI_FONT="Calibri";
	public static String SAVE_PATH = null;
	public static int ScreenShotCount=0;
	public static String[] getCaseId={
		"410488","399822","399823","399824","399825","399826","399827","399828","399829","399830","399831","399832","399833","399836","414117"
	};
	public static String[] getCaseMethodName={
		"testCaseId_410488_P2_MyPresence",
		"testCaseId_399822_P2_MyPresence",
		"testCaseId_399823_P2_MyPresence",
		"testCaseId_399824_P2_MyPresence",
		"testCaseId_399825_P2_MyPresence",
		"testCaseId_399826_P2_MyPresence",
		"testCaseId_399827_P2_MyPresence",
		"testCaseId_399828_P2_MyPresence",
		"testCaseId_399829_P2_MyPresence",
		"testCaseId_399830_P2_MyPresence",
		"testCaseId_399831_P2_MyPresence",
		"testCaseId_399832_P2_MyPresence",
		"testCaseId_399833_P2_MyPresence",
		"testCaseId_399836_P2_MyPresence",
		"testCaseId_414117_P2_MyStatusOptions"
	};
	//Symbol
	public final static String UNDERLINE="_";
	public final static String SPLIST_DEVICE="__";
	public final static String WELL_NO="#";
	public final static String WELL_NO_TWO="##";
	public final static String WELL_NO_THREE="###";
	public final static String BLANK=" ";
	public final static String POINT=".";
	public final static String SEMICOLON=";";
	public final static String COLON=":";
	public final static String COLON_BLANK=" : ";
	public final static String SLASH_SINGLE="/";
	public final static String SLASH_DOUBLE="//";
	public final static String BACKSLASH_DOUBLE="\\";
	public final static String START_THREE="***";
	public final static String NEW_LINE="\n";
	public final static String ENTER_NEW_LINE="\r\n";

	public final static String CHOOSE_TEST_PHONE="Please Choose Test Phone!";
	public final static String OK="OK";
	public final static String CANCEL="Cancel";
	public final static String WARNING="Warning";
	public final static String EMULATOR="emulator";
	public final static String INITIALIZATION_PHONE_VALUE_FAILUER="Initialization phone value failuer!";
	public static IDevice[] devices;
	
	public final static String BASEPATH = "C:\\AndroidAutoMationLogFile_";
	/*****************************RunConfigOptionsUIPhone***********************************/
	public final static String Device_Name1="Device Name : ";
	public static int TITLE_JUSTIFICATION=TitledBorder.DEFAULT_JUSTIFICATION;
	public static int TITLE_POSITION=TitledBorder.DEFAULT_POSITION;
	public final static String CONFIGURATION="Configuration";
	public final static String SELECT_LANGUAGE="Select Language";
	public final static String[] ALL_LANGUAGES = {"en_US","nl_NL","fr_FR","de_DE","it_IT","ja_JP","es_ES","zh_CN","zh_TW","cs_CZ","da_DK","fi_FI","ko_KR","nb_NO","pl_PL","pt_BR","pt_PT","ru_RU","sv_SE","tr_TR","hu_HU","el_GR","bg_BG","lt_LT","ca_ES","sk_SK","hr_HR","sl_SI","id_ID","ro_RO","lv_LV","sr_RS","uk_UA"};
	public final static String SELECT_FEATURE="Select Feature";
	public final static String[] ALL_FEATURES_LIST = {"PhoneDevice","Search","MyPresence","Meetings","MyStatusOptions","InstantMessage","Contacts","Video","Voice"};
	public final static String SELECT_TEST_CASE="Select Test Case";
	public final static String CASE_ID="Case ID";
	public final static String CASE_ID_TEXT = "450770;450657;451135";
	public final static String CASE_RANGE="Case Range";
	public final static String CASE_RANGES_TEXT = "450770-451135;450657-450770";
	public final static String CASE_PRIORITY="Case Priority";
	public final static String CASE_PRIORITY_P0="P0";
	public final static String CASE_PRIORITY_P1="P1";
	public final static String CASE_PRIORITY_P2="P2";
	public final static String CASE_PRIORITY_P3="P3";
	public final static String LOGIN_INFORMATION="Login Information";
	public final static String SIGN_IN_ADDRESSSTRING="Sign-in Address";
	public final static String SIGN_IN_ADDRESSSTRING_TEXT="iphone3@UCWA.ccsctp.net";
	public final static String PASSWORD="Password";
	public final static String PASSWORD_JULY2012="07Apples";
	public final static String USER_NAME="User Name";
	public final static String USER_NAME_LCCUSR1="iphone3";
	public final static String SCREENSHOT_LOCATION="Screenshot Location";
	public final static String SCREENSHOT_LOCATION_TEXT="C:\\AutoMationLogFile";
	public final static String TEST_BUILD_LOCATION="Test Build Location";
	public final static String TEST_BUILD_LOCATION_TEXT="C:\\AutoMationAPKFile";
	public final static String TEST_RESULT="Test Result";
	public final static String START="Start";
	public final static String END="End";
	public final static String SYNC="Sync ScreenShot";
	public final static String WARN_CHOOSE_LANGUAGE="Language must be choose!";
	public final static String LANGUAGE_STRING="Language List : ";
	public final static String FEATURES_STRING="Features List : ";
	public final static String NULL_STRING="null";
	public final static String CASE_ID_STRING="Cases ID : ";
	public final static String SIGNIN_ADDRESS_STRING="SignIn Address : ";
	public final static String PASSWORD_STRING="Password : ******\n";
	public final static String WARN_INPUT_ERROR="Input message error!";
	
	public final static String RUN_CASE_START="Run case start ----";
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
	public final static String RUN_COUNT="Run Count: ";
	public final static String SCREENSHOT="screenshot";
	public final static String CASE_START_TIME="Run Case Start Time : ";
	public final static String CASE_END_TIME="Run Case End Time : ";
	public final static String TIME_COUNT="Time Count ";
	public final static String MINUTES=" Minutes";
	
	public final static String ADB_S="adb -s ";
	public final static String PULL_LYNCAUTOMATION=" pull /mnt/sdcard/lync15automation ";
	public final static String PULL_BAT="//pull.bat";
	public final static String WRITE_FILE_FINSH="Write File Finsh";
	public final static String C_DISK="C://";
	public final static String LYNC_AUTOMATION_LOG="automation_log.txt";
	public final static String SCREENSHOTNAMELIST="screenshotnamelist.txt";
	public final static String SPLIT_LINE_ASTERISK_START="************************************";
	public final static String SPLIT_LINE_ASTERISK_END="************************************\n";
	public final static String PASS_CASE="Passed Cases : ";
	public final static String PASS_CASE_LIST="Pass Cases List : ";
	public final static String FAILED_CASE="Failed Cases : ";
	public final static String FAILED_CASE_LIST="Failed Cases List : ";
	public final static String PASS_RATE="Pass Rate : ";
	
	public final static String CREATE_SCREENSHOT_LOCATION="Create ScreenShot Location ";
	public final static String CREATE_SCREENSHOT_LOCATION_SUCCESS="Create ScreenShot Location success : ";
	
	public final static String CMD_C_START="cmd /c start ";
	public final static String SPLIT_LINE_EQUALS="=====================================================================\n";
	public final static String SET_PHONE_INTERNET="1.set phone connection the Internet.\n";
	public final static String SET_SDCARD_PERMISSION="2.set phone SDCARD read/write permission,need free space 2-4G.\n";
	public final static String SET_AUTO_ROTATE_SCREEN="3.settings-->Display-->uncheck 'Auto-rotate screen'. \n";
	public final static String REMOVE_OTA_SYSTEM_MESSAGE="4.remove android OTA system update message.\n";
	public final static String NEED_INSTALL_SIM_CARD="CaseID:406344;406348;406352;406353 need install sim card\n";
	public final static String NOT_SIM_CARD_RESULT="Result:Not screenshot / Throws Exception:'Can't Find My Mobile Phone'\n";
	public final static String NEED_SET_STATUS="CaseID:399822;399823;399824;399825;399826;399827;399828;399829;399830;399831;399832;399833;399836 need set status\n";
	public final static String NOT_SET_STATUS="Result:Not screenshot / Throws Exception:'Can't Find XXXX'\n";
	public final static String SET_GROUP_OFFLINE="CaseID:410007 need set group 'automationtest' offline \n";
	public final static String NOT_SET_GROUP_OFFLINE="Result:Not screenshot / Throws Exception:'Can't Find offline'\n";
	/*****************************MyCheckBoxList***********************************/
	public final static String FOCUS_CELL_HIGH_LIGHT_BORDER="List.focusCellHighlightBorder";
	/*****************************AndroidDebugBridgeUitlPhone***********************************/
	//installAPKToPhoneByPhoneName

	public final static String INSTALL_APK_START="adb install APK File Start ......";
	public final static String CMD_C_ADB_S="cmd /c "+ADB_S;
	public final static String INSTALL=" install ";
	public final static String INSTALL_APK_END="adb install APK File End ......";
	
	//initDevice
	public final static String SHELL_INSTARUMENT_CLASS=" shell am instrument -e class ";
	public final static String TEST_INIT="testInit ";
	public final static String TRANSVERSE_W="-w ";
	public final static String INSTRUMENTATION_TESTRUNNER="android.test.InstrumentationTestRunner";
	//changeDeviceLanguage
	public final static String TEST_CHANGE_LANGUAGE="testChangeLanguage ";
	//runCaseAllLanguages
	public final static String TEST_CASE_ID="testCaseId";
	public final static String SIGN_IN_START="Sign in start!";
	public final static String SIGN_IN_FINISH="Sign in finish!";
	public final static String TEST_SIGNIN="testSignIn ";
	public final static String SUCCESS="SUCCESS";
	public final static String FAILURE="FAILURE";
	public final static String UNINSTALL=" uninstall ";
	public final static String TAG_UNINSTALL_APK_START="uninstall APK start";
	public final static String TAG_UNINSTALL_APK_END="uninstall APK end";
	public final static String TAG_COPY_SD_START="******copy sdcard lync15automaiton start";
	public final static String TAG_COPY_SD_END="******copy sdcard lync15automaiton end";
	public final static String PULL=" pull ";
	public final static String MNT_SDCARD="/mnt/sdcard";
	public final static String SHELL_RM=" shell rm -r ";
	//runCaseByTestCaseMessageModels
	public final static String CONFIG_TXT="config.txt";
	public final static String PUSH=" push ";
	public final static String CASES_COUNT="cases count = ";
	public final static String TEST_CASEID="testCaseId";
	public final static String NO="No:";
	public final static String START_LINE="-------------------------Start--------------------------";
	public final static String END_LINE="-------------------------End--------------------------";
	public final static String CASEID="case ID : ";
	public final static String FINSH_RUN="finish run : ";
	public final static String START_TIME="start time : ";
	public final static String END_TIME="end time : ";
	public final static String RUN_TIME="run time : ";
	public final static String MINUTES1="minutes";
	
	//getDevicesIPBySystemVersion
	public final static String SHELL_VERSION=" shell getprop ro.build.version.release";
	public final static String TAG_DEVICE="__0.0";
	
	//writeChangelanguageTXT
	public final static String LANGUAGE="language:";
	public final static String UTF_8="utf-8";
	/*****************************BaseFileUitl***********************************/
	public final static String DATE_TIME="yyyy-MM-dd HH:mm:ss";
	public static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
	public final static String CASE_STRING="case";
	public final static String PNG="png";
	public final static String WRITER_PNG_FAILED="Failed to find png writer";
	public final static String RUNTIMELOG_TXT="lync15runtimelog.txt";
	public final static String TESTID="testid";
	public final static String CASESTYPE="casestype";
	public final static String PRIORITY="priority";
	public final static String CASERANGE="caserange";
	public final static String TESTMETHOD="testmethod";
	public final static String INTERNALDISCOVERY="internalDiscoveryAddress";
	public final static String EXTERNALDISCOVERY="externalDiscoveryAddress";
	public final static String DOMAIN="domain";
	public final static String USERNAME="userName";
	public final static String SIGNINADDRESS="signInAddress";
	public final static String PASSWORD1="password";
	public final static String BYTES="bytes";
	public final static String FILE_SIZE="File Size():";
	public final static String FILE_PATH="File Path:";
	public final static String BMP="/*.bmp ";
	public final static String FINISH="----finish----";
	
	//runCaseByCaseList
	public final static String FAST_RUN_TESTCASE=".MainActivityUIAutoMationTest#testCaseRunByRunCaseList ";
	//getAndroidDebugBridge
	public final static String BINDIR="com.android.screenshot.bindir";
	public final static String ADB="adb";
	public final static String GETDEVICELIST_TIMEOUT="Timeout getting device list!";
	
	//talkScreenShot
	public final static String DEVICE_NULL="device=null";
	public final static String DEVICE_ERROR="Error 0012152-954";

	/*****************************BaseFileUitl***********************************/
	//isapk
	public final static String AAPT_L="aapt l ";
	public final static String ANDROIDMANIFEST_XML="AndroidManifest.xml";
	public final static String APK=".apk";
	/*****************************RunCaseByConfig***********************************/
	//getCasesNumberByTestCaseMessageModel
	public final static String RUNCASESLIST_TXT="runlync15caseslist.txt";
	
	/*****************************SendMail***********************************/
	public final static String MailFrom = "tsghk@163.com";
	public final static String MailSubject = "回拨报告";
	public final static String MailHost = "smtp.163.com";
	public final static String MailUsername = "tsghk";
	public final static String MailPassword = "jacky2013";
	public final static String serverPort = "587";
	
	
	
	public final static String emailcontent = "自动化测试报告";//邮件文本内容
	public final static String emailsubject = "自动化测试报告";//邮件
	public final static String[] emailtos = {};//邮件
	public static String saveFile;//下载的文件名
	public static String savePath;//本次运行目录

}
