package telran.forumservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Builder
@Getter
public class Post {
    @Id
    String id;
    @Setter
    String title;
    @Setter
    String content;
    @Setter
    String author;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime dateCreated = LocalDateTime.now();
    @Setter
    Set<String> tags;
    @Setter
    int likes;
    Set<Comment> comments = new HashSet<>();
    Set<String> usersSetAlreadyLike = new HashSet<>();
}
