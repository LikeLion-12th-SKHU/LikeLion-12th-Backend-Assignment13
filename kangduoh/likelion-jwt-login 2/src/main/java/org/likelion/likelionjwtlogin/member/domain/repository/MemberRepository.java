package org.likelion.likelionjwtlogin.member.domain.repository;

import org.likelion.likelionjwtlogin.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT m FROM Member m WHERE m.email LIKE %:domain% AND m.email != :email")
    List<Member> findByDomainExceptSelf(@Param("email") String email,
                                        @Param("domain") String domain);
}
