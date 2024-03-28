package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// JPA 엔티티임을 나타내는 어노테이션
@Entity
public class Member {

    // 엔티티의 식별자를 나타내는 필드
    @Id
    // 자동 생성되는 식별자 값임을 나타내는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 회원의 이름을 나타내는 필드
    private String name;

    // 아래는 각 필드에 대한 Getter 및 Setter 메소드

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
