package br.com.eurofarma.infoQuik.repository;

import br.com.eurofarma.infoQuik.model.Treinador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface TreinadorRepository extends JpaRepository<Treinador, Long> {

    UserDetails findByEmail(String email);
    Boolean existsByEmail(String email);
    Object findByCpf(String cpf);
    Boolean existsByCpf(String cpf);
}
