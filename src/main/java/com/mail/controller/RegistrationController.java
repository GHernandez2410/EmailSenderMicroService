package com.mail.controller;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mail.json.MailRequest;
import com.mail.model.User;
import com.mail.service.MailService;

/**
 * This class contains a Mail API developed using Spring Boot
 * 
 * @author Gonzalo Hernandez
 *
 */
@CrossOrigin(origins = "*")
@RestController
public class RegistrationController {
	
	@Autowired
	private MailService notificationService;

	@Autowired
	private User user;

	
	@PostMapping("/send-mail")
	public String send(@Valid @RequestBody MailRequest mail) {

		user.setEmailAddress("blabla0@gmail.com");//receiver's email
		
		try {
			notificationService.sendEmail(user,mail);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}

	/**
	 * 
	 * @return
	 * @throws MessagingException
	 */
	@PostMapping("/send-mail-attachment")
	public String sendWithAttachment() throws MessagingException {

		
		user.setEmailAddress("blabla@gmail.com"); //Receiver's email address

		/*
		 * Here we will call sendEmailWithAttachment() for Sending mail to the sender
		 * that contains a attachment.
		 */
		try {
			notificationService.sendEmailWithAttachment(user);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}
}