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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)      // @CreatedBy @LastModifiedBy 설정값 연동
@ToString(exclude = {"orderDetailList","partner"})
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
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;


    // Item : Partner = N : 1
    @ManyToOne
    private Partner partner;

    // Item : OrderDetail =  1: N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;

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
