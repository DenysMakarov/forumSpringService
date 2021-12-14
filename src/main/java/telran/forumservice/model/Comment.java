package telran.forumservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import telran.forumservice.dto.CommentDto;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(of = {"user", "dateCreated"})
public class Comment {
    String user;
    @Setter
    String message;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime dateCreated;
    int likes;

    public Comment(String user, String message) {
        this.user = user;
        this.message = message;
        this.dateCreated = LocalDateTime.now();
        this.likes = 0;
    }
}
