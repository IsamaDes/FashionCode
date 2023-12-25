package com.example.dinostitch2.repository;
import com.example.dinostitch2.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);





    List<Comment> findByContentIgnoreCaseContaining(String content);
}
