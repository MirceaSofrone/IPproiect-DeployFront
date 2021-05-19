package com.fii.houses.fii.houses.demo.controllers;

import com.fii.houses.fii.houses.demo.models.ForumPost;
import com.fii.houses.fii.houses.demo.models.House;
import com.fii.houses.fii.houses.demo.models.User;
import com.fii.houses.fii.houses.demo.service.ForumPostService;
import com.fii.houses.fii.houses.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/forum")
public class ForumPostController {
    @Autowired
    private ForumPostService forumPostService;

    @GetMapping()
    public ResponseEntity<List<ForumPost>> getForumPosts() {
        List<ForumPost> forumPosts = forumPostService.getAllForumPosts();
        if (forumPosts.isEmpty())
            return new ResponseEntity<>(forumPosts, new HttpHeaders(), HttpStatus.NO_CONTENT);

        Collections.sort(forumPosts);
        return new ResponseEntity<>(forumPosts, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ForumPost> createForumPost(@RequestBody ForumPost forumPost) {
        ForumPost createdPost = forumPostService.create(forumPost);
        if (createdPost == null)
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(createdPost, new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deleteById(@PathVariable UUID postId) {
        Optional<ForumPost> optionalPost = forumPostService.getForumPostById(postId);

        if (optionalPost.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (!forumPostService.deleteForumPost(optionalPost.get()))
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ForumPost> getPost(@PathVariable UUID postId) {
        Optional<ForumPost> optionalPost = forumPostService.getForumPostById(postId);

        if (optionalPost.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(optionalPost.get());
    }

    @PutMapping(value = "/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ForumPost> updatePost(@PathVariable UUID postId, @RequestBody ForumPost newPost) {
        Optional<ForumPost> optionalPost = forumPostService.getForumPostById(postId);

        if (optionalPost.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ForumPost oldPost = optionalPost.get();

        // making sure the client doesn't add more than one like (or report)
        if (Math.abs(oldPost.getLikes().size() - newPost.getLikes().size()) > 1 ||
                Math.abs(oldPost.getReports().size() - newPost.getReports().size()) > 1)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        oldPost = newPost;
        newPost = forumPostService.update(oldPost);

        if (newPost == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(newPost, new HttpHeaders(), HttpStatus.OK);
    }
}
