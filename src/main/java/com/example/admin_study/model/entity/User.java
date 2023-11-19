package com.example.admin_study.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor     // User(id,account,...)
@NoArgsConstructor      // User()
@Entity
@ToString(exclude = {"orderGroup"})     // 상호참조되는 OrderGroup은 ToString 제외
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

    // User : OrderGroup = 1: N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")       // mappedBy는 OrderGroup user 매핑
    private List<OrderGroup> orderGroup;

}
