package com.likelion.oauth2test.feed.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.oauth2test.feed.domain.Feed;
import com.likelion.oauth2test.user.domain.User;
import org.springframework.data.jpa.repository.Query;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    List<Feed> findAllByUser(User user);

    @Query("""
    select distinct f
    from Feed f join fetch f.user u
	where u.name like :userName """)
	List<Feed> findByUserName(String userName);

    @Query("""
    select distinct f
    from Feed f
	where f.title like :feedName """)
    Optional<Feed> findByFeedName(String feedName);

    @Query("""
    select distinct f
    from Feed f
	where f.image like :imageName """)
    Optional<Feed> findByImageName(String imageName);


}
