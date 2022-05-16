package poc.ms.hexagonal.db;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import poc.ms.hexagonal.application.port.out.ExampleRepositoryPort;
import poc.ms.hexagonal.db.repository.PersonSpringDataJPARepository;

@Component
@RequiredArgsConstructor
public class PersonRepositoryPortImpl implements ExampleRepositoryPort {
    private final PersonSpringDataJPARepository repository;
}
