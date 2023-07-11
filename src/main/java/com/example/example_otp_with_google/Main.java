package com.example.example_otp_with_google;

import com.example.example_otp_with_google.service.OTPService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OTPService otpService = new OTPService();
        Scanner scanner = new Scanner(System.in);
        System.out.println(otpService.generateOTP());
        System.out.println("Nhập secret:");
        String secret = scanner.next();
        System.out.println("Nhập code:");
        int code = scanner.nextInt();
        System.out.println(otpService.verifyOTP(code, secret));
    }
}
