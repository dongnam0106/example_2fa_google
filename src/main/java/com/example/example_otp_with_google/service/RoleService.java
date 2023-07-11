package com.example.example_otp_with_google.service;

import com.example.example_otp_with_google.dto.RoleDTO;
import com.example.example_otp_with_google.entity.Role;
import com.example.example_otp_with_google.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;

    public RoleDTO create(RoleDTO dto) {
        return null;
    }

    public RoleDTO update(RoleDTO dto) {
        return null;
    }

    public Optional<RoleDTO> getDetails(Long id) {
        return Optional.empty();
    }

    public RoleDTO delete(RoleDTO dto) {
        return null;
    }

    public List<RoleDTO> findAll() {
        return null;
    }

    public Optional<Role> findById(Long id) {
        return repository.findById(id);
    }

    public Role findByName(String name) {
        return repository.findByName(name);
    }

}
