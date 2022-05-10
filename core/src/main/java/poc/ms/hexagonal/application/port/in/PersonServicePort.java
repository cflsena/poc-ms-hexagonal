package poc.ms.hexagonal.application.port.in;


import poc.ms.hexagonal.domain.Person;

import java.util.UUID;

public interface PersonServicePort {
    Person findById(final UUID personId);
    Person create(final Person person);
    void update(final UUID personId, final Person person);
    void delete(final UUID personId);
}
