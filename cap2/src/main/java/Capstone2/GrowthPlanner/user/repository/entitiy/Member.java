package Capstone2.GrowthPlanner.user.repository.entitiy;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity // 이 클래스가 JPA엔티티임을 표시. 테이블 생성
@Table(name = "user_table")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String u_id;
    private String u_pw ;
    private String name ;
    private Long age ;
    private float height ;
    private float weight ;
    private float BMI ;
    public Member(){

    }

    public Member(String id,String pw,String name,Long age,float height,float weight) {
        this.u_id=id;
        this.u_pw=pw;
        this.name=name;
        this.age=age;
        this.height=height;
        this.weight=weight;
    }
    // Getter와 Setter 메서드
}
