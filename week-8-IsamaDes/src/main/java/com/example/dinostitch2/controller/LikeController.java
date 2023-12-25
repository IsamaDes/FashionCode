package com.example.dinostitch2.controller;

import com.example.dinostitch2.model.Post;
import com.example.dinostitch2.serviceImp.LikeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/likes")
public class LikeController {
    private LikeServiceImp likeService;
    @Autowired
    public  LikeController(LikeServiceImp likeService){
         this.likeService=likeService;
    }

    @PutMapping("/like-true/{postId}")
    public ResponseEntity<Post> getIncreaseLikeById(@PathVariable Long postId){
        return likeService.increaseLikePostById(postId);
    }

    @PutMapping("/like-false/{postId}")
    public ResponseEntity<Post> getDecreaseLikeById(@PathVariable Long postId){
        return likeService.decreaseLikePostById(postId);
    }
}
