package org.likelion.likelioncrudexcepvalid.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelioncrudexcepvalid.member.api.dto.request.MemberUpdateReqDto;
import org.likelion.likelioncrudexcepvalid.post.domain.Post;

import java.util.ArrayList;
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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

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