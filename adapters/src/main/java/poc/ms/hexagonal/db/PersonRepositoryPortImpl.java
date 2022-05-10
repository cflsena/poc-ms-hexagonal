package poc.ms.hexagonal.db;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import poc.ms.hexagonal.application.port.out.PersonRepositoryPort;
import poc.ms.hexagonal.db.model.PersonModel;
import poc.ms.hexagonal.db.repository.PersonSpringDataJPARepository;
import poc.ms.hexagonal.domain.Person;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PersonRepositoryPortImpl implements PersonRepositoryPort {

    private final PersonSpringDataJPARepository repository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Person> findById(final UUID personId) {
        Optional<PersonModel> personFound = repository.findById(personId);
        return personFound.map(fp -> new Person(fp.getId(), fp.getName()));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Person> findByName(final String name) {
        Optional<PersonModel> personFound = repository.findByName(name);
        return personFound.map(fp -> new Person(fp.getId(), fp.getName()));
    }

    @Transactional
    @Override
    public Optional<Person> create(final Person person) {
        PersonModel personToSave = PersonModel.builder().name(person.name()).build();
        PersonModel personSaved = repository.saveAndFlush(personToSave);
        Person newPerson = new Person(personSaved.getId(), personSaved.getName());
        return Optional.of(newPerson);
    }

    @Transactional
    @Override
    public void update(final UUID personId, final Person person) {
        final PersonModel personToUpdate = PersonModel.builder().id(personId).name(person.name()).build();
        repository.saveAndFlush(personToUpdate);
    }

    @Transactional
    @Override
    public void delete(final UUID personId) {
        repository.deleteById(personId);
    }
}
