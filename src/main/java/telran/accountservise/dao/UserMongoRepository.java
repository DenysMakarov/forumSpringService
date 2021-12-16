package telran.accountservise.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.accountservise.model.User;

public interface UserMongoRepository extends MongoRepository<User, String> {
    User findByLoginAndPassword(String login, String password);
}
