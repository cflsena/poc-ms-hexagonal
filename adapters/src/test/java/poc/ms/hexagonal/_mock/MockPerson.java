package poc.ms.hexagonal._mock;

import poc.ms.hexagonal.domain.Person;
import poc.ms.hexagonal.rest.dto.PersonRequest;

public final class MockPerson {

    private MockPerson(){}

    public static Person mockValidPersonWithAllArgs(){
        return new Person(MockUUID.mockValidUUIDToPerson(), "Test");
    }

    public static Person mockValidPersonOnlyWithName(){
        return new Person(null, "Test");
    }

    public static PersonRequest mockValidPersonRequestToCreate(){
        return new PersonRequest("Test");
    }

    public static PersonRequest mockValidPersonRequestToUpdate(){
        return new PersonRequest("New name");
    }

}
