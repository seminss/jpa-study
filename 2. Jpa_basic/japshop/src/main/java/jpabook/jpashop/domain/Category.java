package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Category extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    private String name;
    @ManyToOne(fetch= LAZY)//자식 입장에서는 부모가 one
    @JoinColumn(name="PARENT_ID")
    private Category parent;//상위 카테고리

    @OneToMany(mappedBy="parent")//셀프 매핑
    private List<Category> child=new ArrayList<>();

    //ITEM table이랑 조인을 하는데, 연결 테이블을 만들어서 한다.
    @ManyToMany
    @JoinTable(name="CATEGORY_ITEM",
        joinColumns = @JoinColumn(name="CATEGORY_ID"), //내꺼 FK
        inverseJoinColumns = @JoinColumn(name="ITEM_ID") )//반대편 FK
    private List<Item> items=new ArrayList<>();
}
