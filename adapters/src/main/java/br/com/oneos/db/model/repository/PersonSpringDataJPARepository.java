package br.com.oneos.db.model.repository;

import br.com.oneos.db.model.ExampleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonSpringDataJPARepository extends JpaRepository<ExampleModel, UUID>, RevisionRepository<ExampleModel, UUID, Long> { }
