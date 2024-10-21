package org.likelion.likelioncrudexcepvalid.member.domain.repository;

import org.likelion.likelioncrudexcepvalid.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Page<Member> findAll(Pageable pageable);

    Page<Member> findTop3ByOrderByCreatedAtDesc(Pageable pageable);
}


