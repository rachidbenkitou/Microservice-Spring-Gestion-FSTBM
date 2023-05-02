package com.example.securityservice.web;

import com.example.securityservice.DTO.RoleRequestDto;
import com.example.securityservice.DTO.RoleResponseDTO;
import com.example.securityservice.DTO.UserRequestDto;
import com.example.securityservice.DTO.UserResponseDTO;
import com.example.securityservice.Srvice.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public RoleResponseDTO addRole(@RequestBody RoleRequestDto roleRequestDto) {
        return roleService.addRole(roleRequestDto);
    }
    @GetMapping("/{role}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public RoleResponseDTO getRoleByName(@PathVariable String role) {
        return roleService.getRoleByName(role);
    }
    @DeleteMapping ("/{role}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void deleteRole(@PathVariable String role) {
        roleService.deleteRole(role);
    }
    @DeleteMapping("/{userId}/{role}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public UserResponseDTO delteRoleFromUser(@PathVariable Long userId, @PathVariable String role) {
        return roleService.delteRoleFromUser(userId, role);
    }
}
