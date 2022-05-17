package br.com.oneos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExampleRequestDTO {
    @Schema(description = "Descricao do atributo", example = "Algum exemplo")
    private String attribute;
}
