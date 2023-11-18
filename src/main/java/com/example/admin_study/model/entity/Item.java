package com.example.admin_study.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String name;
    private String title;
    private String content;
    private Integer price;
    private String brandName;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Long partnerId;

    // 1: N
    // LAZY = 지연로딩, EAGER = 즉시로딩
    // LAZY = 1:N
    // LAZY = select * from item where id =?

    /* EAGER = 1 :1
        연관된 모든 테이블에 join 발생하므로, 테이블 관계 1:1 인 경우만
     item_id = order_detail.item_id
     user_id = order_detail.user_id
    */
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
//    private List<OrderDetail> orderDetailList;


}
