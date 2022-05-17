package poc.ms.hexagonal.domain;

import br.com.oneos.domain.Example;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test for Example")
class ExampleTest {

    @Test
    @DisplayName("Create your unit test for this domain class")
    void test01() {
        final var uuid = UUID.randomUUID();
        final var example = new Example(uuid);
        assertEquals(uuid, example.id());
    }

}
