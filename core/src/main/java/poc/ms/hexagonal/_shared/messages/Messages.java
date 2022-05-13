package poc.ms.hexagonal._shared.messages;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import poc.ms.hexagonal.message.MessageTemplate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Messages implements MessageTemplate {

    PERSON_IS_MANDATORY("POCUSER-001", "validation.person.is.mandatory"),
    PERSON_NOT_FOUND("POCUSER-002", "validation.person.not.found"),
    PERSON_ALREADY_EXISTS("POCUSER-003", "validation.person.already.exists"),
    PERSON_NOT_CREATED("POCUSER-004", "validation.person.not.created");

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
