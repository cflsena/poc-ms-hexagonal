package poc.ms.hexagonal._shared.exception;

import poc.ms.hexagonal._shared.exception.common.BaseException;

import java.io.Serial;

public class DomainException extends BaseException {
    @Serial
    private static final long serialVersionUID = -1254492652807622184L;
    public DomainException(final String reason){
        super(reason);
    }
}
