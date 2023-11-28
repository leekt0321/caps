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
    private String id;
    private String pw ;
    private String name ;
    private Long age ;
    private Float height ;
    private Float weight ;
    private float BMI ;
    private Float goal;
    public Member(){

    }

    public Member(String id,String pw,String name,Long age,Float height,Float weight,Float goal) {
        this.id=id;
        this.pw=pw;
        this.name=name;
        this.age=age;
        this.height=height;
        this.weight=weight;
        this.goal=goal;
    }
    // Getter와 Setter 메서드
    /*
    * private String u_id;
    * public String getU_id(){
    *  return u_id;
    * }
    *
    * public void setU_id(String u_id){
    *   this.u_id=u_id;
    * }
    * */
}
