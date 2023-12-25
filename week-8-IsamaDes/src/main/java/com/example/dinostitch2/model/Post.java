package com.example.dinostitch2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Data
    @Table(name="posts")
    public class Post {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_generator")
        private long id;

        @Column(name="title")
        private String title;
        @Column(name="description")
        private String description;
        @Column(name="published")
        private boolean published;

        @Column(name="postDate")
        @Temporal(TemporalType.TIMESTAMP)
        @org.hibernate.annotations.CreationTimestamp
        private Date postDate;
        @Column(name="image")
        private String image;

        @Column(name="likes")
        private Integer likes=0;



        public Post( String title, String description, boolean published, String image, Integer likes) {

            this.title = title;
            this.description = description;
            this.published = published;
            this.image= image;
            this.likes = likes;
        }

/////////////////editByTitle getmethod/////////////
        public Post get() {
            return null;
        }




        public Integer incrementPostLikedCount(){
            likes++;
            return likes;

        }

        public Integer decrementPostLikedCount(){
            if(likes==0){
                return 0;
            } else {
                likes--;
            }
            return likes;
        }
    }

