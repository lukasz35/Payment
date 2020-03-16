package com.lukawski.payment.repository;

import com.lukawski.payment.dto.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {

    @Query("FROM Payments WHERE empId = ?1")
    List<Payments> findByEmployerId(Long empId);

    @Query("FROM Payments WHERE empId in :employerIds")
    List<Payments> findByEmployerIds(List<Long> employerIds);

    @Modifying
    @Query("UPDATE Payments p SET p.payment=?1 WHERE p.empId=?2 AND p.paymentDate=?3")
    int updateEmployerPayment(Double payment, Long empId, Date paymentDate);


    @Query(
            value = "select AVG(pay_payment) from payments where pay_emp_id=?1 and pay_date BETWEEN current_date - interval '12 months' and current_date",
            nativeQuery = true
    )
    Double getPaymentAverage(Long empId);

    @Query(
            value = "select AVG(pay_payment) from payments where pay_emp_id=?1 and pay_date BETWEEN ?2 and ?3",
            nativeQuery = true
    )
    Double getPaymentAverageFromRange(Long empId, Date startDate, Date endDate);
}
