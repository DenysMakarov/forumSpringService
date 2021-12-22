package telran;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import telran.accountservise.dao.UserMongoRepository;
import telran.accountservise.model.UserAccount;

@SpringBootApplication
public class ForumServiceApplication implements CommandLineRunner {
    @Autowired
    UserMongoRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ForumServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception { // если нет админа добавить его
        if (!repository.existsById("admin")){
            String password = passwordEncoder.encode("admin");
            UserAccount admin = new UserAccount("admin", "", "", password);
            admin.getRoles().add("user".toUpperCase());
            admin.getRoles().add("moderator".toUpperCase());
            admin.getRoles().add("administrator".toUpperCase());
            repository.save(admin);
        }
    }
}
