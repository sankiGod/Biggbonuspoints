package com.bonuspoint.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bonuspoint.property.SmsProperties;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
public class SendSMS {

	private static String base_url;
	private static String username;
	private static String password;
	private static String sendername;

	@Autowired
	public SendSMS(SmsProperties smsProperties) {
		SendSMS.base_url = smsProperties.getBaseurl();
		SendSMS.username = smsProperties.getUsername();
		SendSMS.password = smsProperties.getPassword();
		SendSMS.sendername = smsProperties.getSendername();
	}

	public static Response sendSMS(String mobileNumber, String message) throws IOException {

		OkHttpClient client = new OkHttpClient();
		String updatedMobileNumber = "91" + mobileNumber;
		HttpUrl.Builder urlBuilder = HttpUrl.parse(base_url).newBuilder();
		urlBuilder.addQueryParameter("username", username).addQueryParameter("password", password)
				.addQueryParameter("sendername", sendername).addQueryParameter("mobileno", updatedMobileNumber)
				.addQueryParameter("message", message);
		String url = urlBuilder.build().toString();
		RequestBody body = RequestBody.create(null, new byte[0]);

		Request request = new Request.Builder().url(url).post(body).addHeader("Cache-Control", "no-cache").build();

		return client.newCall(request).execute();

	}
}
