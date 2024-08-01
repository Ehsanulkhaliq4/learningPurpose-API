package com.exam.examserver.service.ForgotPassword;

public interface EmailService {
    // send email with html tags
    void sendEmailWithHtmlTags(String to , String subject , String htmlContact);
}
