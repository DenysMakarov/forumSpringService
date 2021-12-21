package telran.forumservice.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.forumservice.dao.ForumMongoRepository;
import telran.forumservice.dto.*;
import telran.forumservice.model.Comment;
import telran.forumservice.model.Post;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@Slf4j
public class ForumServiceImpl implements ForumService {
    //    static final Logger log = LoggerFactory.getLogger(ForumServiceImpl.class);
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
        return forumRepository.save(post);
//        return Post.builder()
//                .id(post.getId())
//                .title(post.getTitle())
//                .content(post.getContent())
//                .author(post.getAuthor())
//                .dateCreated(post.getDateCreated())
//                .tags(post.getTags())
//                .likes(post.getLikes())
//                .comments(post.getComments())
//                .build();
    }

    @Override
    public PostDto findPostById(String id) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFondException(id));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto removePost(String id) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFondException(id));
        forumRepository.deleteById(id);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto updatePost(String id, ContentDto contentDto) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFondException(id));
        if (contentDto.getContent() != null) post.setContent(contentDto.getContent());
        if (contentDto.getTitle() != null) post.setTitle(contentDto.getTitle());
        if (contentDto.getTags() != null) post.setTags(contentDto.getTags());
        forumRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public boolean addLikeToPost(String id, String principalName) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFondException(id));

        if (!post.getUsersSetAlreadyLike().contains(principalName)) {
            post.setLikes(post.getLikes() + 1);
        }
        post.getUsersSetAlreadyLike().add(principalName);
        forumRepository.save(post);
        return true;
    }

    @Override
    public PostDto addCommentToPost(String id, String author, MessageDto message) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFondException(id));
        Comment comment = new Comment(author, message.getMessage());
        post.getComments().add(comment);
        forumRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> findPostsByAuthor(String author) {
//        long t1 = System.currentTimeMillis();
        List<PostDto> res = forumRepository.findPostsByAuthor(author)
                .map(p -> modelMapper.map(p, PostDto.class))
                .collect(Collectors.toList());
//        long t2 = System.currentTimeMillis();
//        log.info("duration = {}",  (t2-t1));
        return res;
    }

    @Override
    public List<PostDto> findPostsByTags(List<String> tags) {
        return forumRepository.findPostsByTags(tags)
                .map(p -> modelMapper.map(p, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostsByDateCreated(DateCreatedDto date) {
        return forumRepository.findPostByDateCreatedBetween(date.getDateFrom(), date.getDateTo())
                .map(p -> modelMapper.map(p, PostDto.class))
                .collect(Collectors.toList());
    }
}
