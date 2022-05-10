package poc.ms.hexagonal.application.port.out;

import poc.ms.hexagonal.domain.Person;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepositoryPort {
    Optional<Person> findById(final UUID personId);
    Optional<Person> findByName(final String name);
    Optional<Person> create(final Person person);
    void update(final UUID personId, final Person person);
    void delete(final UUID personId);
}
