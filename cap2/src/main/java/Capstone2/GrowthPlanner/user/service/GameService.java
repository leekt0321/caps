package Capstone2.GrowthPlanner.user.service;

import Capstone2.GrowthPlanner.user.repository.GameRepository;
import Capstone2.GrowthPlanner.user.repository.entitiy.Game;
import org.springframework.stereotype.Service;

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
