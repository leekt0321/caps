package Capstone2.GrowthPlanner.user.service;


import Capstone2.GrowthPlanner.user.repository.MemberRepository;
import Capstone2.GrowthPlanner.user.repository.entitiy.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class EntityService {
    @Autowired
    private MemberRepository memberRepository;


    @Transactional
    public void addMember(Member member) {

        memberRepository.save(member); // DB에 저장
    }

    @Transactional
    public boolean authenticateUser(String id, String pw) {

        Member member = memberRepository.findById(id);

        if (member != null && member.getPw().equals(pw)) {
            return true; // 인증 성공
        }
        return false; // 인증 실패

    }

    public Member getMemberById(String id){
        return memberRepository.findById(id);
    }

}
