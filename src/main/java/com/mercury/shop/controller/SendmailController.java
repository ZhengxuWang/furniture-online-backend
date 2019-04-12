package com.mercury.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.shop.bean.Mail;
import com.mercury.shop.http.Response;
import com.mercury.shop.mail.EmailService;

@RestController
public class SendmailController {
	@Autowired
	EmailService emailservice;
	@PostMapping("/mail")
	public Response sendMail(@RequestBody Mail mail) {
		try {
			emailservice.sendSimpleMessage(mail.to, mail.subject, mail.text);
			return new Response(true);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response(false);
	}
}
