package poc.ms.hexagonal.domain;

import poc.ms.hexagonal._shared.messages.Messages;
import poc.ms.hexagonal.assertion.Assert;
import poc.ms.hexagonal.exception.DomainException;

import java.util.UUID;

public record Person(UUID id, String name) {

    public Person {
        validateName(name);
    }

    private static void validateName(final String name) {
        Assert.isNotEmpty(name, new DomainException(Messages.PERSON_IS_MANDATORY));
    }
}
