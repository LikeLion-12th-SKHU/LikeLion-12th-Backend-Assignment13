package org.likelion.likelioncrudexcepvalid.post.api.dto;

import jakarta.validation.Valid;
import org.likelion.likelioncrudexcepvalid.common.dto.BaseResponse;
import org.likelion.likelioncrudexcepvalid.common.error.SuccessCode;
import org.likelion.likelioncrudexcepvalid.post.api.dto.request.PostSaveReqDto;
import org.likelion.likelioncrudexcepvalid.post.api.dto.request.PostUpdateReqDto;
import org.likelion.likelioncrudexcepvalid.post.api.dto.response.PostInfoResDto;
import org.likelion.likelioncrudexcepvalid.post.api.dto.response.PostListResDto;
import org.likelion.likelioncrudexcepvalid.post.application.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<PostInfoResDto> postSave(@RequestBody @Valid PostSaveReqDto postSaveReqDto) {
        PostInfoResDto postInfoResDto = postService.postSave(postSaveReqDto);
        return BaseResponse.success(SuccessCode.POST_SAVE_SUCCESS, postInfoResDto);
    }

    // 글 전체 조회
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostListResDto> postFindAll() {
        PostListResDto postListResDto = postService.postFindAll();
        return BaseResponse.success(SuccessCode.GET_SUCCESS, postListResDto);
    }

    // postId에 따라 글 한 개 조회
    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostInfoResDto> postFindById(@PathVariable("postId") Long postId) {
        PostInfoResDto postInfoResDto = postService.postFindById(postId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, postInfoResDto);
    }

    @GetMapping("/members/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostListResDto> myPostFindAll(@PathVariable("memberId") Long memberId) {
        PostListResDto postListResDto = postService.postFindMember(memberId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, postListResDto);
    }

    @PatchMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostInfoResDto> postUpdate(@PathVariable("postId") Long postId,
                                             @RequestBody @Valid PostUpdateReqDto postUpdateReqDto) {
        PostInfoResDto postInfoResDto = postService.postUpdate(postId, postUpdateReqDto);
        return BaseResponse.success(SuccessCode.POST_UPDATE_SUCCESS, postInfoResDto);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostInfoResDto> postDelete(@PathVariable("postId") Long postId) {
        PostInfoResDto postInfoResDto = postService.postDelete(postId);
        return BaseResponse.success(SuccessCode.POST_DELETE_SUCCESS, postInfoResDto);
    }

    // 과제
    // 키워드
    @GetMapping("search/{input}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostListResDto> findByInput(@PathVariable("input") String input) {
        PostListResDto postListResDto = postService.findByInput(input);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, postListResDto);
    }

    // 이름
    @GetMapping("name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostListResDto> findByName(@PathVariable("name") String name) {
        PostListResDto postListResDto = postService.findByName(name);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, postListResDto);
    }

    // 이메일
    @GetMapping("email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostListResDto> findByEmail(@PathVariable("email") String email) {
        PostListResDto postListResDto = postService.findByEmail(email);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, postListResDto);
    }

}