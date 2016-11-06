package com.amardeep.mail.service;

import com.amardeep.mail.bo.MailRequest;
import com.amardeep.mail.bo.MailResponse;

public interface MailService {

	public MailResponse sendMail(MailRequest mailRequest); 
}
