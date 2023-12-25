package com.example.dinostitch2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.Date;
    //id-primarykey of the database

    //gv- id is automatically generated
    //sequence-type of sequence
    //lob-content field should be large object
    //many comment should be associated to one post
    //fetchtype=default value for many to one
    //join
    //joincolumn collects the postid from post to comment
    //this makes post a foregin key here
    //entity creates table,
    // @table allows us to give a name to our table different from the one the entity provides



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="comment_generator")
    private Long id;

    @Column(nullable=false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Post post;

    @Column(name="commentDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentDate;

    public Comment(String content){
        this.content=content;
    }
}
