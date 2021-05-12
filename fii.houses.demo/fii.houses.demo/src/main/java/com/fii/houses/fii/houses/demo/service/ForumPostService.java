package com.fii.houses.fii.houses.demo.service;

import com.fii.houses.fii.houses.demo.models.ForumPost;
import com.fii.houses.fii.houses.demo.repository.ForumPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ForumPostService {
    @Autowired
    private ForumPostRepository forumPostRepository;

    public List<ForumPost> getAllForumPosts() {
        return forumPostRepository.findAll();
    }

    public Optional<ForumPost> getForumPostById(UUID id) {
        return forumPostRepository.findById(id);
    }

    public ForumPost create(ForumPost forumPost) {
        forumPost.setPostID(UUID.randomUUID());
        forumPost.setCreationDate(new Date());
        return forumPostRepository.save(forumPost);
    }

    public ForumPost update(ForumPost forumPost) {
        if (!forumPostRepository.existsById(forumPost.getPostID()))
            throw new IllegalArgumentException("Resource can't be updated because it hasn't been created before!");

        return forumPostRepository.save(forumPost);
    }

    public boolean deleteForumPost(ForumPost forumPost) {
        if (!forumPostRepository.existsById(forumPost.getPostID()))
            return false;

        forumPostRepository.delete(forumPost);
        return true;
    }
}
