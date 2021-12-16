package telran.forumservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import telran.forumservice.model.MyDateFormatter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DateCreatedDto {
    @JsonFormat(pattern = MyDateFormatter.DATE_FORMATTER)
    LocalDate DateFrom;
    @JsonFormat(pattern = MyDateFormatter.DATE_FORMATTER)
    LocalDate DateTo;
}
