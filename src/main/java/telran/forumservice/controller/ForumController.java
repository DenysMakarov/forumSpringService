package telran.forumservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import telran.forumservice.dto.ContentDto;
import telran.forumservice.dto.PostDto;
import telran.forumservice.model.Post;
import telran.forumservice.service.ForumService;

@RestController
public class ForumController {
    ForumService forumService;

    @Autowired
    public ForumController(ForumService forumService) {
        this.forumService = forumService;
    }

    @PostMapping("/forum/post/{author}")
    public Post addPost(@PathVariable("author") String author, @RequestBody ContentDto contentDto ){
        return forumService.addPost(contentDto, author);
    }

    @GetMapping("/forum/post/{id}")
    public PostDto findPostById(@PathVariable("id") String id){
        return forumService.findPostById(id);
    }

//    @DeleteMapping("/forum/post/{id}")
//    public PostDto deletePost(@PathVariable("id") String id){
//        return forumService.deletePost(id);
//    }

    @PutMapping("/forum/post/{id}")
    public PostDto updatePost(@PathVariable String id, @RequestBody ContentDto contentDto){
        return forumService.updatePost(id, contentDto);
    }

    @PutMapping("/forum/post/{id}/like")
    public void addLike(@PathVariable String id){
        forumService.addLikeToPost(id);
    }

//    @PutMapping("/forum/post/{id}/comment/{author}")
//    public PostDto addCommentToPost(@PathVariable String id, @PathVariable String author, @RequestBody MessageDto messageDto){
//        return forumRepository.addCommentToPost(messageDto);
//    }
//
//    @GetMapping("/forum/posts/author/{author}")
//    public List<PostDto> findPostByAuthor(@PathVariable String author){
//        return forumRepository.findPostByAuthor(author);
//    }
}
