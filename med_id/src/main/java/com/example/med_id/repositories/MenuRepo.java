package com.example.med_id.repositories;

import com.example.med_id.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepo extends JpaRepository<Menu, Long> {
    @Query("FROM Menu WHERE ParentId = null")
    List<Menu> GetAvailable();
}
