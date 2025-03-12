package net.opensource.stringtransformer.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException responseStatusException) {
        log.debug("User caused exception caught", responseStatusException);
        return ResponseEntity.status(responseStatusException.getStatusCode()).body(responseStatusException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        log.error("Unrecognized exception caught", exception);
        return ResponseEntity.status(HttpStatusCode.valueOf(500)).body("Something went wrong :(");
    }
}
