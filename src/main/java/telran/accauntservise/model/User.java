package telran.accauntservise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    String login;
    String firstName;
    String lastName;
    String password;
    Set<String> roles;
}

