package com.busanit.jpashop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** 장바구니 | cart */

@Entity
@Table(name = "cart")
@Getter @Setter @ToString
@NoArgsConstructor
public class Cart {

    // 장바구니id
    @Id
    @Column(name = "cart_id")
    @GeneratedValue
    private Long id;

    // 일대일 단방향 mapping
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

}


