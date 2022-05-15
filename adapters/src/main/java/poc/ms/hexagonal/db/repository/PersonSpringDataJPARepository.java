package poc.ms.hexagonal.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import poc.ms.hexagonal.db.model.PersonModel;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonSpringDataJPARepository extends JpaRepository<PersonModel, UUID>, RevisionRepository<PersonModel, UUID, Long> {
    Optional<PersonModel> findByName(final String name);
}
