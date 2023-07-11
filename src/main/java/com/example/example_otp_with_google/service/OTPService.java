package com.example.example_otp_with_google.service;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.springframework.stereotype.Service;

@Service
public class OTPService {
    GoogleAuthenticator gAuth = new GoogleAuthenticator();

    //tạo ra secret key => save key vào user khi đăng ký người dùng mới
    public String generateOTP() {
        GoogleAuthenticatorKey key = gAuth.createCredentials();
        String secret = key.getKey();
        return secret;
    }

    //sử dụng tiện ích gg authenticator, nhập secret key vào đó sẽ nhận được code mỗi 30s
    //sử dụng secret key và code để xac thực khi login (verify username, password, code)
    public boolean verifyOTP(int code, String secret) {
        return gAuth.authorize(secret, code);
    }
}
