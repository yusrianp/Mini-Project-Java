package com.example.med_id.repositories;

import com.example.med_id.model.MenuRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRoleRepo extends JpaRepository<MenuRole, Long> {
    @Query("FROM MenuRole WHERE isDelete = false")
    List<MenuRole> GetAvailable();
}
