package com.haako.blog.services;

import com.haako.blog.domain.entities.Post;
import com.haako.blog.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPost(User user);
}
