package Capstone2.GrowthPlanner.user.repository.entitiy;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity // 이 클래스가 JPA엔티티임을 표시. 테이블 생성
@Table(name = "login")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long age ;
    public Member(){

    }

    public Member(String name,Long age, String sex, float height,float weigth) {
        this.name=name;
        this.age=age;

    }
    // Getter와 Setter 메서드
}
