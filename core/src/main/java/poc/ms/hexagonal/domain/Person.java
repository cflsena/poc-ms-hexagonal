package poc.ms.hexagonal.domain;

import lombok.Getter;
import lombok.ToString;
import poc.ms.hexagonal._shared.exception.DomainException;

import java.util.UUID;

public record Person(UUID id, String name) {

    public Person {
        validateName(name);
    }

    private static void validateName(final String name) {
        if (name == null || name.isEmpty()) {
            throw new DomainException("Person name is mandatory");
        }
    }
}
