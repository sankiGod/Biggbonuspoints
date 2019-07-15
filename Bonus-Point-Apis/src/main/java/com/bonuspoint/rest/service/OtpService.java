package com.bonuspoint.rest.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonuspoint.exception.CustomErrorException;
import com.bonuspoint.model.Otp;
import com.bonuspoint.repository.OtpRepository;
import com.bonuspoint.util.ErrorCodes;
import com.bonuspoint.util.GenerateOtp;
import com.bonuspoint.util.SendSMS;

import okhttp3.Response;

@Service
public class OtpService {

	@Autowired
	OtpRepository repository;

	public Otp sendRegistrationOtp(String mobileNumber) {
		Otp otp = new Otp();
		otp.setOtp(GenerateOtp.generateOTP(6));
		String message = "Welcome to Biggbonuspoints registration. Your OTP is " + otp.getOtp();

		Response response;
		try {
			response = SendSMS.sendSMS(mobileNumber, message);
			response.close();
			return repository.save(otp);
		} catch (IOException e) {
			throw new CustomErrorException("Customer", "Not Able To Send OTP",
					ErrorCodes.INTERNAL_SERVER_ERROR.getCode());
		}

	}

	public Otp sendPasswordChangeOtp(String mobileNumber) {
		Otp otp = new Otp();
		otp.setOtp(GenerateOtp.generateOTP(6));
		String message = "You have initiated password change for Biggbonuspoints Mobile App. Kindly use OTP " + otp.getOtp();

		Response response;
		try {
			response = SendSMS.sendSMS(mobileNumber, message);
			response.close();
			return repository.save(otp);
		} catch (IOException e) {
			throw new CustomErrorException("Customer", "Not Able To Send OTP",
					ErrorCodes.INTERNAL_SERVER_ERROR.getCode());
		}
	}

	public Otp sendRedeemOtp(String mobileNumber) {
		Otp otp = new Otp();
		otp.setOtp(GenerateOtp.generateOTP(6));
		String message = "You have chosen to redeem Biggbonuspoints. Kindly share/enter OTP " + otp.getOtp() + " on shop owner Mobile App.";

		Response response;
		try {
			response = SendSMS.sendSMS(mobileNumber, message);
			response.close();
			return repository.save(otp);
		} catch (IOException e) {
			throw new CustomErrorException("Customer", "Not Able To Send OTP",
					ErrorCodes.INTERNAL_SERVER_ERROR.getCode());
		}
	}
}
