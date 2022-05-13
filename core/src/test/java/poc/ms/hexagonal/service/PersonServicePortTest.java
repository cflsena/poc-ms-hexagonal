package poc.ms.hexagonal.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import poc.ms.hexagonal._mock.MockPerson;
import poc.ms.hexagonal.application.port.out.PersonRepositoryPort;
import poc.ms.hexagonal.application.service.PersonServicePortImpl;
import poc.ms.hexagonal.domain.Person;
import poc.ms.hexagonal.exception.BusinessException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test for Person Service Port")
class PersonServicePortTest {

    @InjectMocks
    private PersonServicePortImpl personServicePort;

    @Mock
    private PersonRepositoryPort personRepositoryPort;

    @Test
    @DisplayName("Should return Person")
    void test01() {

        UUID expectedId = MockPerson.mockValidPersonWithAllArgs().id();
        when(personRepositoryPort.findById(any())).thenReturn(Optional.of(MockPerson.mockValidPersonWithAllArgs()));

        Person person = personServicePort.findById(expectedId);

        assertNotNull(person);
        assertEquals(person.id(), expectedId);
        assertNotNull(person.name());

    }

    @Test
    @DisplayName("Should throw BusinessException when person not found")
    void test02() {

        when(personRepositoryPort.findById(any())).thenReturn(Optional.empty());

        final BusinessException thrown = assertThrowsExactly(BusinessException.class, () ->
            personServicePort.findById(any()), "Person not found");

        assertEquals("Person not found", thrown.getMessage());

    }

    @Test
    @DisplayName("Should create Person")
    void test03() {

        Person mockValidPersonOnlyWithName = MockPerson.mockValidPersonOnlyWithName();
        String expectedName = mockValidPersonOnlyWithName.name();
        when(personRepositoryPort.findByName(any())).thenReturn(Optional.empty());
        when(personRepositoryPort.create(any())).thenReturn(Optional.of(MockPerson.mockValidPersonWithAllArgs()));

        Person person = personServicePort.create(mockValidPersonOnlyWithName);

        assertNotNull(person);
        assertNotNull(person.id());
        assertEquals(person.name(), expectedName);

    }

    @Test
    @DisplayName("Should throw BusinessException when person already exists")
    void test04() {

        Person mockValidPersonOnlyWithName = MockPerson.mockValidPersonOnlyWithName();

        when(personRepositoryPort.findByName(any())).thenReturn(Optional.of(MockPerson.mockValidPersonWithAllArgs()));

        BusinessException thrown = assertThrowsExactly(BusinessException.class, () ->
                personServicePort.create(mockValidPersonOnlyWithName), "Person already exists");

        assertEquals("Person already exists", thrown.getMessage());

    }

    @Test
    @DisplayName("Should throw BusinessException when person is not created")
    void test05() {

        Person mockValidPersonOnlyWithName = MockPerson.mockValidPersonOnlyWithName();

        when(personRepositoryPort.findByName(any())).thenReturn(Optional.empty());
        when(personRepositoryPort.create(any())).thenReturn(Optional.empty());

        BusinessException thrown = assertThrowsExactly(BusinessException.class, () ->
                personServicePort.create(mockValidPersonOnlyWithName), "Person not created");

        assertEquals("Person not created", thrown.getMessage());

    }

    @Test
    @DisplayName("Should update Person")
    void test06() {

        Person mockValidPersonWithAllArgs = MockPerson.mockValidPersonWithAllArgs();
        UUID personId = mockValidPersonWithAllArgs.id();
        when(personRepositoryPort.findById(any())).thenReturn(Optional.of(MockPerson.mockValidPersonWithAllArgs()));
        lenient().doNothing().when(personRepositoryPort).update(personId, mockValidPersonWithAllArgs);

        assertDoesNotThrow(() -> personServicePort.update(personId, mockValidPersonWithAllArgs));

    }

    @Test
    @DisplayName("Should delete Person")
    void test07() {

        Person mockValidPersonWithAllArgs = MockPerson.mockValidPersonWithAllArgs();
        UUID personId = mockValidPersonWithAllArgs.id();
        when(personRepositoryPort.findById(any())).thenReturn(Optional.of(MockPerson.mockValidPersonWithAllArgs()));
        lenient().doNothing().when(personRepositoryPort).delete(personId);

        assertDoesNotThrow(() -> personServicePort.delete(personId));

    }

}
