package telran.forumservice.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ContentDto {
    String title;
    String content;
    Set<String> tags;
}
