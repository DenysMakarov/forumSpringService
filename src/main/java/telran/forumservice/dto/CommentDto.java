package telran.forumservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(of = {"user", "dateCreated"})
public class CommentDto {
    String user;
    @Setter
    String message;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime dateCreated;
    int likes;

    public CommentDto(String user, String message) {
        this.user = user;
        this.message = message;
        this.dateCreated = LocalDateTime.now();
        this.likes = 0;
    }
}
