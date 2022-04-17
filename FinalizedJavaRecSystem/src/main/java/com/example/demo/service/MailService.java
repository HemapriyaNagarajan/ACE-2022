package com.example.demo.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Email;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.itextpdf.text.Font;
import com.itextpdf.text.Chunk;

@Service
public class MailService {
	private JavaMailSender javaMailSender;
	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}


	public void sendEmail(Email user) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmailAddress());
		mail.setSubject("Congratulations !!! You have been shortlisted");
		mail.setText("We are happy to inform you that your application has been considered for the upcoming selection");
		javaMailSender.send(mail);
	}

	
	public void sendEmailWithAttachment(Email user) throws MailException, MessagingException, FileNotFoundException, DocumentException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setTo(user.getEmailAddress());
		helper.setSubject("Congratulations !!! You have been shortlisted");
		helper.setText("We are happy to inform you that your application has been considered . Please find the attached document below.");

		
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(("./src/main/resources/Main.pdf")));
		document.open();
		Paragraph line = new Paragraph("\n");
		
		String text1 = "Greetings !!!";
		Paragraph p1 = new Paragraph(text1);  
		
		String text2 = user.getFirstName() + " "+ user.getLastName();
		Paragraph p2 = new Paragraph(text2); 
		
		String text3 = "We are delighted to offer you the full-time job offer with an anticipated earliest start date, contingent upon screening your resume";
		Paragraph p3 = new Paragraph(text3);
		
		String text4 = "We offer comprehensive benefits program, which includes medical insurance, paid time off, etc. Your employment with us will be on an at-will basis, which means you and the company are free to terminate employment at any time, with or without cause or advance notice.";
		Paragraph p4 = new Paragraph(text4);
		
		String text5 = "This letter is not a contract indicating employment terms or duration";
		Paragraph p5 = new Paragraph(text5);
		
		String text6 = "Please confirm your acceptance of this offer by signing and returning this letter by the end of this month.";
		Paragraph p6 = new Paragraph(text6);
		
		String text7 = "Regards, Team Rekruit :)";
		Paragraph p7 = new Paragraph(text7);
		
		document.add(p1);
		document.add(line);
		document.add(p2);
		document.add(line);
		document.add(p3);
		document.add(line);
		document.add(p4);
		document.add(line);
		document.add(p5);
		document.add(line);
		document.add(p6);
		document.add(line);
		document.add(line);
		document.add(p7);
		document.close();
		
		FileSystemResource file  = new FileSystemResource(new File("./src/main/resources/Main.pdf"));
        helper.addAttachment("Main.pdf", file);
        javaMailSender.send(mimeMessage);

	}

}