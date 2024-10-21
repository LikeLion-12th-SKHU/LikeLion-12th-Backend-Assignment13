package org.likelion.likelioncrudexcepvalid.post.domain.repository;

import org.likelion.likelioncrudexcepvalid.member.domain.Member;
import org.likelion.likelioncrudexcepvalid.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByMember(Member member);

    @Query("select distinct p from Post p where p.title like :input or p.contents like :input")
    List<Post> findByInput(@Param(value = "input") String input);

    @Query("select distinct p from Post p join fetch p.member m where m.name = :name")
    List<Post> findByName(@Param(value = "name") String name);

    @Query("select distinct p from Post p join fetch p.member m where m.email = :email")
    List<Post> findByEmail(@Param(value = "email") String email);

}
