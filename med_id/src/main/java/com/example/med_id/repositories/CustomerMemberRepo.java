package com.example.med_id.repositories;

import com.example.med_id.model.CustomerMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerMemberRepo extends JpaRepository<CustomerMember, Long> {
    @Query("FROM CustomerMember WHERE isDelete = false AND ParentBiodataId=?1")
    List<CustomerMember> GetAvailable(Long id);
}
