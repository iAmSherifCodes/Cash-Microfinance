package com.example.loanapp.notification.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class EmailRequest {
    private MailInfo sender;
    private List<MailInfo> to;
    private String subject;
    private String htmlContent;
}
