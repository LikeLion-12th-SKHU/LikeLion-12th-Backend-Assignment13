package org.likelion.likelioncrudexcepvalid.post.domain.repository;

import org.likelion.likelioncrudexcepvalid.member.domain.Member;
import org.likelion.likelioncrudexcepvalid.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByMember(Member member);


    // 과제

    // 제목 조회
    @Query("select distinct p from Post p where p.title like :title")
    List<Post> findByTitle(@Param(value = "title") String title);

    // 작성자명 조회
    @Query("select distinct p from Post p join fetch p.member m where m.name = :writer ")
    List<Post> findByWriter(String writer);

    // 내용 조회
    @Query("select distinct p from Post p where p.contents like :contents")
    List<Post> findByContents(@Param("contents") String contents);
}
