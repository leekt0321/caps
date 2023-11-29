package Capstone2.GrowthPlanner.user.repository.entitiy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user_table")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String id;
    private String pw;
    private String name;
    private Long age;
    private Float height;
    private Float weight;
    private float BMI;

    public Member() {
    }

    public Member(String id, String pw, String name, Long age, Float height, Float weight) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    // equals, hashCode 메서드 추가
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(seq, member.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq);
    }
}
