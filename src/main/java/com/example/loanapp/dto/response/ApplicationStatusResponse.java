package com.example.loanapp.dto.response;

import lombok.Data;

@Data
public class ApplicationStatusResponse {
    private String applicationStatus;
    private String notFoundMessage;
}
