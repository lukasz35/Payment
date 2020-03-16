package com.lukawski.payment.service;

import com.lukawski.payment.dto.Payments;
import com.lukawski.payment.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Service
@Repository
@Transactional
public class PaymentsService {

    @Autowired
    PaymentsRepository paymentsRepository;


    public Optional<Payments> findById(Long id) {
        return paymentsRepository.findById(id);
    }

    public List<Payments> findAll() {
        return paymentsRepository.findAll();
    }

    public Payments save(Payments payments) {
        return paymentsRepository.save(payments);
    }

    public Payments update(Payments payments) {
        return paymentsRepository.save(payments);
    }

    public void delete(Payments payments) {
        paymentsRepository.delete(payments);
    }

    public void deleteById(Long id) {
        paymentsRepository.deleteById(id);
    }

    public List<Payments> findByEmployerId(Long empId){
        return paymentsRepository.findByEmployerId(empId);
    }

    public List<Payments> findByEmployerIds(List<Integer> employerIds){
        return paymentsRepository.findByEmployerIds(employerIds.stream().mapToLong(Integer::longValue).boxed().collect(Collectors.toList()));
    }

    public int updateEmployerPayment(Double payment, Long empId, Date paymentDate){
        return paymentsRepository.updateEmployerPayment(payment, empId, paymentDate);
    }

    public Double getPaymentAverage(Long empId) {
        return paymentsRepository.getPaymentAverage(empId);
    }

    public Double getPaymentAverageFromRange(Long empId, Date startDate, Date endDate){
        return paymentsRepository.getPaymentAverageFromRange(empId, startDate, endDate);
    }
}
