package telran.forumservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DateCreatedDto {
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate DateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate DateTo;

}
