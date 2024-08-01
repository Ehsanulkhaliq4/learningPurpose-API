package com.exam.examserver.service.ForgotPassword.impl;

import com.exam.examserver.service.ForgotPassword.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendEmailWithHtmlTags(String to, String subject, String htmlContact) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(htmlContact,true);
            messageHelper.setFrom("virtualsociety274@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
