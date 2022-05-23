package jpabook.jpashop.repository;

import jpabook.jpashop.domain.OrderStatus;

public class OrderSearch {
    private String memberName;
    private static OrderStatus orderStatus;


    //Getter, Setter
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public static OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

}
