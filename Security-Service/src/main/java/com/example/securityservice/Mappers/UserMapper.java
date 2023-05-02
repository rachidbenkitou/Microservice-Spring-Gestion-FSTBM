package com.example.securityservice.Mappers;

import com.example.securityservice.DTO.UserRequestDto;
import com.example.securityservice.DTO.UserResponseDTO;
import com.example.securityservice.Entities.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {
       User dtoToModel(UserRequestDto userRequestDto);

       UserResponseDTO modelToDto(User user);

    List<UserResponseDTO> modelToDtos(List<User> users);
}
