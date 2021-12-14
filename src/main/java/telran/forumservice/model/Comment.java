package telran.forumservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

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
        dateCreated = LocalDateTime.now();
        this.user = user;
        this.message = message;
    }

//    public void addLike() {
//        likes++;
//    }
}
