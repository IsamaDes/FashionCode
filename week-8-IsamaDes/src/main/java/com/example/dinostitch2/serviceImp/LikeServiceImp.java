package com.example.dinostitch2.serviceImp;

import com.example.dinostitch2.model.Post;
import com.example.dinostitch2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImp {
    private PostRepository postRepository;
    @Autowired
    public LikeServiceImp( PostRepository postRepository){
        this.postRepository=postRepository;
    }

    public ResponseEntity<Post> increaseLikePostById(Long postId){
        Post post = postRepository.findById(postId).orElse(null);

        if(post!=null) {
            post.incrementPostLikedCount();
            postRepository.save(post);

            return new ResponseEntity<>(post, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<Post> decreaseLikePostById(Long postId){
        Post post = postRepository.findById(postId).orElse(null);
        if(post!=null) {
            post.decrementPostLikedCount();
            postRepository.save(post);

            return new ResponseEntity<>(post, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
