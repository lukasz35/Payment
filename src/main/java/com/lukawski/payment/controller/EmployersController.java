package com.lukawski.payment.controller;

import com.lukawski.payment.dto.Employer;
import com.lukawski.payment.service.EmployersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employers")
public class EmployersController {

    @Autowired
    private EmployersService service;

    @GetMapping(value = "/{id}")
    public Employer findById(@PathVariable("id") Long id){
        return service.findById(id).orElse(null);
    }

    @GetMapping(value = "/all")
    public List<Employer> findAll() {
        return service.getAll();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PostMapping
    public Employer newEmployer( @RequestBody Employer newEmployer ){
        return service.save(newEmployer);
    }

    @PutMapping("/{id}")
    Employer updateEmployer( @RequestBody Employer newEmployer, @PathVariable Long id){
        return service.findById(id).map(
                employer -> {
                    employer.setName(newEmployer.getName());
                    employer.setSurname(newEmployer.getSurname());
                    return service.save(employer);
                }).orElse(null);
    }


}
