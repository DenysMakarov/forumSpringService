package telran.forumservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DateCreatedDto {
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime from;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime to;

}
