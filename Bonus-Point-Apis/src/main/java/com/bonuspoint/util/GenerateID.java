package com.bonuspoint.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class GenerateID {

	public static String generateCustID(long cid) {
		String rNo = String.valueOf(cid);
		String customerID = "BP" + StringUtils.leftPad(rNo, 10, "0");
		return customerID;
	}

	public static String generateProjID(long pid, String abbrv) {
		String rNo = String.valueOf(pid);
		String projectID = abbrv + StringUtils.leftPad(rNo, 6, "0");
		return projectID;
	}

	public static String generateMerchantID(ArrayList<String> mids, String abbrv) {

		String merchantID = null;
		while (true) {
			merchantID = getRandomMid(abbrv);
			if (!checkUnique(merchantID, mids)) {
				break;
			}
		}

		return merchantID;
	}

	private static boolean checkUnique(String iD, ArrayList<String> mids) {
		return mids.contains(iD);
	}

	public static String getRandomMid(String abbrv) {
		long millsec = System.currentTimeMillis();
		String numbers = String.valueOf(millsec);
		Random random = new Random();
		char[] otp = new char[11];

		for (int i = 0; i < 11; i++) {
			otp[i] = numbers.charAt(random.nextInt(numbers.length()));
		}
		String sOtp = new String(otp);
		String merchantID = abbrv + sOtp;
		return merchantID;
	}

	public static String generateTerminalIDs(ArrayList<String> tids) {
		String terminalID = null;
		while (true) {
			terminalID = getRandomTid();
			if (!checkUnique(terminalID, tids)) {
				break;
			}
		}
		return terminalID;
	}

	private static String getRandomTid() {
		long millsec = System.currentTimeMillis();
		String numbers = String.valueOf(millsec);
		Random random = new Random();
		char[] otp = new char[8];

		for (int i = 0; i < 8; i++) {
			otp[i] = numbers.charAt(random.nextInt(numbers.length()));
		}
		String sOtp = new String(otp);
		String terminalID = sOtp;
		return terminalID;
	}

	public static String generateEmployeeID(long eid) {
		String rNo = String.valueOf(eid);
		String employeeID = "BPE" + StringUtils.leftPad(rNo, 10, "0");
		return employeeID;
	}

	public static String generateTransactionID(long mtlId) {
		String rNo = String.valueOf(mtlId);
		String transactionID = "TXN" + StringUtils.leftPad(rNo, 8, "0");
		return transactionID;
	}

	public static String generateRrn(long atId, Date createdAt) {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyy");
		String date = sdf.format(createdAt);
		String rNo = String.valueOf(atId);
		String rrnID = "REW" + date + StringUtils.leftPad(rNo, 10, "0");
		return rrnID;
	}

	public static String generateOfferCode(long offerId, String offerTitle) {
		String rNo = String.valueOf(offerId);
		String trimmedtitle = offerTitle.replaceAll(" ", "");
		String offerCode = "BP" + trimmedtitle.substring(0, 4) + StringUtils.leftPad(rNo, 3, "0");
		return offerCode;
	}

	public static int generatePin(int length) {
		String numbers = "1234567890";
		Random random = new Random();
		char[] otp = new char[length];

		for (int i = 0; i < length; i++) {
			otp[i] = numbers.charAt(random.nextInt(numbers.length()));
		}
		String sOtp = new String(otp);
		return new Integer(sOtp);
	}

}
