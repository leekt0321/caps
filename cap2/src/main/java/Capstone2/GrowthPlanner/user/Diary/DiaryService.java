package Capstone2.GrowthPlanner.user.Diary;

import Capstone2.GrowthPlanner.user.repository.MemberRepository;
import Capstone2.GrowthPlanner.user.repository.entitiy.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private MemberRepository memberRepository;



    public List<Diary> getUserDiaries(String userId) {
        Member user = memberRepository.findById(userId);
        if (user != null) {
            return diaryRepository.findByUser(user);
        }
        return Collections.emptyList();
    }

    public void saveDiary(Member user, String content) {
        Diary diary = new Diary(user, content);
        diaryRepository.save(diary);
    }

    // 기타 필요한 메서드는 여기에 추가됩니다.
}
