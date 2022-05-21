package com.example.emergency_doctor.repostories;

import com.example.emergency_doctor.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String s);
}

