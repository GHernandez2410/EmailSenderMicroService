package com.mail.json;

import javax.validation.constraints.NotEmpty;

public class MailRequest {
	
	@NotEmpty
	private String subject;
	
	@NotEmpty
	private String text;
	
	@NotEmpty
	private String sender;
	
	public MailRequest(String subject, String text, String sender) {
		this.subject = subject;
		this.text = text;
		this.sender = sender;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
}