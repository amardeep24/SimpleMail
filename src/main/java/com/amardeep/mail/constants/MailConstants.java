package com.amardeep.mail.constants;

public class MailConstants {

	public static final boolean SMTP_AUTH= true;
	
	public static final boolean TTLS_ENABLE=true;
	
	public static final String MAIL_SERVER="smtp.gmail.com";
	
	public static final int MAIL_PORT=465;
	
	public enum ResponseCode {
		SENT(0), FAILED(-1), PENDING(1);

		private final int value;

		ResponseCode(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	}
}
