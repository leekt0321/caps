package Capstone2.GrowthPlanner.user.service;

import Capstone2.GrowthPlanner.user.repository.GameRepository;
import Capstone2.GrowthPlanner.user.repository.entitiy.Game;
import Capstone2.GrowthPlanner.user.repository.entitiy.Member;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game getGameInfo(Long memberSeq){
        return gameRepository.findByMemberSeq(memberSeq);
    }
}
