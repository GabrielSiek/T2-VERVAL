package SGP_Backend.SGP_Backend.exceptions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class ApiErrorDTO {

    private Integer statusCode;
    private String error;
    private List<String> messages;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String detail;

    public ApiErrorDTO(Integer statusCode, String error, List<String> messages, String detail) {
        this.statusCode = statusCode;
        this.error = error;
        this.messages = messages;
        this.detail = detail;
    }

}