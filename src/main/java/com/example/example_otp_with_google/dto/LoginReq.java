package com.example.example_otp_with_google.dto;

import lombok.Data;

@Data
public class LoginReq {
    private String username;
    private String password;
    private int codeOTP;
}
