package com.example.example_otp_with_google.service;

import com.example.example_otp_with_google.dto.UserDTO;
import com.example.example_otp_with_google.entity.User;
import com.example.example_otp_with_google.mapper.UserMapper;
import com.example.example_otp_with_google.repository.UserRepository;
import com.example.example_otp_with_google.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private OTPService otpService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper mapper;

    public UserDTO create(UserDTO dto) {
        String secretKey = otpService.generateOTP();
        dto.setSecretKey(secretKey);
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User user = mapper.toEntity(dto);
        repository.save(user);
        dto.setPassword("");
        return dto;
    }

    public UserDTO update(UserDTO dto) {
        return null;
    }

    public Optional<UserDTO> getDetails(Long id) {
        return Optional.empty();
    }

    public UserDTO delete(UserDTO dto) {
        return null;
    }

    public List<UserDTO> findAll() {
        return null;
    }

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = repository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
    }

    public UserDTO findByUsername(String username) {
        Optional<User> userOptional = repository.findByUsername(username);
        return mapper.toDto(userOptional.get());
    }

}
