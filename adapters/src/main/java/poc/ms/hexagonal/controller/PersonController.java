package poc.ms.hexagonal.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poc.ms.hexagonal.api.PersonApiContract;
import poc.ms.hexagonal.application.port.in.PersonServicePort;
import poc.ms.hexagonal.db.model.PersonModel;
import poc.ms.hexagonal.db.repository.PersonSpringDataJPARepository;
import poc.ms.hexagonal.dto.PersonRequestDTO;
import poc.ms.hexagonal.dto.PersonResponseDTO;
import poc.ms.hexagonal.dto.response.ServerResponseDTO;
import poc.ms.hexagonal.dto.response.ServerResponseListDTO;
import poc.ms.hexagonal.mapper.PersonMapper;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("persons")
@RestController
@RequiredArgsConstructor
@Tag(name = "Pessoas", description = "API para cadastro de pessoas")
public class PersonController implements PersonApiContract {

    private final PersonServicePort personServicePort;
    private final PersonSpringDataJPARepository repository;

    @Override
    @Operation(summary = "Endpoint para consultar uma pessoa por id", description = "Necess\u00E1rio informar um UUID v\u00E1lido que " +
            "corresponda a uma pessoa cadastrada previamente", tags = {"Pessoas"})
    public ResponseEntity<ServerResponseDTO<PersonResponseDTO>> findById(@PathVariable UUID personId) {
        final var personFound = personServicePort.findById(personId);
        final var personResponseDTO = PersonMapper.MAPPER.toDTO(personFound);
        return ResponseEntity.ok(new ServerResponseDTO<>(personResponseDTO));
    }

    @Override
    @Operation(summary = "Endpoint para criar uma pessoa", tags = {"Pessoas"})
    public ResponseEntity<ServerResponseDTO<PersonResponseDTO>> create(@RequestBody @Valid PersonRequestDTO request) {
        final var personToCreate = PersonMapper.MAPPER.toEntity(request);
        final var personCreated = personServicePort.create(personToCreate);
        final var personResponseDTO = PersonMapper.MAPPER.toDTO(personCreated);
        return new ResponseEntity<>(new ServerResponseDTO<>(personResponseDTO), HttpStatus.CREATED);
    }

    @Override
    @Operation(summary = "Endpoint para atualizar os atributos de uma pessoa", description = "Necess\u00E1rio informar um UUID v\u00E1lido que " +
            "corresponda a uma pessoa cadastrada previamente", tags = {"Pessoas"})
    public ResponseEntity<Void> update(@PathVariable UUID personId,
                                       @RequestBody @Valid PersonRequestDTO request) {
        final var personToUpdate = PersonMapper.MAPPER.toEntity(request);
        personServicePort.update(personId, personToUpdate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @Operation(summary = "Endpoint para deletar uma pessoa", description = "Necess\u00E1rio informar um UUID v\u00E1lido que " +
            "corresponda a uma pessoa cadastrada previamente", tags = {"Pessoas"})
    public ResponseEntity<Void> delete(@PathVariable UUID personId) {
        personServicePort.delete(personId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<ServerResponseListDTO<PersonModel>> findAll() {
        final var all = repository.findAll();
        return ResponseEntity.ok(new ServerResponseListDTO<>(all));
    }

}
