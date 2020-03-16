package com.lukawski.payment.service;

import com.lukawski.payment.dto.Employer;
import com.lukawski.payment.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//@Scope(value = "session")
//@Component(value="employeesService")
//@Component

//@Service
@Repository
@Transactional
public class EmployersService {

    @Autowired
    EmployerRepository employerRepository;

    public Optional<Employer> findById(Long id) {
        return employerRepository.findById(id);
    }

    public List<Employer> getAll() {
        return employerRepository.findAll();
    }

    public Employer save(Employer employer) {
        return employerRepository.save(employer);
    }

    public Employer update(Employer employer) {
        return employerRepository.save(employer);
    }

    public void delete(Employer employer) {
        employerRepository.delete(employer);
    }

    public void deleteById(Long id) {
        employerRepository.deleteById(id);
    }
}
