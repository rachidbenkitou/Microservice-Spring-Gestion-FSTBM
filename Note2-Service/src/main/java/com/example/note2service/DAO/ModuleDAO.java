package com.example.note2service.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleDAO extends JpaRepository<Module, Integer> {
}
