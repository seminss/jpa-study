package hellojpa;

import javax.persistence.Entity; //jpa가 사용하는 애구나 관리해야겠구나를 알려줌!
import javax.persistence.Id;

@Entity
public class Member {

    @Id
    private Long id;
    private String name;

    public Member() {
    }

    public Member(long id, String name) {
        setId(id);
        setName(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
