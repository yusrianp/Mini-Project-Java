package com.example.med_id.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.med_id.model.Location;

public interface LocationRepo extends JpaRepository<Location, Long> {
}
