package com.example.med_id.repositories;

import com.example.med_id.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role, Long> {
    @Query("FROM Role WHERE isDelete = false AND lower(Name) LIKE lower(concat('%',?1,'%') ) ")
    List<Role> SearchRole(String keyword);

    @Query("FROM Role WHERE isDelete = false")
    List<Role> GetAvailable();

    @Query(" SELECT f FROM Role f WHERE isDelete = false")
    Page<Role> GetAvailablePage(Pageable pageable);
}
