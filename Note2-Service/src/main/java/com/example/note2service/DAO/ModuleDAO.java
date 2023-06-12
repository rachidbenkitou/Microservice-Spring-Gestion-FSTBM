package com.example.note2service.DAO;

import com.example.note2service.Entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleDAO extends JpaRepository<Module, Integer> {
    List<Module> findModulesByModuleNameLikeIgnoreCase(String name);
    void deleteByModuleName(String name);
    boolean existsByModuleName(String name);
    List<Module> getModulesByCinEnseignant(String cinEnseigant);
}
