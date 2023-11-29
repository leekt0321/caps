package Capstone2.GrowthPlanner.user.Diary;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import Capstone2.GrowthPlanner.user.repository.entitiy.Member;

@Getter
@Setter
@ToString
@Entity
@Table(name = "diary_table")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private Member user;

    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Diary() {
    }

    public Diary(Member user, String content) {
        this.user = user;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }
}
