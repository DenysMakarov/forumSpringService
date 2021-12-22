package telran.accountservise.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.accountservise.model.UserAccount;

public interface UserMongoRepository extends MongoRepository<UserAccount, String> {
    UserAccount findByLoginAndPassword(String login, String password);
}
