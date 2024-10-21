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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 맴버 저장
    @Transactional
    public MemberInfoResDto memberSave(MemberSaveReqDto memberSaveReqDto) {
        Member member = Member.builder()
                .name(memberSaveReqDto.name())
                .age(memberSaveReqDto.age())
                .email(memberSaveReqDto.email())
//                .part(memberSaveReqDto.part())
                .build();

        memberRepository.save(member);
        return MemberInfoResDto.from(member);
    }

    // 맴버 전체 조회
    public MemberListResDto memberFindAll(Pageable pageable) {
        Page<Member> members = memberRepository.findAll(pageable);

        List<MemberInfoResDto> memberInfoResDtoList = members.stream()
                .map(MemberInfoResDto::from)
                .collect(Collectors.toList());

        return MemberListResDto.from(memberInfoResDtoList);
    }

    // 맴버 개별 조회
    public MemberInfoResDto memberFindOne(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION,
                ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION.getMessage() + memberId)
        );

        return MemberInfoResDto.from(member);
    }

    // 맴버 검색(이름) 조회
    public MemberListResDto memberFindByName(String input) {
        String searchInput = "%" + input + "%";
        List<Member> members = memberRepository.findByName(searchInput);

        List<MemberInfoResDto> memberInfoResDtoList = members.stream()
                .map(MemberInfoResDto::from)
                .collect(Collectors.toList());

        return MemberListResDto.from(memberInfoResDtoList);
    }

    // 맴버 검색(나이) 조회
    public MemberListResDto memberFindByOver18(int age) {
        List<Member> members = memberRepository.findByOverAge(age);

        List<MemberInfoResDto> memberInfoResDtoList = members.stream()
                .map(MemberInfoResDto::from)
                .collect(Collectors.toList());

        return MemberListResDto.from(memberInfoResDtoList);
    }

    // 맴버 검색(이메일) 조회
    public MemberListResDto memberFindByEmail(String email) {
        String searchInput = "%" + email + "%";
        List<Member> members = memberRepository.findByEmail(searchInput);

        List<MemberInfoResDto> memberInfoResDtoList = members.stream()
                .map(MemberInfoResDto::from)
                .collect(Collectors.toList());

        return MemberListResDto.from(memberInfoResDtoList);
    }

    // 맴버 수정
    @Transactional
    public MemberInfoResDto memberUpdate(Long memberId, MemberUpdateReqDto memberUpdateReqDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION.getMessage() + memberId)
        );

        member.update(memberUpdateReqDto);
        return MemberInfoResDto.from(member);
    }

    // 맴버 삭제
    @Transactional
    public MemberInfoResDto memberDelete(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION.getMessage() + memberId)
        );

        memberRepository.delete(member);
        return MemberInfoResDto.from(member);
    }

}