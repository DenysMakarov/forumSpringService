package telran.forumservice.service;

import org.springframework.stereotype.Service;
import telran.forumservice.dto.ContentDto;
import telran.forumservice.dto.MessageDto;
import telran.forumservice.dto.PostDto;
import telran.forumservice.model.Post;

import java.util.List;

public interface ForumService {
    Post addPost(ContentDto contentDto, String author);
    PostDto findPostById(String id);
    PostDto deletePost(String id);
    PostDto updatePost(String id, ContentDto contentDto);
    boolean addLikeToPost(String id);
    PostDto addCommentToPost(String id, String author, MessageDto message);
    List<PostDto> findPostByAuthor(String author);

}
