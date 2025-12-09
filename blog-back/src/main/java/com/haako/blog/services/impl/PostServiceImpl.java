package com.haako.blog.services.impl;

import com.haako.blog.domain.PostStatus;
import com.haako.blog.domain.entities.Category;
import com.haako.blog.domain.entities.Post;
import com.haako.blog.domain.entities.Tag;
import com.haako.blog.domain.entities.User;
import com.haako.blog.repositories.PostRepository;
import com.haako.blog.services.CategoryService;
import com.haako.blog.services.PostService;
import com.haako.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;

    @Transactional(readOnly = true)
    @Override
    public List<Post> getAllPosts(UUID categoryId, UUID tagId) {

        if(categoryId != null && tagId != null){
            Category category = categoryService.getCategoryById(categoryId);
            Tag tag  = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndCategoryAndTagsContaining(PostStatus.PUBLISHED,category,tag);
        }
        if (categoryId != null){
            Category category = categoryService.getCategoryById(categoryId);
            return postRepository.findAllByStatusAndCategory(PostStatus.PUBLISHED, category);
        }

        if (tagId != null){
            Tag tag  = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndTags(PostStatus.PUBLISHED, tag);
        }
        return postRepository.findAllByStatus(PostStatus.PUBLISHED);
    }

    @Override
    public List<Post> getDraftPost(User user) {
       return postRepository.findAllByAuthorAndStatus(user,PostStatus.DRAFT);
    }


}
