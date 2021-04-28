package com.example.med_id.repositories;

import com.example.med_id.model.CustomerRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRelationRepo extends JpaRepository<CustomerRelation, Long> {
    @Query("FROM CustomerRelation WHERE isDelete = false AND lower(Name) LIKE lower(concat('%',?1,'%') ) ")
    List<CustomerRelation> SearchRelation(String keyword);

    @Query("FROM CustomerRelation WHERE isDelete = false")
    List<CustomerRelation> GetAvailable();
}
