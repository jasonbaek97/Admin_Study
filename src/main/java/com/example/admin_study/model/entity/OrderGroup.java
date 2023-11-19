package com.example.admin_study.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)      // @CreatedBy @LastModifiedBy 설정값 연동
@Data
@ToString(exclude = {"user","orderDetailList"})       // 상호 참조되는 변수는 ToString 제외
@Builder                                            // lombok builder 생성자 패턴
@Accessors(chain = true)                            // lombok chain 패턴 지원 ( set을 .연산자로 연결해서 지정 )
public class OrderGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private String orderType;
    private String revAddress;
    private String revName;
    private String paymentType;
    private BigDecimal totalPrice;
    private Integer totalQuantity;
    private LocalDateTime orderAt;
    private LocalDateTime arrivalDate;
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

    // OrderGroup : User = N : 1
    @ManyToOne
    private User user;

    // OrderGroup : OrderDetail  = 1: N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;
}
