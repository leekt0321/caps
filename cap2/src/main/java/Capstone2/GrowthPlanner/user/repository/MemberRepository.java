package Capstone2.GrowthPlanner.user.repository;

import Capstone2.GrowthPlanner.user.repository.entitiy.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> { // Member(엔티티)와 관련된 CRUD작업을 수행.
Member findById(String id);
}
