package poc.ms.hexagonal._mock;

import poc.ms.hexagonal.domain.Person;
import poc.ms.hexagonal.dto.PersonRequestDTO;

public final class MockPerson {

    private MockPerson(){}

    public static Person mockValidPersonWithAllArgs(){
        return new Person(MockUUID.mockValidUUIDToPerson(), "Test");
    }

    public static Person mockValidPersonOnlyWithName(){
        return new Person(null, "Test");
    }

    public static PersonRequestDTO mockValidPersonRequestToCreate(){
        return new PersonRequestDTO("Test");
    }

    public static PersonRequestDTO mockValidPersonRequestToUpdate(){
        return new PersonRequestDTO("New name");
    }

}
