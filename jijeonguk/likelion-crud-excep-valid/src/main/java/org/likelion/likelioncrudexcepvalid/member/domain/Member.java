package org.likelion.likelioncrudexcepvalid.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.likelion.likelioncrudexcepvalid.member.api.dto.request.MemberUpdateReqDto;
import org.likelion.likelioncrudexcepvalid.post.domain.Post;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    private String name;
    private int age;

    private String email;

    @Enumerated(EnumType.STRING)
    private Part part;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> posts;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    private Member(String name, int age, String email, Part part) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.part = part;
    }

    public void update(MemberUpdateReqDto memberUpdateReqDto) {
        this.name = memberUpdateReqDto.name();
        this.age = memberUpdateReqDto.age();
        this.email = memberUpdateReqDto.email();
    }
}
