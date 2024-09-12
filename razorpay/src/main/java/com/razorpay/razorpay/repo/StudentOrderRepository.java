package com.razorpay.razorpay.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.razorpay.razorpay.dto.StudentOrder;

@Repository
public interface  StudentOrderRepository extends JpaRepository<StudentOrder, Integer> {
    public StudentOrder findByRazorpayOrderId(String order);
}
