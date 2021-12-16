package telran.accountservise.dto;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor // для того, чтобы было можно ни чего не передавать
@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistException extends RuntimeException{
    private static final long serialVersionUID = 5733765225604672320L;
    public UserAlreadyExistException(String login) {
        super("User with id " + login + " ALREADY EXIST");
    }
}
