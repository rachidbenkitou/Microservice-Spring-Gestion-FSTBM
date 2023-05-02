package com.example.securityservice.Srvice;

import com.example.securityservice.DTO.RoleRequestDto;
import com.example.securityservice.DTO.RoleResponseDTO;
import com.example.securityservice.DTO.UserRequestDto;
import com.example.securityservice.DTO.UserResponseDTO;

public interface RoleService {
    RoleResponseDTO addRole(RoleRequestDto roleRequestDto);
    RoleResponseDTO getRoleByName(String role);
    void deleteRole(String role);
    UserResponseDTO delteRoleFromUser(Long id, String role);
}
