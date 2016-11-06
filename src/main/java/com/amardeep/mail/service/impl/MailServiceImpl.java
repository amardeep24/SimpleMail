package com.amardeep.mail.service.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.amardeep.mail.bo.MailRequest;
import com.amardeep.mail.bo.MailResponse;
import com.amardeep.mail.constants.MailConstants;
import com.amardeep.mail.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	Logger log = Logger.getLogger(MailServiceImpl.class);
	
	@Override
	public MailResponse sendMail(MailRequest mailRequest) {
		try {
			// Setting mail properties
			Properties mailProperties = new Properties();
			mailProperties.put("mail.smtp.auth", MailConstants.SMTP_AUTH);
			/*mailProperties.put("mail.smtp.starttls.enable",
					MailConstants.TTLS_ENABLE);*/
			mailProperties.put("mail.smtp.host", MailConstants.MAIL_SERVER);
			mailProperties.put("mail.smtp.socketFactory.port", MailConstants.MAIL_PORT);
			mailProperties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			mailProperties.put("mail.smtp.port", MailConstants.MAIL_PORT);
			
			log.info("username received "+mailRequest.getUsername());
			log.debug("password received "+mailRequest.getPassword());
			log.info("message received "+mailRequest.getMessage());
			log.info("subject line "+mailRequest.getSubject());
			log.info("receipents received "+mailRequest.getRecipient()[0]);
			
			// Credential authenticator
			Authenticator authenticator = new SMTPAuthenticator(mailRequest.getUsername(),
					mailRequest.getPassword());

			Session session = Session.getDefaultInstance(mailProperties,
					authenticator);
			// for dev environment
			session.setDebug(true);

			// Constructing email message
			Message message = new MimeMessage(session);
			
			String[] recipients= mailRequest.getRecipient();

			// Setting recipients
			InternetAddress[] addressTo = new InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++) {
				addressTo[i] = new InternetAddress(recipients[i]);
			}
			message.setFrom(new InternetAddress(mailRequest.getUsername()));
			
			message.setRecipients(Message.RecipientType.TO, addressTo);

			message.setSubject(mailRequest.getSubject());

			message.setContent(mailRequest.getMessage(), "text/plain");

			Transport.send(message);
		} catch (MessagingException exception) {
			log.error(exception.getMessage(),exception);
		}

		return null;
	}

}

/**
 * SimpleAuthenticator is used to do simple authentication when the SMTP server
 * requires it.
 */
class SMTPAuthenticator extends javax.mail.Authenticator {
	private String username;
	private String password;

	SMTPAuthenticator(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		String username = this.username;
		String password = this.password;
		return new PasswordAuthentication(username, password);
	}
}
