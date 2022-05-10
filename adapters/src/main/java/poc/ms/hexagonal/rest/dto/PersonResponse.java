package poc.ms.hexagonal.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonResponse {

    @Schema(description = "Campo contendo o id da pessoa", example = "3821e915-05c0-4f6c-a85d-6a0a86386df5", type = "UUID")
    private UUID id;

    @Schema(description = "Campo contendo o nome da pessoa", example = "Cleiton Sena")
    private String name;

}
