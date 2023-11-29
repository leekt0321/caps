package Capstone2.GrowthPlanner.user.repository.entitiy;


import Capstone2.GrowthPlanner.user.repository.entitiy.Member;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "game_table")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    @OneToOne
    @JoinColumn(name = "user_seq")
    private Member member;
    private Long level;
    private Long HP;
    private Long STR;
    private Long DEX;
    private Long patient;
    private Long LUK;
    private boolean att_check;



}
