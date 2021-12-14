package telran.forumservice.service;

import telran.forumservice.dto.ContentDto;
import telran.forumservice.dto.MessageDto;
import telran.forumservice.dto.PostDto;

import java.util.List;

public class PostServiceImpl implements PostService{
    @Override
    public PostDto addPost(ContentDto contentDto) {
        return null;
    }

    @Override
    public PostDto findPostById(String id) {
        return null;
    }

    @Override
    public PostDto deletePost(String id) {
        return null;
    }

    @Override
    public PostDto updatePost(ContentDto contentDto) {
        return null;
    }

    @Override
    public void addLikeToPost(String id) {

    }

    @Override
    public PostDto addCommentToPost(MessageDto message) {
        return null;
    }

    @Override
    public List<PostDto> findPostByAuthor(String author) {
        return null;
    }
}
