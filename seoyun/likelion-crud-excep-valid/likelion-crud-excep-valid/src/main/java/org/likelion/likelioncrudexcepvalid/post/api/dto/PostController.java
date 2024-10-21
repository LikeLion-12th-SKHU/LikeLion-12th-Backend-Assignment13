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

import java.util.List;

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

    //글 전체 조회
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostListResDto> postFindAll(){
        PostListResDto postListResDto = postService.postFindAll();

        return BaseResponse.success(SuccessCode.GET_SUCCESS, postListResDto);
    }

    //글 ID에 따라 글 한 개 조회
    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostInfoResDto> postFindById(@PathVariable("postId") Long postId){
        PostInfoResDto postInfoResDto = postService.postFindById(postId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, postInfoResDto);
    }

    //멤버이름으로 검색
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostListResDto> findByMemberName(@PathVariable("name") String name) {
        PostListResDto postListResDto = postService.findByMemberName(name);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, postListResDto);
    }

    //이메일으로 검색
    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostListResDto> findByMemberEmail(@PathVariable("email") String email) {
        PostListResDto postListResDto = postService.findByMemberEmail(email);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, postListResDto);
    }

    // 입력 조회
    @GetMapping("/search/{input}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostListResDto> findByPostInput(@PathVariable("input") String input) {
        PostListResDto postListResDto = postService.findByPostInput(input);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, postListResDto);
    }

    @PatchMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostInfoResDto> postUpdate(@PathVariable("postId") Long postId,
                                             @Valid @RequestBody PostUpdateReqDto postUpdateReqDto) {
        PostInfoResDto postInfoResDto = postService.postUpdate(postId, postUpdateReqDto);
        return BaseResponse.success(SuccessCode.MEMBER_UPDATE_SUCCESS, postInfoResDto);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostInfoResDto> postDelete(@PathVariable("postId") Long postId) {
        PostInfoResDto postInfoResDto = postService.postDelete(postId);
        return BaseResponse.success(SuccessCode.MEMBER_DELETE_SUCCESS, postInfoResDto);
    }
}