package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@Table(name="ORDERS")// db에 그냥 order은 예약어라 orders로
public class Order extends BaseEntity{

    @Id @GeneratedValue
    @Column(name="OREDR_ID")
    private Long id; //orderId

    @ManyToOne(fetch= LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @OneToOne(fetch= LAZY,cascade = ALL)
    @JoinColumn(name="DELIVERY_ID")//FK명을 적는거임
    private Delivery delivery;

    @OneToMany(mappedBy = "order",cascade = ALL)
    private List<OrderItem> orderItems=new ArrayList<>();
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    //양방향 연관관계 편의 메소드
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
