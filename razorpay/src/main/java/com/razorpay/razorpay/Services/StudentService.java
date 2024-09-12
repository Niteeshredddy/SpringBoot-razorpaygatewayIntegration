package com.razorpay.razorpay.Services;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.razorpay.dto.StudentOrder;
import com.razorpay.razorpay.repo.StudentOrderRepository;

@Service
public class StudentService {

    @Autowired
    private StudentOrderRepository studentRepo;

    @Value("${razorpay.key.id}")
    private String razorPayKey;

    @Value("${razorpay.secret.key}")
    private String razorPaySecret;

    private RazorpayClient client;

    public StudentOrder createOrder(StudentOrder StuOrder) throws Exception{

        JSONObject orderReq = new JSONObject();
        
        orderReq.put("amount",StuOrder.getAmount()*100);
        orderReq.put("currency","INR");
        orderReq.put("receipt",StuOrder.getEmail());

        this.client = new RazorpayClient(razorPayKey,razorPaySecret);

        Order razorPayOrder = client.orders.create(orderReq);

        StuOrder.setRazorpayOrderId(razorPayOrder.get("id"));
        StuOrder.setOrderStatus(razorPayOrder.get("status"));

        studentRepo.save(StuOrder);

        return StuOrder;
    }

    public StudentOrder updatedOrder(Map<String,String> responsePayLoad){
        String razorPayOrderId = responsePayLoad.get("razorpay_order_id");

        StudentOrder order = studentRepo.findByRazorpayOrderId(razorPayOrderId);
        order.setOrderStatus("PAYMENT_COMPLETED");

       StudentOrder updatedOrder= studentRepo.save(order);
       return updatedOrder;
    }
    
}
