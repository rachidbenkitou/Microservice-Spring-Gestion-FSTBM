package com.example.securityservice.Srvice;

import com.example.securityservice.DTO.*;

import java.util.List;

public interface UserService {
    UserResponseDTO signIn(UserRequestDto userRequestDto);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserByEmail(String email);

    UserResponseDTO updatePassword(UpdatePasswordDTO updatePasswordDTO);
    UserResponseDTO updateUser(UserRequestDto userRequestDto);
    void deleteUser(Long id);

    UserResponseDTO addRoleToUser(UserRequestDto userRequestDto, String role);

}
