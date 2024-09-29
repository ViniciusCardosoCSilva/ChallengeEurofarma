package br.com.eurofarma.infoQuik.dto;

import br.com.eurofarma.infoQuik.model.UserRole;

public record LoginResponseDTO(String token, Long id, UserRole role) {
}
