package poc.ms.hexagonal._mock;

import poc.ms.hexagonal.domain.Person;

import java.util.UUID;

public final class MockPerson {

    private MockPerson(){}

    public static Person mockValidPersonWithAllArgs(){
        return new Person (UUID.fromString("5bbac0be-1c50-40f2-810a-60bd6c82fc5b"), "Test");
    }

    public static Person mockValidPersonOnlyWithName(){
        return new Person (null, "Test");
    }

}
