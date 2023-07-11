package com.example.example_otp_with_google.repository;

import com.example.example_otp_with_google.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
