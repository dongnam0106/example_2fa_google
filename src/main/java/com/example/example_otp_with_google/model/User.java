package com.example.example_otp_with_google.model;

import lombok.Data;
import org.jboss.aerogear.security.otp.api.Base32;

import javax.persistence.*;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private boolean isUsing2FA;
    private String secret;

    public User() {
        super();
        this.secret = Base32.random();
    }
}