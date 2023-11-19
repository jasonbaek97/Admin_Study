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

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity     // order_detail
@EntityListeners(AuditingEntityListener.class)      // @CreatedBy @LastModifiedBy 설정값 연동
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"orderGroup","item"})        // lombok ToString() 을 통해서 User, OrderDetail 상호 참조된걸 제외
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private LocalDateTime arrivalDate;
    private Integer quantity;
    private BigDecimal totalPrice;
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;


    // OrderDetail : OrderGroup = N : 1
    @ManyToOne
    private OrderGroup orderGroup;

    // OrerDetail : Item = N : 1
    @ManyToOne
    private Item item;
}
