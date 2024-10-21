package org.likelion.likelioncrudexcepvalid.member.domain.repository;

import org.likelion.likelioncrudexcepvalid.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Page<Member> findAll(Pageable pageable);

    @Query("select distinct m from Member m where m.name like :name ")
    List<Member> findByName(String name);

    @Query("select distinct m from Member m where m.age > :age ")
    List<Member> findByOverAge(int age);

    @Query("select distinct m from Member m where m.name like :input ")
    List<Member> findByEmail(String input);
}