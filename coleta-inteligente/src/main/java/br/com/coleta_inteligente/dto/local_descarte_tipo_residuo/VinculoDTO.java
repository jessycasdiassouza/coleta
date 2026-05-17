package br.com.coleta_inteligente.dto.local_descarte_tipo_residuo;

public record VinculoDTO(

        Long localId,

        String localTitulo,

        Long tipoId,

        String tipoNome

) {
}
