package com.example.med_id.repositories;

import com.example.med_id.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query("FROM User WHERE Email = ?1")
    Optional<User> FindByEmail(String email);

    @Query(value = "SELECT * FROM m_user ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<User> findLastId();

    @Query("FROM User u INNER JOIN Role r ON u.RoleId = r.Id WHERE u.Email =  ?1")
    User FindEmail(String email);
}
