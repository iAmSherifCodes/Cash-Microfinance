package com.example.loanapp.notification.mail;

import com.example.loanapp.notification.dto.EmailRequest;
import com.example.loanapp.notification.dto.MailInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BrevoMailImpl implements MailService{
    @Value("${sendinblue.mail.api_key}")
    private String apiKey;

    @Value("${sendinblue.mail.url}")
    private String mailUrl;

    public String sendMail(EmailRequest emailRequest) {

        emailRequest.setSender(
                new MailInfo("Sherif", "awofiranyesherif4@gmail.com")
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
