package poc.ms.hexagonal.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poc.ms.hexagonal.api.ExampleApiContract;
import poc.ms.hexagonal.application.port.in.ExampleServicePort;
import poc.ms.hexagonal.db.repository.PersonSpringDataJPARepository;

@RequestMapping("persons")
@RestController
@RequiredArgsConstructor
@Tag(name = "Pessoas", description = "API para cadastro de pessoas")
public class ExampleController implements ExampleApiContract {

    private final ExampleServicePort personServicePort;
    private final PersonSpringDataJPARepository repository;

}
