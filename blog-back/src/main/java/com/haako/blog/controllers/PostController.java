package com.haako.blog.controllers;


import com.haako.blog.domain.PostStatus;
import com.haako.blog.domain.dtos.PostDto;
import com.haako.blog.domain.entities.Post;
import com.haako.blog.domain.entities.User;
import com.haako.blog.mappers.PostMapper;
import com.haako.blog.repositories.PostRepository;
import com.haako.blog.services.PostService;
import com.haako.blog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/posts")
@RequiredArgsConstructor
public class PostController {


    private final PostService postService;
    private final PostMapper postMapper;

    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<PostDto>> getPosts(
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) UUID tagId){
        List<Post> posts = postService.getAllPosts(categoryId, tagId);
        List<PostDto> postDtos = posts.stream().map(postMapper::toDto).toList();
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping(path = "/drafts")
    public ResponseEntity<List<PostDto>> getDrafts(@RequestAttribute UUID userId){
        User loggedInUser = userService.getUserById(userId);
        List<Post> draftPost = postService.getDraftPost(loggedInUser);
        List<PostDto> draftPostDtos = draftPost.stream().map(postMapper::toDto).toList();
        return ResponseEntity.ok(draftPostDtos);
    }
}
