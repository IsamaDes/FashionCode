package com.example.dinostitch2.controller;

import com.example.dinostitch2.model.Comment;
import com.example.dinostitch2.repository.PostRepository;
import com.example.dinostitch2.serviceImp.CommentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private CommentServiceImp commentService;

    @Autowired
    public CommentController(CommentServiceImp commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/save-comment/{postId}")
    public ResponseEntity<Comment> createCommentByPostId(@PathVariable Long postId, @RequestBody Comment newComment) {
        return commentService.createCommnentByPostId(postId, newComment);
    }

    @PutMapping("/edit-comment/{commentId}")
    public ResponseEntity<Comment> editCommentById(@PathVariable Long commentId, @RequestBody Comment newComment) {
        return commentService.editCommentById(commentId, newComment);
    }

    @GetMapping("/all-comment/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postId) {
        return commentService.getCommentByPostId(postId);
    }
    @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<Void> deleteCommentByCommentId(@PathVariable Long commentId){
        return  commentService.deleteCommentById(commentId);
    }

    @GetMapping("/get-comment/{content}")
    public ResponseEntity<List<Comment>> searchCommentByContent(@PathVariable String content){
        return commentService.searchCommentByContent(content);
    }

}


