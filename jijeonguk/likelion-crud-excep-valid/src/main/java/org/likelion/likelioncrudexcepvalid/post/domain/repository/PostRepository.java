package org.likelion.likelioncrudexcepvalid.post.domain.repository;

import org.likelion.likelioncrudexcepvalid.member.domain.Member;
import org.likelion.likelioncrudexcepvalid.member.domain.Part;
import org.likelion.likelioncrudexcepvalid.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByMember(Member member);


    @Query("SELECT m FROM Member m ORDER BY m.createdAt DESC")
    List<Member> findTop3ByOrderByCreatedAtDesc(Pageable pageable);

    @Query("SELECT p FROM Post p ORDER BY p.createdAt DESC")
    Page<Post> findTop5(Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.contents LIKE %:keyword%")
    List<Post> findByTitle(@Param("keyword") String keyword);
}