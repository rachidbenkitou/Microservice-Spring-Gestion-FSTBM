package com.example.securityservice.Srvice.Impl;

import com.example.securityservice.DAO.RoleDAO;
import com.example.securityservice.DAO.UserDAO;
import com.example.securityservice.DTO.*;
import com.example.securityservice.Entities.Role;
import com.example.securityservice.Entities.User;
import com.example.securityservice.Mappers.RoleMapper;
import com.example.securityservice.Mappers.UserMapper;
import com.example.securityservice.Srvice.UserService;
import com.example.securityservice.exceptions.EntityAlreadyExistException;
import com.example.securityservice.exceptions.EntityNotFoundException;
import com.example.securityservice.exceptions.IncorrectPasswordException;
import com.example.securityservice.exceptions.InvalidEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private UserMapper userMapper;
    private RoleMapper roleMapper;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, UserMapper userMapper, RoleMapper roleMapper, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO signIn(UserRequestDto userRequestDto) {
         return saveUser(userRequestDto);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
          List<User>users = userDAO.findAll();
          return userMapper.modelToDtos(users);
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) throws EntityNotFoundException{
        Optional<User> user = Optional.ofNullable(userDAO.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("No user with email "+email+"were found")));
        return userMapper.modelToDto(user.get());
    }

    @Override
    public UserResponseDTO updatePassword(UpdatePasswordDTO updatePasswordDTO) throws EntityNotFoundException, IncorrectPasswordException{

           Optional<User> user = Optional.ofNullable(userDAO.findByEmail(updatePasswordDTO.getEmail()).orElseThrow(
                   () -> new EntityNotFoundException("No user with email " + updatePasswordDTO.getEmail() + "were found")));
           if(!updatePasswordDTO.getPassword().equals(user.get().getPassword())){
               throw new IncorrectPasswordException("password incorrect");
           }
            if (updatePasswordDTO.getNewPassword().equals(user.get().getPassword())) {
                throw new IncorrectPasswordException("New password cannot be the same as the current password");
            }
           user.get().setPassword(passwordEncoder.encode(updatePasswordDTO.getNewPassword()));
           User userUpdated = userDAO.save(user.get());
           return userMapper.modelToDto(userUpdated);
    }

    @Override
    public UserResponseDTO updateUser(UserRequestDto userRequestDto) {
       return saveUser(userRequestDto);
    }

    public UserResponseDTO saveUser(UserRequestDto userRequestDto){
        User user = userMapper.dtoToModel(userRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<String> roles = userRequestDto.getRoleNames();
        List<Role>roleList = new ArrayList<>();
        for( String role : roles){
            roleList.add(roleDAO.findByRole(role).get());
        }
        user.setRoles(roleList);
        User userUpdated= userDAO.save(user);
        return userMapper.modelToDto(userUpdated);
    }

    @Override
    public void deleteUser(Long id) {
        if(!userDAO.findById(id).isPresent())
            throw new EntityNotFoundException("No User with id "+id+" were found");
        userDAO.deleteById(id);
    }


    @Override
    public UserResponseDTO addRoleToUser(UserRequestDto userRequestDto, String role)throws EntityNotFoundException {
        if(!roleDAO.findByRole(role).isPresent())
            throw new EntityNotFoundException("No role with name "+role+" were found");
        userRequestDto.getRoleNames().add(role);
        List<Role> roles = new ArrayList<>();
        for( String rol : userRequestDto.getRoleNames()){
            roles.add(roleDAO.findByRole(rol).get());
        }
        User user = userMapper.dtoToModel(userRequestDto);
        user.setRoles(roles);
        return userMapper.modelToDto(
                userDAO.save(
                        user
                )
        );
    }
}
