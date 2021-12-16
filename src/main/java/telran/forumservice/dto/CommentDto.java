package telran.forumservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import telran.forumservice.model.MyDateFormatter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentDto {
    String user;
    String message;
    @JsonFormat(pattern = MyDateFormatter.DATE_FORMATTER)
    LocalDateTime dateCreated;
    int likes;
}
