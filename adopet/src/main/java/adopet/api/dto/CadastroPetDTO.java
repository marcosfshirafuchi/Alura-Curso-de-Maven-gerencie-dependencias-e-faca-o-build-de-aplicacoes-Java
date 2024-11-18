package adopet.api.dto;

import adopet.api.model.TipoPet;
import adopet.api.model.TipoPorte;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroPetDTO(
        @NotBlank
        String nome,
        @NotNull
        Integer idade,
        @NotNull
        TipoPet tipo,
        @NotNull
        TipoPorte porte
) {
}
