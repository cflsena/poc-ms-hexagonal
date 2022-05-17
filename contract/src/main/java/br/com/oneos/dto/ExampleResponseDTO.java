package br.com.oneos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExampleResponseDTO {
    @Schema(description = "Descricao do atributo", example = "Algum exemplo")
    private String attribute;
}
