package org.likelion.likelioncrudexcepvalid.post.application;

import org.likelion.likelioncrudexcepvalid.common.error.ErrorCode;
import org.likelion.likelioncrudexcepvalid.common.exception.NotFoundException;
import org.likelion.likelioncrudexcepvalid.member.domain.Member;
import org.likelion.likelioncrudexcepvalid.member.domain.Part;
import org.likelion.likelioncrudexcepvalid.member.domain.repository.MemberRepository;
import org.likelion.likelioncrudexcepvalid.post.api.dto.request.PostSaveReqDto;
import org.likelion.likelioncrudexcepvalid.post.api.dto.request.PostUpdateReqDto;
import org.likelion.likelioncrudexcepvalid.post.api.dto.response.PostInfoResDto;
import org.likelion.likelioncrudexcepvalid.post.api.dto.response.PostListResDto;
import org.likelion.likelioncrudexcepvalid.post.domain.Post;
import org.likelion.likelioncrudexcepvalid.post.domain.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public PostService(MemberRepository memberRepository, PostRepository postRepository) {
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    // Create
    @Transactional
    public PostInfoResDto postSave(PostSaveReqDto postSaveReqDto) {
        Member member = memberRepository.findById(postSaveReqDto.memberId()).orElseThrow(
                () -> new NotFoundException(ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.INTERNAL_SERVER_ERROR.getMessage() + postSaveReqDto.memberId())    // 수정
        );

        Post post = Post.builder()
                .title(postSaveReqDto.title())
                .contents(postSaveReqDto.contents())
                .member(member)
                .build();

        postRepository.save(post);
        return PostInfoResDto.from(post);
    }

    // Read (작성자에 따른 게시물 리스트)
    public PostListResDto postFindMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION.getMessage() + memberId)  // 수정
        );

        List<Post> posts = postRepository.findByMember(member);
        List<PostInfoResDto> postInfoResDtoList = posts.stream()
                .map(PostInfoResDto::from)
                .toList();

        return PostListResDto.from(postInfoResDtoList);
    }

    // READ ALL
    public PostListResDto postFindAll() {
        List<Post> posts = postRepository.findAll();

        List<PostInfoResDto> postInfoResDtoList = posts.stream()
                .map(PostInfoResDto::from)
                .toList();

        return PostListResDto.from(postInfoResDtoList);
    }

    // READ ONE
    public PostInfoResDto postFindById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException(ErrorCode.POSTS_NOT_FOUND_EXCEPTION,
                        ErrorCode.POSTS_NOT_FOUND_EXCEPTION.getMessage() + postId)  // 수정
        );

        return PostInfoResDto.from(post);
    }

    // Update
    @Transactional
    public PostInfoResDto postUpdate(Long postId, PostUpdateReqDto postUpdateReqDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException(ErrorCode.POSTS_NOT_FOUND_EXCEPTION,
                        ErrorCode.POSTS_NOT_FOUND_EXCEPTION.getMessage() + postId)  // 수정
        );

        post.update(postUpdateReqDto);
        return PostInfoResDto.from(post);
    }

    // Delete
    @Transactional
    public PostInfoResDto postDelete(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException(ErrorCode.POSTS_NOT_FOUND_EXCEPTION,
                        ErrorCode.POSTS_NOT_FOUND_EXCEPTION.getMessage() + postId)  // 수정
        );

        postRepository.delete(post);
        return PostInfoResDto.from(post);
    }

    // 과제 부분
    // 특정 키워드를 통한 검색 조회
    @Transactional
    public PostListResDto postFindByInput(String input) {
        String searchInput = "%" + input + "%";
        List<Post> posts = postRepository.postFindByInput(searchInput);

        List<PostInfoResDto> postInfoResDtoList = posts.stream()
                .map(PostInfoResDto::from)
                .toList();

        return PostListResDto.from(postInfoResDtoList);
    }

    // 사용자 이름으로 게시글 조회
    @Transactional
    public PostListResDto postFindByName(String name) {
        List<Post> posts = postRepository.postFindByName(name);

        List<PostInfoResDto> postInfoResDtoList = posts.stream()
                .map(PostInfoResDto::from)
                .toList();

        return PostListResDto.from(postInfoResDtoList);
    }

    // 사용자 part로 게시글 조회
    @Transactional
    public PostListResDto postFindByPart(String part) {
        Part partEnum = Part.valueOf(part);
        List<Post> posts = postRepository.postFindByPart(partEnum);

        List<PostInfoResDto> postInfoResDtoList = posts.stream()
                .map(PostInfoResDto::from)
                .toList();

        return PostListResDto.from(postInfoResDtoList);
    }

}