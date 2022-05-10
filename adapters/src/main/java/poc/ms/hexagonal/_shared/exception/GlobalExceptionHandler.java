package poc.ms.hexagonal._shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import poc.ms.hexagonal._shared.error.Error;
import poc.ms.hexagonal._shared.exception.common.BaseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Error> baseException(final BaseException ex) {
        return new ResponseEntity<>(new Error(ex.getError().getMessage()), HttpStatus.BAD_REQUEST);
    }

}
