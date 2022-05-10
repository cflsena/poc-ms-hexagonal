package poc.ms.hexagonal._shared.exception;

import poc.ms.hexagonal._shared.exception.common.BaseException;

import java.io.Serial;

public class BusinessException extends BaseException {
    @Serial
    private static final long serialVersionUID = -571260232448921077L;
    public BusinessException(final String reason) {
        super(reason);
    }
}
