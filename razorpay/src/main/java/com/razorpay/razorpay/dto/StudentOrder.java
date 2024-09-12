package com.razorpay.razorpay.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name ="student_orders")
public class StudentOrder {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer orderId;
    private String name;
    private  String email;
    private String phno;
    private String course;
    private Integer amount;
    private String orderStatus;
    private String razorpayOrderId;
}
