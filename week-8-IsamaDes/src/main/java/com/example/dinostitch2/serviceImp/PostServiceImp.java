package com.example.dinostitch2.serviceImp;

import com.example.dinostitch2.model.Post;
import com.example.dinostitch2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;




@Service
    public class PostServiceImp {

        private PostRepository postRepository;
        @Autowired
        public  PostServiceImp(PostRepository postRepository){
            this.postRepository=postRepository;
        }

            public ResponseEntity<Post> savePost(Post newPost){
            Post post = new Post(newPost.getTitle(), newPost.getDescription(), newPost.isPublished(), newPost.getImage(), newPost.getLikes());
            postRepository.save(post);
            return new ResponseEntity<>(post, HttpStatus.CREATED);
        }

        public ResponseEntity<List<Post>> getAllPost() {
            List<Post> allPost = postRepository.findAll();
            return new ResponseEntity<>(allPost, HttpStatus.FOUND);
        }

        public ResponseEntity<List<Post>> searchByPostTitle(String title) {
            List<Post> allSearchedPost = postRepository.findByTitleContaining(title);
            if (allSearchedPost.isEmpty()) {
                return new ResponseEntity<>(allSearchedPost, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(allSearchedPost, HttpStatus.FOUND);
            }
        }

        public ResponseEntity<Void> deletePostById(Long id){
            postRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        public ResponseEntity<Post> editPostById(Long id, Post postToBeEdited) {
            Optional<Post> post = postRepository.findById(id);
            if (post.isPresent()) {
                Post post1 = post.get();
                post1.setTitle(postToBeEdited.getTitle());
                post1.setDescription(postToBeEdited.getDescription());
                post1.setPublished(postToBeEdited.isPublished());
                post1.setImage(postToBeEdited.getImage());
                postRepository.save(post1);
                return new ResponseEntity<>(post1, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

            public ResponseEntity<Post> editPostByTitle(String title, Post postToBeEdited){
            Optional<Post> postTobeEdited = postRepository.findByTitle(title);

            if(postTobeEdited.isPresent()){
                Post post1= postToBeEdited.get();
                post1.setTitle(postToBeEdited.getTitle());
                post1.setDescription(postToBeEdited.getDescription());
                post1.setPublished(postToBeEdited.isPublished());
                post1.setImage(postToBeEdited.getImage());
                postRepository.save(post1);
                return  new ResponseEntity<>(post1, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
         }
    }


