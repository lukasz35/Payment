package com.lukawski.payment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="employees", schema = "public")
public class Employer implements Serializable {

    private static final long serialVersionUID = 6789151069394702028L;

    public Employer() {
    }

    @Id
    @Column(name="emp_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="emp_name", nullable = false)
    private String name;

    @Column(name="emp_surname", nullable = false)
    private String surname;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name="sal_emp_id", referencedColumnName = "emp_id")
//    @OrderBy("paymentDate")
//    private List<Payments> payments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
//
//    public List<Payments> getPayments() {
//        return payments;
//    }
//
//    public void setPayments(List<Payments> payments) {
//        this.payments = payments;
//    }
}
