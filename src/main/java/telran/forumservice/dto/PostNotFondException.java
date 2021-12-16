package telran.forumservice.dto;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor // для того, чтобы если ни чего не передавать
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFondException extends RuntimeException{
    private static final long serialVersionUID = 1234;
    public PostNotFondException(String id) {
        super("Post with id " + id + " NOT FOUND");
    }
}
