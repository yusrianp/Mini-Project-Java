package com.example.med_id.repositories;

import com.example.med_id.model.Biodata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiodataRepo extends JpaRepository<Biodata, Long> {

}
