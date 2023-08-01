package com.example.loanapp.notification.mail;

import com.example.loanapp.notification.dto.EmailRequest;
import com.example.loanapp.notification.dto.MailInfo;
import org.springframework.web.reactive.function.client.WebClient;

public class BrevoMailImpl implements MailService{
    public String sendMail(EmailRequest emailRequest) {
        emailRequest.setSender(
                new MailInfo("Opay", "contact@opay.com")
        );

        return WebClient.builder()
                .baseUrl(mailUrl)
                .defaultHeader("api-key", apiKey)
                .build()
                .post()
                .bodyValue(emailRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
