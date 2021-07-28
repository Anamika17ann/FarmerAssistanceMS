package com.farm.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farm.main.entity.MailEntity;
import com.farm.main.service.MailService;

@RestController
@RequestMapping("/mailer")
public class MailController {
	
	@Autowired
	MailService mailer ;
	
	@PostMapping("/mailservice")
	public String mailService(@RequestBody MailEntity mail) {
		mailer.sendMail(mail.getToEmail(),mail.getSubject(),mail.getMessage());
		return "Mail sent";
	}
	

}
