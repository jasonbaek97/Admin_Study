package com.example.admin_study.model.entity;

import com.example.admin_study.model.enumclass.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
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
@ToString(exclude = {"orderGroup"})                 // 상호참조되는 OrderGroup은 ToString 제외
@Builder                                            // lombok builder 생성자 패턴
@Accessors(chain = true)                            // lombok chain 패턴 지원 ( set을 .연산자로 연결해서 지정 )
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus status;           // REGISTERED / UNREGISTERED
    private String email;
    private String phoneNumber;         // JPA에서 자동으로 phone_number 매핑
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
