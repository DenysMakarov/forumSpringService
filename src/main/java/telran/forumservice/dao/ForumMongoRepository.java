package telran.forumservice.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import telran.forumservice.dto.PostDto;
import telran.forumservice.model.Post;

import java.util.List;
import java.util.stream.Stream;

public interface ForumMongoRepository extends MongoRepository<Post, String> {
//    @Query(value = "{'author' : ${author}}")
    Stream<Post> findPostsByAuthor(String author);
}
