package com.amardeep.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amardeep.mail.bo.MailRequest;
import com.amardeep.mail.bo.MailResponse;
import com.amardeep.mail.constants.MailConstants.ResponseCode;
import com.amardeep.mail.service.MailService;

@RestController
public class MailController {
	
	@Autowired 
	private MailService mailService;
	
	@RequestMapping(value="/sendmail", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public MailResponse Sendmail(@RequestBody final MailRequest mailRequest){
		
		Thread mailThread =new Thread(new Runnable(){

			@Override
			public void run() {
				mailService.sendMail(mailRequest);
			}
			
		});
		
		mailThread.start();
		return new MailResponse().setResponseCode(ResponseCode.SENT).setResponseText("Sending message...");
		
		
	}
}
