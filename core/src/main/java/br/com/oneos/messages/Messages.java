package br.com.oneos.messages;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import br.com.oneos.message.MessageTemplate;

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
