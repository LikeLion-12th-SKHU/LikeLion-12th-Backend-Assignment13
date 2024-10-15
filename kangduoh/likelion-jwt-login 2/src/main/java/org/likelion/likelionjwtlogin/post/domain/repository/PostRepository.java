package org.likelion.likelionjwtlogin.post.domain.repository;

import java.util.List;
import org.likelion.likelionjwtlogin.member.domain.Member;
import org.likelion.likelionjwtlogin.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByMember(Member member);

    @Query("SELECT p FROM Post p WHERE p.member = :member AND p.title LIKE %:titleKeyword% AND p.content LIKE %:contentKeyword%")
    List<Post> findByTitleAndContent(@Param("member") Member member,
                                     @Param("titleKeyword") String titleKeyword,
                                     @Param("contentKeyword") String contentKeyword);

    @Query("SELECT p FROM Post p WHERE p.member = :member ORDER BY LENGTH(p.content) DESC")
    List<Post> findLongestPost(@Param("member") Member member);
}
