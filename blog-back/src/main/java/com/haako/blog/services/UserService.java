package com.haako.blog.services;

import com.haako.blog.domain.entities.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id );
}
