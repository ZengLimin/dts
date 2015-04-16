package com.zenglm.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TestSms {

	public static int SendSms(String SmsUsername, String SmsPassword,
			String MobileNum, String SendMsg, Boolean AllowLong) {
		/*参数依次为：您在晴朗短信网关（SMSGATE.CN）注册的用户名、登录密码、接收者手机号、短信内容、是否允许发送长短信。返回值：>0代表您成功发送的短信数目，-1用户不存在，-2系统错误，-3余额不足，-4无发送内容，-5无接收号码，-6网络通信错误，-7密码错误或IP非法，-8帐户未激活，-9网络异常。**/
		int sendstate;
		String s0;
		String s1;
		String s2;
		if (AllowLong) {
			s2 = "1";
		} else {
			s2 = "0";
		}
		try {
			SendMsg = URLEncoder.encode(SendMsg, "GB2312");
		} catch (UnsupportedEncodingException e0) {
		}
		s1 = "http://www.smsgate.cn/gb.asp?usr=".concat(SmsUsername)
				.concat("&pwd=").concat(SmsPassword).concat("&tel=")
				.concat(MobileNum).concat("&msg=").concat(SendMsg)
				.concat("&lng=").concat(s2);
		s0 = SendPost(s1);
		try {
			sendstate = Integer.parseInt(s0);
		} catch (Exception e1) {
			sendstate = -9;
		}
		return sendstate;
	}

	public static String SendPost(String url) {
		String result = "";
		try {
			URL httpurl = new URL(url);
			HttpURLConnection httpConn = (HttpURLConnection) httpurl
					.openConnection();
			httpConn.setDoInput(true);
			BufferedReader readin = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream()));
			String CurLine;
			while ((CurLine = readin.readLine()) != null) {
				result += CurLine;
			}
			readin.close();
		} catch (Exception e) {
		}
		return result;
	}
}
