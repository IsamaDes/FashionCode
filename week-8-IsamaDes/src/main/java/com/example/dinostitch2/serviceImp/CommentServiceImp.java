package com.example.dinostitch2.serviceImp;

import com.example.dinostitch2.model.Comment;
import com.example.dinostitch2.model.Post;
import com.example.dinostitch2.repository.CommentRepository;
import com.example.dinostitch2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImp {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    @Autowired
    public  CommentServiceImp(CommentRepository commentRepository, PostRepository postRepository){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;


    }

    public ResponseEntity<Comment> createCommnentByPostId( Long postId, Comment newComment) {
        Optional<Post> post = postRepository.findById(postId);

        if(post.isPresent()) {
            Post savedPost = post.get();
            newComment.setPost(savedPost);
            newComment.setContent(newComment.getContent());
            commentRepository.save(newComment);
            return new ResponseEntity<>(newComment, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Comment> editCommentById(Long commentId, Comment newComment) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if(comment.isPresent()) {
            Comment comment1 = comment.get();
            comment1.setContent(newComment.getContent());
            commentRepository.save(comment1);
            return new ResponseEntity<>(comment1, HttpStatus.OK);
        }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    public ResponseEntity<List<Comment>> getCommentByPostId(Long postId) {
        List<Comment> commentList = commentRepository.findByPostId(postId);

        if (commentList.isEmpty()) {
            return new ResponseEntity<>(commentList, HttpStatus.NO_CONTENT);
        }else {
            return  new ResponseEntity<>(commentList, HttpStatus.FOUND);
        }
    }


    public ResponseEntity<Void> deleteCommentById(Long commentId) {
        Optional<Comment> comment= commentRepository.findById(commentId);

        if(comment.isPresent()){
            Comment comment1 = comment.get();
            commentRepository.deleteById(comment1.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Comment>> searchCommentByContent(String content) {
        List<Comment> commentList = commentRepository.findByContentIgnoreCaseContaining(content);

    if(commentList.isEmpty()){
         return new ResponseEntity<>(commentList, HttpStatus.FOUND);
    }else{
        return new ResponseEntity<>(commentList, HttpStatus.NO_CONTENT);
    }
    }
}











//        public ResponseEntity<List<Comment>> getAllCommentByPostId(Long postId, Comment allComment);
//        List<Post> allComment = postRepository.findAllCommentPostById();
//        if (allComment.isEmpty()) {
//            return new ResponseEntity<>(allComment, HttpStatus.NO_CONTENT);
//        return  commentRepository.findAllCommentByPostId(postId);



//    public ResponseEntity<List<Post>> getAllCommentById(Long id) {
//        BiFunctional<PostId, Comment> allCommentById  = commentRepository.findAllCommentById(id);
//        return new ResponseEntity<>(allCommentById, HttpStatus.FOUND);
