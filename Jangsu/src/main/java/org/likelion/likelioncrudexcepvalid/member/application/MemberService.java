package org.likelion.likelioncrudexcepvalid.member.application;

import org.likelion.likelioncrudexcepvalid.common.Exception.NotFoundException;
import org.likelion.likelioncrudexcepvalid.common.error.ErrorCode;
import org.likelion.likelioncrudexcepvalid.member.api.dto.request.MemberSaveReqDto;
import org.likelion.likelioncrudexcepvalid.member.api.dto.request.MemberUpdateReqDto;
import org.likelion.likelioncrudexcepvalid.member.api.dto.response.MemberInfoResDto;
import org.likelion.likelioncrudexcepvalid.member.api.dto.response.MemberListResDto;
import org.likelion.likelioncrudexcepvalid.member.domain.Member;
import org.likelion.likelioncrudexcepvalid.member.domain.Part;
import org.likelion.likelioncrudexcepvalid.member.domain.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Create
    @Transactional
    public MemberInfoResDto memberSave(MemberSaveReqDto memberSaveReqDto) {
        Member member = Member.builder()
                .name(memberSaveReqDto.name())
                .age(memberSaveReqDto.age())
                .email(memberSaveReqDto.email())
                .part(memberSaveReqDto.part())
                .build();

        memberRepository.save(member);
        return MemberInfoResDto.from(member);
    }

    // Read All
    public MemberListResDto memberFindAll(Pageable pageable) {
        Page<Member> members = memberRepository.findAll(pageable);

        List<MemberInfoResDto> memberInfoResDtoList = members.stream()
                .map(MemberInfoResDto::from)
                .collect(Collectors.toList());

        return MemberListResDto.from(memberInfoResDtoList);
    }

    // Read One
    public MemberInfoResDto memberFindOne(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION.getMessage()
                                + memberId));

        return MemberInfoResDto.from(member);
    }

    // Update
    @Transactional
    public MemberInfoResDto memberUpdate(Long memberId, MemberUpdateReqDto memberUpdateReqDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION.getMessage()
                                + memberId)
        );

        member.update(memberUpdateReqDto);
        return MemberInfoResDto.from(member);
    }

    // Delete
    @Transactional
    public MemberInfoResDto memberDelete(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION.getMessage()
                                + memberId)
        );

        memberRepository.delete(member);
        return MemberInfoResDto.from(member);
    }

// 여기서부터 과제

    // 입력된 나이 이상을 조회
    public MemberListResDto memberFindAgeOverInput(Integer searchInput) {
        List<Member> posts = memberRepository.findByOverInputAge(searchInput);

        List<MemberInfoResDto> memberInfoResDto = posts.stream()
                .map(MemberInfoResDto::from)
                .toList();

        return MemberListResDto.from(memberInfoResDto);
    }

    // 입력된 맴버의 파트를 조회
    public MemberListResDto memberFindPart(String memberPart) {
        Part searchInput = Part.valueOf(memberPart);
        List<Member> membersParts = memberRepository.findByMembersPart(searchInput);

        List<MemberInfoResDto> memberInfoResDto = membersParts.stream()
                .map(MemberInfoResDto::from)
                .toList();

        return MemberListResDto.from(memberInfoResDto);
    }
}