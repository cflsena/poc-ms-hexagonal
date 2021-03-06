package poc.ms.hexagonal.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poc.ms.hexagonal.dto.PersonRequestDTO;
import poc.ms.hexagonal.dto.PersonResponseDTO;
import poc.ms.hexagonal.dto.response.ServerResponseDTO;

import javax.validation.Valid;
import java.util.UUID;

public interface PersonApiContract {
    @GetMapping(path = "/{personId}")
    ResponseEntity<ServerResponseDTO<PersonResponseDTO>> findById(@PathVariable("personId") UUID personId);

    @PostMapping
    ResponseEntity<ServerResponseDTO<PersonResponseDTO>> create(@RequestBody @Valid PersonRequestDTO request);

    @PutMapping(path = "/{personId}")
    ResponseEntity<Void> update(@PathVariable("personId") UUID personId, @RequestBody @Valid PersonRequestDTO request);

    @DeleteMapping(path = "/{personId}")
    ResponseEntity<Void> delete(@PathVariable("personId") UUID personId);
}
