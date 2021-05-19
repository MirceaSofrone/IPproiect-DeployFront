package com.fii.houses.fii.houses.demo.controllers;

import com.fii.houses.fii.houses.demo.models.ForumComment;
import com.fii.houses.fii.houses.demo.models.ForumPost;
import com.fii.houses.fii.houses.demo.service.ForumCommentService;
import com.fii.houses.fii.houses.demo.service.ForumPostService;
import com.fii.houses.fii.houses.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/forum/{idPost}")
public class ForumCommentController {
    @Autowired
    private ForumCommentService forumCommentService;

    @GetMapping("/comments")
    public ResponseEntity<Set<ForumComment>> getPostComments(@PathVariable UUID idPost) {
        Set<ForumComment> comments = forumCommentService.getAllCommentsOfPostById(idPost);

        if (comments.isEmpty())
            return new ResponseEntity<>(comments, new HttpHeaders(), HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(comments, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ForumComment> createForumComment(@RequestBody ForumComment forumComment, @PathVariable UUID idPost) {
        ForumComment createdComment = forumCommentService.create(forumComment, idPost);

        if (createdComment == null)
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(createdComment, new HttpHeaders(), HttpStatus.CREATED);
    }

}
