package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy="member") //order table에 있는 member 필드에 의해서 매핑 된거야!
    //이 값이 변경된다고 해서 fk 값이 변경되지는 않는다.
    private List<Order> orders = new ArrayList<>();

}
