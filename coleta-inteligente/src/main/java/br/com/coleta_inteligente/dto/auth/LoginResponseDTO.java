package br.com.coleta_inteligente.dto.auth;

public record LoginResponseDTO(
        Long usuarioId,
        String nome,
        String email,
        String perfil,
        boolean ativo,
        String token
) {
}
