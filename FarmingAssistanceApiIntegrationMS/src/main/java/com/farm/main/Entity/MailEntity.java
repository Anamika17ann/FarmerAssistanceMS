package com.farm.main.Entity;

import org.springframework.stereotype.Component;

@Component
public class MailEntity {

	private String[] toEmail;
	private String subject;
	private String message;

	public String[] getToEmail() {
		return toEmail;
	}

	public void setToEmail(String[] toEmail) {
		this.toEmail = toEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
