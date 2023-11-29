package Capstone2.GrowthPlanner.user.Diary;

import org.springframework.data.jpa.repository.JpaRepository;
import Capstone2.GrowthPlanner.user.repository.entitiy.Member;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUser(Member user);


}
