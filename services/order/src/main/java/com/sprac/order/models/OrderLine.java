package com.sprac.order.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
@Entity
@Table(name = "customer_line")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    private Integer productId;
    private double quantity;

}
