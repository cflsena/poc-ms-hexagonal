package poc.ms.hexagonal.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import poc.ms.hexagonal._common.BaseIT;
import poc.ms.hexagonal._mock.MockPerson;
import poc.ms.hexagonal.application.port.out.PersonRepositoryPort;
import poc.ms.hexagonal.domain.Person;
import poc.ms.hexagonal.dto.PersonRequestDTO;
import poc.ms.hexagonal.dto.PersonResponseDTO;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("Integration Test for Person Controller")
@ActiveProfiles("test")
class PersonControllerIT extends BaseIT {

    @Autowired
    private PersonRepositoryPort repositoryPort;

    @Test
    @DisplayName("findById returns Person when successful")
    void test01() {

        final Person personToBeSaved = MockPerson.mockValidPersonOnlyWithName();

        Optional<Person> personSaved = repositoryPort.create(personToBeSaved);

        assertTrue(personSaved.isPresent());

        final UUID expectedId = personSaved.get().id();
        final String expectedName = personSaved.get().name();

        ResponseEntity<PersonResponseDTO> response = testRestTemplate
                .getForEntity("/persons/{personId}", PersonResponseDTO.class, expectedId);

        assertNotNull(response);
        assertEquals(OK, response.getStatusCode());

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals(expectedId, response.getBody().getId());
        assertEquals(expectedName, response.getBody().getName());
    }

    @Test
    @DisplayName("create returns Person when successful")
    void test02() {

        final PersonRequestDTO personToBeSaved = MockPerson.mockValidPersonRequestToCreate();
        String expectedName = personToBeSaved.getName();

        ResponseEntity<PersonResponseDTO> response = testRestTemplate
                .postForEntity("/persons", personToBeSaved, PersonResponseDTO.class);

        assertNotNull(response);
        assertEquals(CREATED, response.getStatusCode());

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals(expectedName, response.getBody().getName());
    }

    @Test
    @DisplayName("update replace Person when successful")
    void test03() {

        final Person personToBeSaved = MockPerson.mockValidPersonOnlyWithName();

        Optional<Person> personSaved = repositoryPort.create(personToBeSaved);

        assertTrue(personSaved.isPresent());

        final UUID expectedId = personSaved.get().id();

        PersonRequestDTO PersonRequest = MockPerson.mockValidPersonRequestToUpdate();
        String expectedName = PersonRequest.getName();

        ResponseEntity<Void> response = testRestTemplate
                .exchange("/persons/{personId}", HttpMethod.PUT,
                        new HttpEntity<>(PersonRequest),
                        Void.class, expectedId);

        Optional<Person> personUpdated = repositoryPort.findById(expectedId);

        assertNotNull(personUpdated);
        assertTrue(personUpdated.isPresent());
        assertEquals(expectedId, personUpdated.get().id());
        assertEquals(expectedName, personUpdated.get().name());

        assertNotNull(response);
        assertEquals(NO_CONTENT, response.getStatusCode());

    }

    @Test
    @DisplayName("delete removes Person when successful")
    void test04() {

        Person personToBeSaved = MockPerson.mockValidPersonOnlyWithName();

        Optional<Person> personSaved = repositoryPort.create(personToBeSaved);

        assertTrue(personSaved.isPresent());

        final UUID personIdToExclude = personSaved.get().id();

        ResponseEntity<Void> response = testRestTemplate
                .exchange("/persons/{personId}", HttpMethod.DELETE,
                        null, Void.class, personIdToExclude);

        Optional<Person> personExcluded = repositoryPort.findById(personIdToExclude);

        assertNotNull(personExcluded);
        assertFalse(personExcluded.isPresent());

        assertNotNull(response);
        assertEquals(NO_CONTENT, response.getStatusCode());
    }

}
