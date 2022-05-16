package poc.ms.hexagonal._shared.messages;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import poc.ms.hexagonal.message.MessageTemplate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Messages implements MessageTemplate {

    CHANGE_THIS_MESSAGE("ONEOSEXP-001", "validation.change.this.message");

    private final String code;
    private final String message;

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }

}
