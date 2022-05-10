package poc.ms.hexagonal.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import poc.ms.hexagonal._shared.exception.BusinessException;
import poc.ms.hexagonal.application.port.in.PersonServicePort;
import poc.ms.hexagonal.application.port.out.PersonRepositoryPort;
import poc.ms.hexagonal.domain.Person;

import javax.inject.Named;
import java.util.Optional;
import java.util.UUID;

@Named
@RequiredArgsConstructor
@Slf4j
public class PersonServicePortImpl implements PersonServicePort {

    private final PersonRepositoryPort personRepositoryPort;

    @Override
    public Person findById(final UUID personId) {
        log.info("Searching for person {}", personId);
        final Optional<Person> person = personRepositoryPort.findById(personId);
        if (person.isEmpty()) {
            throw new BusinessException("Person not found");
        }
        log.info("Person {} successfully found", personId);
        return person.get();
    }

    @Override
    public Person create(final Person person) {

        log.info("Creating person {}", person);
        final Optional<Person> personFound = personRepositoryPort.findByName(person.name());

        if (personFound.isPresent()) {
            throw new BusinessException("Person already exists");
        }

        final Optional<Person> personSaved = personRepositoryPort.create(person);

        if (personSaved.isEmpty()) {
            throw new BusinessException("Person not created");
        }

        log.info("Person {} successfully created", person);
        return personSaved.get();
    }

    @Override
    public void update(final UUID personId, final Person person) {
        log.info("Updating person {}", person);
        findById(personId);
        personRepositoryPort.update(personId, person);
        log.info("Person {} successfully updated", person);
    }

    @Override
    public void delete(final UUID personId) {
        log.info("Deleting person {}", personId);
        findById(personId);
        personRepositoryPort.delete(personId);
        log.info("Person {} successfully deleted", personId);
    }

}
