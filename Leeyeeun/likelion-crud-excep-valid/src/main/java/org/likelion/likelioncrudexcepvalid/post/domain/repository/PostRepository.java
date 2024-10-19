package org.likelion.likelioncrudexcepvalid.post.domain.repository;

import org.likelion.likelioncrudexcepvalid.member.domain.Member;
import org.likelion.likelioncrudexcepvalid.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByMember(Member member);

    @Query("select p from Post p where p.title like %:title%")
    List<Post> findByTitle(@Param("title") String title);

    @Query("select p from Post p where p.member.name like %:name%")
    List<Post> findByMemberName(@Param("name") String name);

    @Query("select p from Post p where p.contents like %:contents%")
    List<Post> findByContents(@Param("contents") String contents);

}
