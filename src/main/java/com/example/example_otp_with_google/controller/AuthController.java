package com.example.example_otp_with_google.controller;

import com.example.example_otp_with_google.dto.LoginReq;
import com.example.example_otp_with_google.dto.UserDTO;
import com.example.example_otp_with_google.security.jwt.JwtResponse;
import com.example.example_otp_with_google.security.jwt.JwtService;
import com.example.example_otp_with_google.service.OTPService;
import com.example.example_otp_with_google.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private OTPService otpService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userReq) {
        UserDTO dto = userService.create(userReq);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginReq loginReq) throws NotFoundException {
        UserDTO userDTO = userService.findByUsername(loginReq.getUsername());
        if (Objects.isNull(userDTO)) {
            throw new NotFoundException("No");
        }
        if (!otpService.verifyOTP(loginReq.getCodeOTP(),userDTO.getSecretKey())) {
            throw new NotFoundException("Sai key");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserDTO currentUser = userService.findByUsername(loginReq.getUsername());
        JwtResponse tokenRes = new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), currentUser.getFullName(), userDetails.getAuthorities());
        return ResponseEntity.ok(tokenRes);
    }
}
