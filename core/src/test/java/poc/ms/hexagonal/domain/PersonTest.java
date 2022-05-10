package poc.ms.hexagonal.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import poc.ms.hexagonal._shared.exception.DomainException;
import poc.ms.hexagonal._mock.MockPerson;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test for Person")
class PersonTest {

    @Test
    @DisplayName("Should create new Person")
    void test01() {

        String expectedName = MockPerson.mockValidPersonWithAllArgs().name();
        UUID expectedId = MockPerson.mockValidPersonWithAllArgs().id();

        Person person = new Person(expectedId, expectedName);

        assertNotNull(person);
        assertNotNull(person.id());
        assertNotNull(person.name());
        assertEquals(expectedName, person.name());
        assertEquals(expectedId, person.id());
    }

    @Test
    @DisplayName("Should throw DomainException when person name is invalid")
    void test02() {

        final DomainException thrown = assertThrowsExactly(DomainException.class, () ->
                new Person(null, ""), "Person name is mandatory");

        assertEquals("Person name is mandatory", thrown.getMessage());

    }

}
