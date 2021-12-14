package telran.forumservice.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.forumservice.dto.PostDto;
import telran.forumservice.model.Post;

public interface ForumMongoRepository extends MongoRepository<Post, String> {

}
