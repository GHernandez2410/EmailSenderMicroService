package com.mail.service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mail.json.MailRequest;
import com.mail.model.User;

@Service
public class MailService {

	private JavaMailSender javaMailSender;

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	
	public void sendEmail(User user,MailRequest mailrequest) throws MailException {

		

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmailAddress());
		mail.setSubject(mailrequest.getSubject());
		mail.setText(mailrequest.getText() + "\nSent by: " + mailrequest.getSender());

		/*
		 * This send() contains an Object of SimpleMailMessage as an Parameter
		 */
		javaMailSender.send(mail);
	}

	
	public void sendEmailWithAttachment(User user) throws MailException, MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setTo(user.getEmailAddress());
		helper.setSubject("Testing Mail API with Attachment");
		helper.setText("Please find the attached document below.");

		ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
		helper.addAttachment(classPathResource.getFilename(), classPathResource);

		javaMailSender.send(mimeMessage);
	}

}