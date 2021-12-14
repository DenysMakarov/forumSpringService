package telran.forumservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.forumservice.dao.ForumMongoRepository;
import telran.forumservice.dto.*;
import telran.forumservice.model.Post;

import java.util.List;

@Service
public class ForumServiceImpl implements ForumService {
    ForumMongoRepository forumRepository;
    ModelMapper modelMapper;

    @Autowired
    public ForumServiceImpl(ForumMongoRepository forumMongoRepository, ModelMapper modelMapper) {
        this.forumRepository = forumMongoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Post addPost(ContentDto contentDto, String author) {
        Post post = modelMapper.map(contentDto, Post.class);
        post.setAuthor(author);
        forumRepository.save(post);
        return Post.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .dateCreated(post.getDateCreated())
                .tags(post.getTags())
                .likes(post.getLikes())
                .comments(post.getComments())
                .build();
    }

    @Override
    public PostDto findPostById(String id) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFondException(id));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto deletePost(String id) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFondException(id));
        forumRepository.deleteById(id);
        return modelMapper.map(post, PostDto.class);
        //        return post == null ? null : modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto updatePost(String id, ContentDto contentDto) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFondException(id));
        post.setTitle(contentDto.getTitle());
        post.setContent(contentDto.getContent());
        post.setTags(contentDto.getTags());
        forumRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public boolean addLikeToPost(String id) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFondException(id));
        post.setLikes(post.getLikes()+1);
        forumRepository.save(post);
        return true;
    }

    @Override
    public PostDto addCommentToPost(String id, String author, MessageDto message) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFondException(id));
        CommentDto commentDto = new CommentDto(author, message.getMessage());
        post.getComments().add(commentDto);
        forumRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> findPostByAuthor(String author) {
        return null;
    }
}
