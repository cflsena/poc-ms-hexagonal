package poc.ms.hexagonal._shared.exception.common;

import lombok.Getter;

import java.io.Serial;

@Getter
public abstract class BaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5850142575059154255L;
    private final Error error;

    protected BaseException(final String errorMessage) {
        super(errorMessage);
        this.error = new Error(errorMessage);
    }

}
