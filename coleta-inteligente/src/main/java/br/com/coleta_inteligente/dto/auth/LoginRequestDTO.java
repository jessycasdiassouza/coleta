package br.com.coleta_inteligente.dto.auth;

public record LoginRequestDTO(
        String email,
        String senha
) {
}
