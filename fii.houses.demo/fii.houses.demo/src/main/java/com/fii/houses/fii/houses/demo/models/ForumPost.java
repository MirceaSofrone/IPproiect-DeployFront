package com.fii.houses.fii.houses.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.*;

@Entity
public class ForumPost implements Comparable<ForumPost> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID postID;

    @ManyToOne
    @JsonIgnoreProperties({"forumPosts", "forumComments"})
    private User author;

    @OneToMany(mappedBy = "forumPost")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties("forumPost")
    @OrderBy("creationDate DESC")
    private SortedSet<ForumComment> comments = new TreeSet<>();

    @ManyToMany
    @JsonIgnoreProperties("likedPosts")
    private Set<User> likes = new HashSet<>();

    @ManyToMany
    @JsonIgnoreProperties("reportedPosts")
    private Set<User> reports = new HashSet<>();

    private Date creationDate;
    private String title;
    private String content;

    public UUID getPostID() {
        return postID;
    }

    public void setPostID(UUID postID) {
        this.postID = postID;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<ForumComment> getComments() {
        return comments;
    }

    public void setComments(SortedSet<ForumComment> comments) {
        this.comments = comments;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    public Set<User> getReports() {
        return reports;
    }

    public void setReports(Set<User> reports) {
        this.reports = reports;
    }

    @Override
    public int compareTo(ForumPost o) {
        return o.creationDate.compareTo(creationDate);
    }
}
