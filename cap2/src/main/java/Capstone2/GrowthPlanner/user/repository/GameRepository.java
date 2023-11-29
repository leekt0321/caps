package Capstone2.GrowthPlanner.user.repository;

import Capstone2.GrowthPlanner.user.repository.entitiy.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByMemberSeq(Long memberSeq);
}
