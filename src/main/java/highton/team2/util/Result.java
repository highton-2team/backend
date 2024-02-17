package highton.team2.util;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class Result<T> {
    private final int status;
    private final T data;

    public Result(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public static <T> ResponseEntity<Result<T>> of(HttpStatus httpStatus, T message) {
        Result<T> result = new Result<>(httpStatus.value(), message);
        return new ResponseEntity<>(result, httpStatus);
    }
}
