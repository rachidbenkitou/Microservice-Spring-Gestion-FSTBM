package com.example.securityservice.Mappers;

import com.example.securityservice.DTO.RoleRequestDto;
import com.example.securityservice.DTO.RoleResponseDTO;
import com.example.securityservice.DTO.UserRequestDto;
import com.example.securityservice.DTO.UserResponseDTO;
import com.example.securityservice.Entities.Role;
import com.example.securityservice.Entities.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface RoleMapper {
       Role dtoToModel(RoleRequestDto roleRequestDto);

       RoleResponseDTO modelToDto(Role role);

    List<RoleResponseDTO> modelToDtos(List<Role> roles);
}
