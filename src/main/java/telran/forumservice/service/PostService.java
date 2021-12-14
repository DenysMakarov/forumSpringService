package telran.forumservice.service;

import telran.forumservice.dto.ContentDto;
import telran.forumservice.dto.MessageDto;
import telran.forumservice.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto addPost(ContentDto contentDto);
    PostDto findPostById(String id);
    PostDto deletePost(String id);
    PostDto updatePost(ContentDto contentDto);
    void addLikeToPost(String id);
    PostDto addCommentToPost(MessageDto message);
    List<PostDto> findPostByAuthor(String author);

}
