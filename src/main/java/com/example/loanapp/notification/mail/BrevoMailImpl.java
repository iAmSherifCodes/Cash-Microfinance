package com.example.loanapp.notification.mail;

import com.example.loanapp.notification.dto.EmailRequest;
import com.example.loanapp.notification.dto.MailInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BrevoMailImpl implements MailService{
    @Value("${sendinblue.mail.api_key}")
    private String apiKey;

    @Value("${sendinblue.mail.url}")
    private String mailUrl;

//    @Async
    public String sendMail(EmailRequest emailRequest) {

        emailRequest.setSender(
                new MailInfo("Sherif", "awofiranyesherif4@gmail.com")
        );

        return WebClient.builder()
                .baseUrl("https://api.brevo.com/v3/smtp/email")
                .defaultHeader("api-key", "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")
                .build()
                .post()
                .bodyValue(emailRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
