package com.example.admin_study.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor     // User(id,account,...)
@NoArgsConstructor      // User()
@Entity
@EntityListeners(AuditingEntityListener.class)      // @CreatedBy @LastModifiedBy 설정값 연동
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
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

    // User : OrderGroup = 1: N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")       // mappedBy는 OrderGroup user 매핑
    private List<OrderGroup> orderGroup;

}
