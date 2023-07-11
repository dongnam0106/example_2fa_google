package com.example.example_otp_with_google.mapper;

import com.example.example_otp_with_google.dto.UserDTO;
import com.example.example_otp_with_google.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {
}
