package telran.forumservice.dto;

import java.time.LocalDateTime;

public class CommentDto {
    String user;
    String message;
    LocalDateTime dateCreated;
    Integer likes;
}
