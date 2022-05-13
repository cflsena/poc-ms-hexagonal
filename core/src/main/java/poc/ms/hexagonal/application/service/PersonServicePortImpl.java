package poc.ms.hexagonal.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import poc.ms.hexagonal._shared.messages.Messages;
import poc.ms.hexagonal.application.port.in.PersonServicePort;
import poc.ms.hexagonal.application.port.out.PersonRepositoryPort;
import poc.ms.hexagonal.assertion.Assert;
import poc.ms.hexagonal.domain.Person;
import poc.ms.hexagonal.exception.BusinessException;

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
        Assert.isTrue(person.isEmpty(), new BusinessException(Messages.PERSON_NOT_FOUND));
        log.info("Person {} successfully found", personId);
        return person.get();
    }

    @Override
    public Person create(final Person person) {
        log.info("Creating person {}", person);
        final Optional<Person> personFound = personRepositoryPort.findByName(person.name());
        Assert.isTrue(personFound.isPresent(), new BusinessException(Messages.PERSON_ALREADY_EXISTS));
        final Optional<Person> personSaved = personRepositoryPort.create(person);
        Assert.isTrue(personFound.isEmpty(), new BusinessException(Messages.PERSON_NOT_CREATED));
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
