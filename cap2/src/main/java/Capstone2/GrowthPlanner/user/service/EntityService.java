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
        public void addMember(Member member){

            memberRepository.save(member); // DB에 저장
    }

}
