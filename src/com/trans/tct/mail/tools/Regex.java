package com.trans.tct.mail.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	public static boolean regexMail(String str) {

		if (str.length() != 0) {

			String regex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

			Pattern pattern = Pattern.compile(regex);

			Matcher matcher = pattern.matcher(str.trim());

			return matcher.matches();
		} else {

			return false;
		}
	}
}
