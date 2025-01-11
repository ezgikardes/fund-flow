package github.ezgikardes.loanapi.exceptions;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder // builder pattern
public class ApiExceptionResponse {
    private String message;
    private int status;
    private long timestamp;
}
