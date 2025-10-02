package com.haako.blog.repositories;

import com.haako.blog.domain.entities.Category;
import com.haako.blog.domain.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {
    @Query("SELECT c from Tag c LEFT JOIN FETCH c.posts")
    List<Tag> findAllWithPostCount();
    List<Tag> findByNameIn(Set<String> names);
}
