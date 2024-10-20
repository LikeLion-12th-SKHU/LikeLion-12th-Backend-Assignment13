package org.likelion.likelioncrudexcepvalid.post.domain.repository;

import org.likelion.likelioncrudexcepvalid.member.domain.Member;
import org.likelion.likelioncrudexcepvalid.member.domain.Part;
import org.likelion.likelioncrudexcepvalid.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByMember(Member member);


    @Query("select distinct p from Post p where p.title like :input or p.contents like :input ")
    List<Post> findByInput(String input);

    @Query("select distinct p from Post p join fetch p.member m where m.name = :name ")
    List<Post> findByName(String name);

    @Query("select distinct p from Post p join fetch p.member m where m.email = :email")
    List<Post> findByEmail(String email);


}
