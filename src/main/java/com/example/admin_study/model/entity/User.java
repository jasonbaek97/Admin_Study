package com.example.admin_study.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor     // User(id,account,...)
@NoArgsConstructor      // User()
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private String password;
    private String status;
    private String email;
    private String phoneNumber;     // JPA에서 자동으로 phone_number 매핑
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

//    // 1:N
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")       // User : OrderDetail = 1:N, OrderDetail.user 로 매핑
//    private List<OrderDetail> orderDetailList;
}
