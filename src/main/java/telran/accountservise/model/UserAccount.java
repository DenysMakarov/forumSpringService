package telran.accountservise.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"login"})
@Document(collection = "users")
public class UserAccount {
    @Id
    String login;
    String firstName;
    String lastName;
    String password;
    Set<String> roles = new HashSet<>();
    LocalDate passwordExpDate = LocalDate.now().plusDays(30);



    public UserAccount(String login, String firstName, String lastName, String password) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
}

