package poc.ms.hexagonal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequestDTO {

    @NotBlank
    @Schema(description = "Campo contendo o nome da pessoa", example = "Cleiton Sena", required = true)
    private String name;
}
