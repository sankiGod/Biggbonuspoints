package com.bonuspoint.util;

public enum ErrorCodes {

	INCORRECT_PASSWORD(101, "Incorrect Password"),

	ACCOUNT_NOT_FOUND(102, "Customer BP Account Not Found"),

	INSUFFICIENT_FUNDS(103, "Insufficient Funds"),

	LIMIT_NOT_SET(104, "Merchant Limit Not Set"),

	SUMMARY_NOT_AVAILABLE(105, "Customer Summary Not Available"),

	MAPPING_NOT_FOUND(106, "No Mapping found for given merchantID"),

	ACCOUNT_DOES_NOT_EXIST(107, "Corporate Account Does Not Exist"),

	CANNOT_TRANSFER_FUNDS(108, "Funds Transfer Facility Not Applicable"),

	ID_NOT_FOUND(109, "Please provide Id Type With The Request"),

	UNSUPPORTED_TYPE_VALUE(110, "Unsupported Type Value. Should be either 'merchant' or 'project'"),

	NOT_ALLOWED(111, "Not Allowed"),

	PROJECT_MOBILE_EXISTS(113, "Project With This Mobile Number Already Exists!"),

	PROJECT_EMAIL_EXISTS(114, "Project With This Email Id Already Exists!"),

	MERCHANT_MOBILE_EXISTS(115, "Merchant With This Mobile Number Already Exists!"),

	MERCHANT_EMAIL_EXISTS(116, "Merchant With This Email Id Already Exists!"),

	MERCHANT_SHOP_EXISTS(117, "Merchant With This Shop Name Already Exists"),

	DUPLICATE_ENTRY(120, "Duplicate Entry"),

	INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

	VALIDATION_FAILED(408, "Validation Failed/Bad Request"),

	RESOURCE_NOT_FOUND(404, "Resource Not Found"),

	BAD_REQUEST(400, "Bad Request");

	private final int code;

	private final String description;

	private ErrorCodes(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return this.code;
	}

	public String getDescription() {
		return this.description;
	}

}
