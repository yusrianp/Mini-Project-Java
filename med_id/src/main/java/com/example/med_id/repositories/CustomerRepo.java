package com.example.med_id.repositories;

import com.example.med_id.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
@Query("FROM Customer WHERE BiodataId = ?1 ")
    Optional<Customer> GetCustomerByBiodataId(Long id);
    
}
