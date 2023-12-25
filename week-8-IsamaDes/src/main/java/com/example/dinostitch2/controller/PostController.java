package com.example.dinostitch2.controller;

import com.example.dinostitch2.model.Post;
import com.example.dinostitch2.serviceImp.PostServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/post")
    public class PostController {
        private PostServiceImp postService;

        @Autowired
        public PostController(PostServiceImp postService) {
            this.postService = postService;
        }

        @PostMapping("/create-post")
        public ResponseEntity<Post> createPost(@RequestBody Post newPost) {
            return postService.savePost(newPost);
        }

        ////Get All Post////
        @GetMapping("/all-post")
        public ResponseEntity<List<Post>> getAllPost() {
            return postService.getAllPost();
        }

        /////Search Post By Title////
        //through pathvariable
        @GetMapping("/search-post/{title}")
        public ResponseEntity<List<Post>> searchPost(@PathVariable String title) {
            return postService.searchByPostTitle(title);
        }

        ////Delete Post////
        @DeleteMapping("/delete-post/{id}")
        public ResponseEntity<Void> deletePostById(@PathVariable long id) {
            return postService.deletePostById(id);
        }


        /////Edit Post////
        @PutMapping("/edit-post/{id}")
        public ResponseEntity<Post> editPostById(@PathVariable Long id, @RequestBody Post postToBeEdited) {
            return postService.editPostById(id, postToBeEdited);
        }


        /////Edit Post By Title/////
        @PutMapping("/edit-post/{title}")
        public  ResponseEntity<Post> editPostByTitle(@PathVariable String title, @RequestBody Post postToBeEdited){
            return postService.editPostByTitle(title, postToBeEdited);
        }

    }









//ResponseEntity
//ResponseEntity helps us create a new status code with everypost that is saved on our API
// API- Application Programme Interface
//response entity is a model for how http response should look like, including, the header, body,
//important when we we want a response that isnt an html page;
//it gives a response code


////Pathvariable
///extracts values from URI and use them as method parameters
////Uniform Resource Identifier


/////RequestBody
//RequestBody helps in deserialization of json object to java object
//RequestBody tells spring framework to attach the json object from the uri path to our controller method
//RequestBody works with put and post request;


//when response is sent via http
//it contains header, and body
//response body contains the body
//the view decides what we will use
//if its a search - then we will use requestbody;
//
