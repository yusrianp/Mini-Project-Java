package com.example.med_id.repositories;

import com.example.med_id.model.CustomerCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerCardRepo extends JpaRepository<CustomerCard, Long> {
    @Query("FROM CustomerCard WHERE CustomerId = ?1")
    Optional<CustomerCard> GetCustomerCardByCustomerID(Long id);
}
