package com.epam.xiao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Data
@Table(name = "order_tbl")
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "person_id",length = 32)
    private String personId;

    @Column(name = "order_name", length = 50)
    private String orderName;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "points")
    private BigDecimal points;
}
