package com.example.loanapp.dto.request;

import lombok.Data;

@Data
public class OfficerLoginRequest {
    private String fullName;
    private String password;
}
