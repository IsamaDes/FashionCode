package com.example.dinostitch2.repository;

import com.example.dinostitch2.model.Comment;
import com.example.dinostitch2.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
        List<Post> findByTitleContaining(String title);
        Optional<Post> findByTitle(String title);



        Optional<Post> findById(Long postId );

}


