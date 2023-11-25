package Capstone2.GrowthPlanner.user.service;


import Capstone2.GrowthPlanner.user.repository.MemberRepository;
import Capstone2.GrowthPlanner.user.repository.entitiy.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EntityService {
    @Autowired
    private MemberRepository memberRepository;

    //public List<Member> findLoginsByUsername(String username) {
    //    return MemberRepository.findByUsername(username);
    //}
    @Transactional
    public void addMember(Member member) {

        memberRepository.save(member); // DB에 저장
    }

    @Transactional
    public boolean authenticateUser(String id, String pw) {
        // Id로 사용자를 찾습니다.
        Member member = memberRepository.findById(id);

        // 사용자가 존재하고 비밀번호가 일치하는지 확인합니다.
        if (member != null && member.getPw().equals(pw)) {
            return true; // 인증 성공
        }
        return false; // 인증 실패

    }
}
