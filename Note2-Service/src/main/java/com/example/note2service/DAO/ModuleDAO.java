package com.example.note2service.DAO;

import com.example.note2service.Entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleDAO extends JpaRepository<Module, Integer> {
    Module findModulesByModuleNameLikeIgnoreCase(String name);
}
