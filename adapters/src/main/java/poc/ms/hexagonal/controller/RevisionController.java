package poc.ms.hexagonal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poc.ms.hexagonal.db.repository.PersonSpringDataJPARepository;
import poc.ms.hexagonal.dto.response.ServerResponseListDTO;

import java.util.UUID;

@RequestMapping("revisions")
@RestController
@RequiredArgsConstructor
public class RevisionController {

    private final PersonSpringDataJPARepository repository;

    @GetMapping("/persons/{personId}")
    public ResponseEntity<ServerResponseListDTO<String>> revisionByPersonId(@PathVariable("personId") UUID personId) {
        final var revisions = repository.findRevisions(personId)
                .stream()
                .map(Object::toString)
                .toList();
        return ResponseEntity.ok(new ServerResponseListDTO<>(revisions));
    }

}
