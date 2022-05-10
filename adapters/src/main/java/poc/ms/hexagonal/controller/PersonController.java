package poc.ms.hexagonal.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poc.ms.hexagonal._shared.mapper.PersonMapper;
import poc.ms.hexagonal.api.PersonApiContract;
import poc.ms.hexagonal.application.port.in.PersonServicePort;
import poc.ms.hexagonal.domain.Person;
import poc.ms.hexagonal.dto.PersonRequestDTO;
import poc.ms.hexagonal.dto.PersonResponseDTO;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("persons")
@RestController
@RequiredArgsConstructor
@Tag(name = "Pessoas", description = "API para cadastro de pessoas")
public class PersonController implements PersonApiContract {

    private final PersonServicePort personServicePort;

    @Operation(summary = "Endpoint para consultar uma pessoa por id", description = "Necess\u00E1rio informar um UUID v\u00E1lido que " +
            "corresponda a uma pessoa cadastrada previamente", tags = {"Pessoas"})
    public ResponseEntity<PersonResponseDTO> findById(@PathVariable UUID personId) {
        Person personFound = personServicePort.findById(personId);
        return ResponseEntity.ok(PersonMapper.MAPPER.toDTO(personFound));
    }

    @Operation(summary = "Endpoint para criar uma pessoa", tags = {"Pessoas"})
    public ResponseEntity<PersonResponseDTO> create(@RequestBody @Valid PersonRequestDTO request) {
        Person personToCreate = PersonMapper.MAPPER.toEntity(request);
        Person personCreated = personServicePort.create(personToCreate);
        return new ResponseEntity<>(PersonMapper.MAPPER.toDTO(personCreated), HttpStatus.CREATED);
    }

    @Operation(summary = "Endpoint para atualizar os atributos de uma pessoa", description = "Necess\u00E1rio informar um UUID v\u00E1lido que " +
            "corresponda a uma pessoa cadastrada previamente", tags = {"Pessoas"})
    public ResponseEntity<Void> update(@PathVariable UUID personId,
                                       @RequestBody @Valid PersonRequestDTO request) {
        Person personToUpdate = PersonMapper.MAPPER.toEntity(request);
        personServicePort.update(personId, personToUpdate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Endpoint para deletar uma pessoa", description = "Necess\u00E1rio informar um UUID v\u00E1lido que " +
            "corresponda a uma pessoa cadastrada previamente", tags = {"Pessoas"})
    public ResponseEntity<Void> delete(@PathVariable UUID personId) {
        personServicePort.delete(personId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
