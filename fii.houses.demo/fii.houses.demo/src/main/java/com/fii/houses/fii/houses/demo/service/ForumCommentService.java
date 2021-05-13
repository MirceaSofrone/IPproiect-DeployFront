package com.fii.houses.fii.houses.demo.service;

import com.fii.houses.fii.houses.demo.models.ForumComment;
import com.fii.houses.fii.houses.demo.models.ForumPost;
import com.fii.houses.fii.houses.demo.repository.ForumCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ForumCommentService {
    @Autowired
    private ForumCommentRepository forumCommentRepository;

    @Autowired
    private ForumPostService forumPostService;

    public List<ForumComment> getAllForumComments() {
        return forumCommentRepository.findAll();
    }

    public Optional<ForumComment> getForumCommentById(UUID id) {
        return forumCommentRepository.findById(id);
    }

    public Set<ForumComment> getAllCommentsOfPostById(UUID postId) throws IllegalArgumentException {
        Optional<ForumPost> postOptional = forumPostService.getForumPostById(postId);

        if (postOptional.isEmpty())
            throw new IllegalArgumentException("Post with specified ID doesn't exist!");

        return postOptional.get().getComments();
    }

    public ForumComment create(ForumComment forumComment, UUID postId) throws IllegalArgumentException {
        Optional<ForumPost> postOptional = forumPostService.getForumPostById(postId);

        if (postOptional.isEmpty())
            throw new IllegalArgumentException("Post with specified ID doesn't exist!");

        forumComment.setCommentID(UUID.randomUUID());
        forumComment.setCreationDate(new Date());
        forumComment.setForumPost(postOptional.get());
        System.out.println(postOptional.get().getPostID());

        return forumCommentRepository.save(forumComment);
    }

    public ForumComment update(ForumComment forumComment) throws IllegalArgumentException {
        if (!forumCommentRepository.existsById(forumComment.getCommentID()))
            throw new IllegalArgumentException("Resource can't be updated because it hasn't been created before!");

        return forumCommentRepository.save(forumComment);
    }

    public boolean delete(ForumComment forumComment) {
        if (!forumCommentRepository.existsById(forumComment.getCommentID()))
            return false;

        forumCommentRepository.delete(forumComment);
        return true;
    }
}
