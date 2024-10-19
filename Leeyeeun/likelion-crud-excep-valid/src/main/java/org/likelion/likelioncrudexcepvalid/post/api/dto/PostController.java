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

    // 전체 게시물 조회
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostListResDto> postFindAll() {
        PostListResDto postListResDto = postService.postFindAll();
        return BaseResponse.success(SuccessCode.GET_SUCCESS, postListResDto);
    }

    // ID로 게시물 조회
    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostInfoResDto> postFindById(@PathVariable("postId") Long postId) {
        PostInfoResDto postInfoResDto = postService.postFindById(postId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, postInfoResDto);
    }

    // 제목으로 검색
    @GetMapping("/search/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostListResDto> findByTitle(@PathVariable("title") String title) {
        PostListResDto postListResDto = postService.findByTitle(title);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, postListResDto);
    }

    // 작성자 이름으로 검색
    @GetMapping("/search/member-name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostListResDto> findByMemberName(@PathVariable("name") String name) {
        PostListResDto postListResDto = postService.findByMemberName(name);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, postListResDto);
    }

    // 내용으로 검색
    @GetMapping("/search/contents/{contents}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostListResDto> findByContents(@PathVariable("contents") String contents) {
        PostListResDto postListResDto = postService.findByContents(contents);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, postListResDto);
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
        return BaseResponse.success(SuccessCode.POST_SAVE_SUCCESS, postInfoResDto);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostInfoResDto> postDelete(@PathVariable("postId") Long postId) {
        PostInfoResDto postInfoResDto = postService.postDelete(postId);
        return BaseResponse.success(SuccessCode.POST_DELETE_SUCCESS, postInfoResDto);
    }
}
