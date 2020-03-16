package com.lukawski.payment.controller;

import com.lukawski.payment.dto.Payments;
import com.lukawski.payment.request.PaymentFromPeriod;
import com.lukawski.payment.request.PaymentUpdate;
import com.lukawski.payment.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
public class PaymentsController {

    @Autowired
    private PaymentsService paymentsService;

    @GetMapping(value = "/all")
    public List<Payments> getAllPayments(){
        return paymentsService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Payments findById(@PathVariable("id") Long id){
        return paymentsService.findById(id).orElse(null);
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        paymentsService.deleteById(id);
    }


    @PostMapping(value="/add")
    public Payments newEmployer( @RequestBody Payments newPayments ){
        return paymentsService.save(newPayments);
    }

    @GetMapping(value = "allPayments/{employerId}")
    public List<Payments> findByEmployerId(@PathVariable("employerId") Long employerId){
        return paymentsService.findByEmployerId(employerId);
    }

    @GetMapping(value = "allPayments2/{employerIds}")
    public List<Payments> findByEmployerId(@PathVariable("employerIds") List<Integer> employerIds){
        return paymentsService.findByEmployerIds(employerIds);
    }

    @PutMapping(value = "updatePayment")
    public int updateEmployerFromId(@RequestBody PaymentUpdate paymentUpdateRequest) {
        return paymentsService.updateEmployerPayment(
                paymentUpdateRequest.getPayment(),
                paymentUpdateRequest.getEmpId(),
                paymentUpdateRequest.getPaymentDate()
        );
    }

    @GetMapping(value = "getPaymentAverage/{employerId}")
    public Double getLastYearPaymentAverage(@PathVariable("employerId") Long employerIds){
        return paymentsService.getPaymentAverage(employerIds);
    }

    @PostMapping(value = "/getPaymentAverageInPeriod")
    public Double getPaymentAverageFromRange(@RequestBody PaymentFromPeriod paymentFromPeriodRequest){
        return paymentsService.getPaymentAverageFromRange(
                paymentFromPeriodRequest.getEmpId(),
                paymentFromPeriodRequest.getStartDate(),
                paymentFromPeriodRequest.getEndDate());
    }

}
