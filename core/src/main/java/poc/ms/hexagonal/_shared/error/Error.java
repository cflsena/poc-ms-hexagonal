package poc.ms.hexagonal._shared.error;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Error {

    private final LocalDateTime dateTime;
    private final String message;

    public Error(final String message) {
        this.dateTime = LocalDateTime.now();
        this.message = message;
    }

}
