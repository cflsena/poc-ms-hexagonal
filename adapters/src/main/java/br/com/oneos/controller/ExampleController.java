package br.com.oneos.controller;

import br.com.oneos.api.ExampleApiContract;
import br.com.oneos.application.port.service.in.ExampleServicePort;
import br.com.oneos.db.model.repository.PersonSpringDataJPARepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("persons")
@RestController
@RequiredArgsConstructor
@Tag(name = "Pessoas", description = "API para cadastro de pessoas")
public class ExampleController implements ExampleApiContract {

    private final ExampleServicePort personServicePort;
    private final PersonSpringDataJPARepository repository;

}
