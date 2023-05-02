package com.example.securityservice.Srvice.Impl;

import com.example.securityservice.DAO.RoleDAO;
import com.example.securityservice.DAO.UserDAO;
import com.example.securityservice.DTO.RoleRequestDto;
import com.example.securityservice.DTO.RoleResponseDTO;
import com.example.securityservice.DTO.UserRequestDto;
import com.example.securityservice.DTO.UserResponseDTO;
import com.example.securityservice.Entities.Role;
import com.example.securityservice.Entities.User;
import com.example.securityservice.Mappers.RoleMapper;
import com.example.securityservice.Mappers.UserMapper;
import com.example.securityservice.Srvice.RoleService;
import com.example.securityservice.exceptions.EntityAlreadyExistException;
import com.example.securityservice.exceptions.EntityNotFoundException;
import com.example.securityservice.exceptions.InvalidEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class RoleServiceImpl implements RoleService {
    private RoleDAO roleDAO;
    private UserDAO userDAO;
    private RoleMapper roleMapper;
    private UserMapper userMapper;
    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO,UserDAO userDAO, RoleMapper roleMapper,UserMapper userMapper) {
        this.roleDAO = roleDAO;
        this.userDAO = userDAO;
        this.roleMapper = roleMapper;
        this.userMapper = userMapper;
    }

    @Override
    public RoleResponseDTO addRole(RoleRequestDto roleRequestDto) throws InvalidEntityException, EntityAlreadyExistException {
        if(roleRequestDto.equals(null))
            throw new InvalidEntityException("Role note Valid");
        if(roleDAO.findByRole(roleRequestDto.getRole()).isPresent())
            throw new EntityAlreadyExistException("Role already exists");
        return roleMapper.modelToDto(roleDAO.save(roleMapper.dtoToModel(roleRequestDto)));
    }

    @Override
    public RoleResponseDTO getRoleByName(String role) throws EntityNotFoundException {
        Optional<Role> roleWAnted = roleDAO.findByRole(role);
        if(!roleWAnted.isPresent())
            throw new EntityNotFoundException("Role with name "+role+"not found");
        return roleMapper.modelToDto(roleWAnted.get());
    }

    @Override
    public void deleteRole(String role) throws EntityNotFoundException{
        RoleResponseDTO roleResponseDTO = getRoleByName(role);
        if(roleResponseDTO.equals(null))
            throw new EntityNotFoundException("Role with name "+role+"not found");
        else roleDAO.deleteById(roleResponseDTO.getRoleId());
    }

    @Override
    public UserResponseDTO delteRoleFromUser(Long userId, String role) {
        User user = userDAO.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));;
        List<Role> roles = user.getRoles();
        boolean roleRemoved = roles.removeIf(r -> r.getRole().equals(role));
        if (!roleRemoved) {
            throw new EntityNotFoundException("Role not found for user");
        }
        user.setRoles(roles);
        return userMapper.modelToDto(
                userDAO.save(
                        user
                )
        );
    }
}
