package com.bonuspoint.util;

import java.util.Random;

public class GeneratePassword {

	public static String generatePassword(int len) {
		String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String Small_chars = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		String values = Capital_chars + Small_chars + numbers;

		Random rndm_method = new Random();

		char[] password = new char[len];

		for (int i = 0; i < len; i++) {
			
			password[i] = values.charAt(rndm_method.nextInt(values.length()));
		}
		return new String(password);
	}

}
