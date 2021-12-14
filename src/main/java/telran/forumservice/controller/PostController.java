package telran.forumservice.controller;

import org.springframework.web.bind.annotation.*;
import telran.forumservice.dto.ContentDto;
import telran.forumservice.dto.MessageDto;
import telran.forumservice.dto.PostDto;
import telran.forumservice.service.PostService;

import java.util.List;

@RestController
public class PostController {

    PostService postService;

    @PostMapping("/forum/post/{author}")
    public PostDto addPost(@PathVariable("author") String author,  @RequestBody ContentDto contentDto ){
        return postService.addPost(contentDto);
    }

    @GetMapping("/forum/post/{id}")
    public PostDto findPostById(@PathVariable("id") String id){
        return postService.findPostById(id);
    }

    @DeleteMapping("/forum/post/{id}")
    public PostDto deletePost(@PathVariable("id") String id){
        return postService.deletePost(id);
    }

    @PutMapping("/forum/post/{id}")
    public PostDto updatePost(@PathVariable String id, @RequestBody ContentDto contentDto){
        return postService.updatePost(contentDto);
    }

    @PutMapping("/forum/post/{id}/like")
    public void addLike(@PathVariable String id){
        postService.addLikeToPost(id);
    }

    @PutMapping("/forum/post/{id}/comment/{author}")
    public PostDto addCommentToPost(@PathVariable String id, @PathVariable String author, @RequestBody MessageDto messageDto){
        return postService.addCommentToPost(messageDto);
    }

    @GetMapping("/forum/posts/author/{author}")
    public List<PostDto> findPostByAuthor(@PathVariable String author){
        return postService.findPostByAuthor(author);
    }
}
