package org.likelion.likelioncrudexcepvalid.member.api.dto;

import jakarta.validation.Valid;
import org.likelion.likelioncrudexcepvalid.common.dto.BaseResponse;
import org.likelion.likelioncrudexcepvalid.common.error.SuccessCode;
import org.likelion.likelioncrudexcepvalid.member.api.dto.request.MemberSaveReqDto;
import org.likelion.likelioncrudexcepvalid.member.api.dto.request.MemberUpdateReqDto;
import org.likelion.likelioncrudexcepvalid.member.api.dto.response.MemberInfoResDto;
import org.likelion.likelioncrudexcepvalid.member.api.dto.response.MemberListResDto;
import org.likelion.likelioncrudexcepvalid.member.application.MemberService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 멤버 저장
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<MemberInfoResDto> memberSave(@RequestBody @Valid MemberSaveReqDto memberSaveReqDto) {
        MemberInfoResDto memberInfoResDto = memberService.memberSave(memberSaveReqDto);
        return BaseResponse.success(SuccessCode.MEMBER_SAVE_SUCCESS, memberInfoResDto);
    }

    // 멤버 전체 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<MemberListResDto> memberFindAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "sort", defaultValue = "memberId,asc") String sort
    ) {
        Pageable pageable;

        if (sort.isEmpty()) {
            pageable = PageRequest.of(page, size, Sort.by("memberId").ascending());
        } else {
            String[] sortParms = sort.split(",");
            Sort sortOrder = Sort.by(Sort.Direction.fromString(sortParms[1]), sortParms[0]);
            pageable = PageRequest.of(page, size, sortOrder);
        }
        MemberListResDto memberListResDto = memberService.memberFindAll(pageable);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, memberListResDto);
    }

    // 멤버 id에 따라 멤버 한 개 조회
    @GetMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<MemberInfoResDto> memberFindOne(@PathVariable("memberId") Long memberId) {
        MemberInfoResDto memberInfoResDto = memberService.memberFindOne(memberId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, memberInfoResDto);
    }

    // 글 수정
    @PatchMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<MemberInfoResDto> memberUpdate(@PathVariable("memberId") Long memberId,
                                                       @RequestBody @Valid MemberUpdateReqDto memberUpdateReqDto) {
        MemberInfoResDto memberInfoResDto = memberService.memberUpdate(memberId, memberUpdateReqDto);
        return BaseResponse.success(SuccessCode.MEMBER_UPDATE_SUCCESS, memberInfoResDto);
    }

    // 글 삭제
    @DeleteMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<MemberInfoResDto> memberDelete(@PathVariable("memberId") Long memberId) {
        MemberInfoResDto memberInfoResDto = memberService.memberDelete(memberId);
        return BaseResponse.success(SuccessCode.MEMBER_DELETE_SUCCESS, memberInfoResDto);
    }
}