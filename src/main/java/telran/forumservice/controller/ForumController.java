package telran.forumservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import telran.forumservice.dto.ContentDto;
import telran.forumservice.dto.DateCreatedDto;
import telran.forumservice.dto.MessageDto;
import telran.forumservice.dto.PostDto;
import telran.forumservice.model.Post;
import telran.forumservice.service.ForumService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ForumController {
    ForumService forumService;

    @Autowired
    public ForumController(ForumService forumService) {
        this.forumService = forumService;
    }

    @PostMapping("/forum/post/{author}")
    public Post addPost(@PathVariable("author") String author, @RequestBody ContentDto contentDto) {
        return forumService.addPost(contentDto, author);
    }

    @GetMapping("/forum/post/{id}")
    public PostDto findPostById(@PathVariable("id") String id) {
        return forumService.findPostById(id);
    }

    @DeleteMapping("/forum/post/{id}")
    public PostDto deletePost(@PathVariable String id) {
        return forumService.removePost(id);
    }

    @PutMapping("/forum/post/{id}")
    public PostDto updatePost(@PathVariable String id, @RequestBody ContentDto contentDto) {
        return forumService.updatePost(id, contentDto);
    }

    @PutMapping("/forum/post/{id}/like")
    public void addLike(@PathVariable String id) {
        forumService.addLikeToPost(id);
    }

    @PutMapping("/forum/post/{id}/comment/{author}")
    public PostDto addCommentToPost(@PathVariable String id, @PathVariable String author, @RequestBody MessageDto messageDto) {
        return forumService.addCommentToPost(id, author, messageDto);
    }

    @GetMapping("/forum/posts/author/{author}")
    public List<PostDto> findPostsByAuthor(@PathVariable String author){
        return forumService.findPostsByAuthor(author);
    }

    @PostMapping("/forum/posts/tags")
    public List<PostDto> findPostsByTags(@RequestBody List<String> tags){
        return forumService.findPostsByTags(tags);
    }

    @PostMapping("/forum/posts/period")
    public List<PostDto> findPostsByLocalDate(@RequestBody DateCreatedDto date){
        return forumService.findPostsByDateCreated(date);
    }

}
