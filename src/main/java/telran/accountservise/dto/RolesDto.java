package telran.accountservise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RolesDto {
    String login;
    Set<String> roles;
}