package hellojpa;

import javafx.util.converter.LocalDateTimeStringConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@TableGenerator(
        name="MEMBER_SEQ_GENERATOR",
        table ="MY_SEQUENCES", //매핑할 데이터베이스 시퀀스 이름
        pkColumnName = "MEMBER_SEQ", allocationSize = 1)
public class Member {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,
                    generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name="name", nullable=false)
    private String username;

    public Member(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}