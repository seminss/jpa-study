package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="ORDERS")// db에 그냥 order은 예약어라 orders로
public class Order {

    @Id @GeneratedValue
    @Column(name="OREDR_ID")
    private Long id; //orderId

    @Column(name="MEMBER_ID")
    private Long memberId;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
