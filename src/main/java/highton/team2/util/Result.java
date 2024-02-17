package highton.team2.util;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class Result {
    private final int status;
    private final String data;

    public Result(int status, String data) {
        this.status = status;
        this.data = data;
    }

    public static ResponseEntity<Result> of(HttpStatus httpStatus, String message) {
        Result result = new Result(httpStatus.value(), message);
        return new ResponseEntity<>(result, httpStatus);
    }
}
