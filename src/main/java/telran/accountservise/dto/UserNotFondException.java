package telran.accountservise.dto;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor // для того, чтобы если ни чего не передавать
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFondException extends RuntimeException{
    private static final long serialVersionUID = 2600418174200151797L;
    public UserNotFondException(String login) {
        super("User with id " + login + " NOT FOUND");
    }
}
