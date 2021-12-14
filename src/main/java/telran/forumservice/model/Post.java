package telran.forumservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Post {
    String id;
    @Setter
    String title;
    @Setter
    String content;
    String author;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime dateCreated;
    Set<String> tags;
    int likes;
    Set<Comment> comments;
    public Post(String title, String content, String author, Set<String> tags) {
//        this.id = author + System.currentTimeMillis();
        this.title = title;
        this.content = content;
        this.author = author;
        this.tags = tags;
        dateCreated = LocalDateTime.now();
        comments = new HashSet<>();
    }

//    public Post(String title, String content, String author) {
//        this(title, content, author, new HashSet<>());
//    }
//
//    public void addLike() {
//        likes++;
//    }
//
//    public boolean addComment(Comment comment) {
//        return comments.add(comment);
//    }
//
//    public boolean addTag(String tag) {
//        return tags.add(tag);
//    }
//
//    public boolean removeTag(String tag) {
//        return tags.remove(tag);
//    }

}
