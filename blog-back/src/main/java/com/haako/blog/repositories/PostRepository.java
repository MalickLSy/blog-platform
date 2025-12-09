package com.haako.blog.repositories;

import com.haako.blog.domain.PostStatus;
import com.haako.blog.domain.entities.Category;
import com.haako.blog.domain.entities.Post;
import com.haako.blog.domain.entities.Tag;
import com.haako.blog.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
   List<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatus status, Category category, Tag tag);
   List<Post> findAllByStatusAndCategory(PostStatus status, Category category);
   List<Post> findAllByStatusAndTags(PostStatus status, Tag tag);
   List<Post> findAllByStatus(PostStatus status);
   List<Post> findAllByAuthorAndStatus(User author,PostStatus status);
}
