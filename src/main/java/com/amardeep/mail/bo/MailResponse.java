package com.amardeep.mail.bo;

import com.amardeep.mail.constants.MailConstants.ResponseCode;
public class MailResponse {

	private String responseText;
	private ResponseCode responseCode;

	public String getResponseText() {
		return responseText;
	}

	public MailResponse setResponseText(String responseText) {
		this.responseText = responseText;
		return this;
	}

	public ResponseCode getResponseCode() {
		return responseCode;
	}

	public MailResponse setResponseCode(ResponseCode responseCode) {
		this.responseCode = responseCode;
		return this;
	}

}
