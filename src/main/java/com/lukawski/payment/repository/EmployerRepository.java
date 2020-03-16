package com.lukawski.payment.repository;

import com.lukawski.payment.dto.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer, Long> {

}
