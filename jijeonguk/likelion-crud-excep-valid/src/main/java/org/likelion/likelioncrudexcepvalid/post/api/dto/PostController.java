package org.likelion.likelioncrudexcepvalid.post.api.dto;

import jakarta.validation.Valid;
import org.likelion.likelioncrudexcepvalid.common.dto.BaseResponse;
import org.likelion.likelioncrudexcepvalid.common.error.SuccessCode;
import org.likelion.likelioncrudexcepvalid.member.domain.Member;
import org.likelion.likelioncrudexcepvalid.member.domain.Part;
import org.likelion.likelioncrudexcepvalid.post.api.dto.request.PostSaveReqDto;
import org.likelion.likelioncrudexcepvalid.post.api.dto.request.PostUpdateReqDto;
import org.likelion.likelioncrudexcepvalid.post.api.dto.response.PostInfoResDto;
import org.likelion.likelioncrudexcepvalid.post.api.dto.response.PostListResDto;
import org.likelion.likelioncrudexcepvalid.post.application.PostService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    // 글 ID에 따라 글 한 개 조회
    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostInfoResDto> postFindById(@PathVariable("postId") Long postId) {
        PostInfoResDto postInfoResDto = postService.postFindById(postId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, postInfoResDto);
    }

    @GetMapping("/{members}/posts")
    public BaseResponse<PostListResDto> myPostFindAll(@PathVariable("memberId") Long memberId) {
        PostListResDto postListResDto = postService.postFindMember(memberId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, postListResDto);
    }

    @PatchMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostInfoResDto> postUpdate(@PathVariable("postId") Long postId,
                                             @RequestBody @Valid PostUpdateReqDto postUpdateReqDto) {
        PostInfoResDto postInfoResDto = postService.postUpdate(postId, postUpdateReqDto);
        return BaseResponse.success(SuccessCode.MEMBER_UPDATE_SUCCESS, postInfoResDto);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostInfoResDto> postDelete(@PathVariable("postId") Long postId) {
        PostInfoResDto postInfoResDto = postService.postDelete(postId);
        return BaseResponse.success(SuccessCode.POST_DELETE_SUCCESS, postInfoResDto);
    }


    @GetMapping("/latest")
    public ResponseEntity<List<PostInfoResDto>> findLatestPosts(Pageable pageable) {
        List<PostInfoResDto> posts = postService.findLatestPosts(pageable);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/search/keyword")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostListResDto> searchPostsByKeyword(@RequestParam("keyword") String keyword) {
        PostListResDto postListResDto = postService.searchPostsByKeyword(keyword);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, postListResDto);
    }

    @GetMapping("/latest/members")
    public List<Member> getLatestMembers() {
        return postService.findLatestMembers();
    }
}