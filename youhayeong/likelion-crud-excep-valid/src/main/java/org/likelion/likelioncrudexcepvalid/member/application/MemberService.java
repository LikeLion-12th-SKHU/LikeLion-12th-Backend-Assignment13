package org.likelion.likelioncrudexcepvalid.member.application;

import org.likelion.likelioncrudexcepvalid.common.error.ErrorCode;
import org.likelion.likelioncrudexcepvalid.common.exception.NotFoundException;
import org.likelion.likelioncrudexcepvalid.member.api.dto.request.MemberSaveReqDto;
import org.likelion.likelioncrudexcepvalid.member.api.dto.request.MemberUpdateReqDto;
import org.likelion.likelioncrudexcepvalid.member.api.dto.response.MemberInfoResDto;
import org.likelion.likelioncrudexcepvalid.member.api.dto.response.MemberListResDto;
import org.likelion.likelioncrudexcepvalid.member.domain.Member;
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
    @Transactional // void -> memberInfoResDto
    public MemberInfoResDto memberSave(MemberSaveReqDto memberSaveReqDto) {
        Member member = Member.builder()
                .name(memberSaveReqDto.name())
                .age(memberSaveReqDto.age())
                .email(memberSaveReqDto.email())    // 추가
                .part(memberSaveReqDto.part())
                .build();

        memberRepository.save(member);
        return MemberInfoResDto.from(member); // 추가
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
                        ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION.getMessage() + memberId)  //수정
        );

        return MemberInfoResDto.from(member);
    }

    // Update
    @Transactional
    public MemberInfoResDto memberUpdate(Long memberId, MemberUpdateReqDto memberUpdateReqDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException
                (ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION, ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION.getMessage() + memberId)  // 수정
        );

        member.update(memberUpdateReqDto);
        return MemberInfoResDto.from(member);   // 추가
    }

    // Delete
    @Transactional
    public MemberInfoResDto memberDelete(Long memberId) {
            Member member = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException
                    (ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION, ErrorCode.INTERNAL_SERVER_ERROR.getMessage() + memberId));  // 수정

        memberRepository.delete(member);
        return MemberInfoResDto.from(member);   // 추가
    }

}