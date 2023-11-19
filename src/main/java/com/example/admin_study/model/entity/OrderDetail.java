package com.example.admin_study.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity     // order_detail
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"orderGroup"})        // lombok ToString() 을 통해서 User, OrderDetail 상호 참조된걸 제외
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private LocalDateTime arrivalDate;
    private Integer quantity;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Long itemId;

    // OrderDetail : OrderGroup = N : 1
    @ManyToOne
    private OrderGroup orderGroup;

}
