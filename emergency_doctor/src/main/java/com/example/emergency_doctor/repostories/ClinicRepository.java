package com.example.emergency_doctor.repostories;

import com.example.emergency_doctor.entities.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
}
