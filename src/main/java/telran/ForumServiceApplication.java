package telran;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import telran.accountservise.dao.UserMongoRepository;
import telran.accountservise.model.User;

import java.util.Locale;

@SpringBootApplication
public class ForumServiceApplication implements CommandLineRunner {
    @Autowired
    UserMongoRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(ForumServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception { // если нет админа добавить его
        if (!repository.existsById("admin")){
            String password = BCrypt.hashpw("admin", BCrypt.gensalt());
            User admin = new User("admin", "", "", password);
            admin.getRoles().add("user".toUpperCase());
            admin.getRoles().add("moderator".toUpperCase());
            admin.getRoles().add("administrator".toUpperCase());
            repository.save(admin);
        }
    }
}
