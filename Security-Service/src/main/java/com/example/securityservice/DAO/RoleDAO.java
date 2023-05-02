package com.example.securityservice.DAO;

import com.example.securityservice.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDAO extends JpaRepository<Role,Long> {
    Optional<Role> findByRole(String Role);
}
