package telran.forumservice.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import telran.forumservice.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public interface ForumMongoRepository extends MongoRepository<Post, String> {
    @Query("{author: {$eq: ?0}}")
    Stream<Post> findPostsByAuthor(String author);

    @Query("{tags: {$in: ?0}}")
    Stream<Post> findPostsByTags(List<String> tags);

//    @Query("{dateCreated: {$gr: ?0}")
    Stream<Post> findPostByDateCreatedBetween(LocalDateTime from, LocalDateTime to);
}
