package org.likelion.likelioncrudexcepvalid.member.domain.repository;


import org.likelion.likelioncrudexcepvalid.member.domain.Member;
import org.likelion.likelioncrudexcepvalid.member.domain.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Page<Member> findAll(Pageable pageable);

//  여기서부터 과제

    // 일정 나이 이상인 사람들을 출력하는 레포지토리
    @Query("select distinct m from Member m where m.age >= :inputAge")
    List<Member> findByOverInputAge(Integer inputAge);

    @Query("select distinct m from Member m where m.part = :membersPart")
    List<Member> findByMembersPart(Part membersPart);
}