package telran.security.context;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserProfile {
    String login;
    String password;
    @Singular
    Set<String> roles;
}
