package poc.ms.hexagonal.api;

import poc.ms.hexagonal.dto.PersonRequest;
import poc.ms.hexagonal.dto.PersonResponse;

import java.util.UUID;

public interface PersonContract {
    PersonResponse findById(UUID personId);
    PersonResponse create(PersonRequest request);
    void update(UUID personId, PersonRequest request);
    void delete(UUID personId);
}
