package com.haako.blog.services;

import com.haako.blog.domain.entities.Tag;
import com.haako.blog.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;


public interface TagService {
   List<Tag> getTags();
   List<Tag> createTags(Set<String> tagNames);
   void deleteTag(UUID id);

}
