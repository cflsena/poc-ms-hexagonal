package poc.ms.hexagonal.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import poc.ms.hexagonal._common.BaseIT;
import poc.ms.hexagonal.application.port.out.ExampleRepositoryPort;
import poc.ms.hexagonal.db.model.ExampleModel;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("Integration Test for Example Controller")
@ActiveProfiles("test")
class ExampleControllerIT extends BaseIT {

    @Autowired
    private ExampleRepositoryPort repositoryPort;

    @Test
    @DisplayName("Create your E2E test from this controller")
    void test01() {
        final var uuid = UUID.randomUUID();
        final var exampleModel = new ExampleModel(uuid, "test");
        assertEquals(uuid, exampleModel.getId());
        assertEquals("test", exampleModel.getName());
    }

}
