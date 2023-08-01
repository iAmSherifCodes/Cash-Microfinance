package com.example.loanapp.notification.mail;

import com.example.loanapp.notification.dto.EmailRequest;

public interface MailService {
    String sendMail(EmailRequest emailRequest);
}
