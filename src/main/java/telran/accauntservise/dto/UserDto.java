package telran.accauntservise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserDto {
    String login;
    String firstName;
    String lastName;
    Set<String> roles;
}
