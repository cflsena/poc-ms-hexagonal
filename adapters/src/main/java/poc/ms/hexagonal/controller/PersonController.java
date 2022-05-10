package poc.ms.hexagonal.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poc.ms.hexagonal._shared.mapper.PersonMapper;
import poc.ms.hexagonal.api.PersonContract;
import poc.ms.hexagonal.application.port.in.PersonServicePort;
import poc.ms.hexagonal.domain.Person;
import poc.ms.hexagonal.rest.PersonRestContract;
import poc.ms.hexagonal.rest.dto.PersonRequest;
import poc.ms.hexagonal.rest.dto.PersonResponse;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("persons")
@RestController
@RequiredArgsConstructor
@Tag(name = "Pessoas", description = "API para cadastro de pessoas")
public class PersonController implements PersonRestContract {

    private final PersonServicePort personServicePort;

    @Operation(summary = "Endpoint para consultar uma pessoa por id", description = "Necess\u00E1rio informar um UUID v\u00E1lido que " +
            "corresponda a uma pessoa cadastrada previamente", tags = {"Pessoas"})
    @GetMapping(path = "/{personId}")
    public ResponseEntity<PersonResponse> findById(@PathVariable UUID personId) {
        Person personFound = personServicePort.findById(personId);
        return ResponseEntity.ok(PersonMapper.MAPPER.toDTO(personFound));
    }

    @Operation(summary = "Endpoint para criar uma pessoa", tags = {"Pessoas"})
    @PostMapping
    public PersonResponse create(@RequestBody @Valid PersonRequest request) {
        Person personToCreate = PersonMapper.MAPPER.toEntity(request);
        Person personCreated = personServicePort.create(personToCreate);
        return PersonMapper.MAPPER.toDTO(personCreated);
    }

    @Operation(summary = "Endpoint para atualizar os atributos de uma pessoa", description = "Necess\u00E1rio informar um UUID v\u00E1lido que " +
            "corresponda a uma pessoa cadastrada previamente", tags = {"Pessoas"})
    @PutMapping(path = "/{personId}")
    public void update(@PathVariable UUID personId,
                                       @RequestBody @Valid PersonRequest request) {
        Person personToUpdate = PersonMapper.MAPPER.toEntity(request);
        personServicePort.update(personId, personToUpdate);
    }

    @Operation(summary = "Endpoint para deletar uma pessoa", description = "Necess\u00E1rio informar um UUID v\u00E1lido que " +
            "corresponda a uma pessoa cadastrada previamente", tags = {"Pessoas"})
    @DeleteMapping(path = "/{personId}")
    public void delete(@PathVariable UUID personId) {
        personServicePort.delete(personId);
    }

}
