package br.com.eurofarma.infoQuik.repository;

import br.com.eurofarma.infoQuik.model.Treinador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TreinadorRepository extends JpaRepository<Treinador, Long> {

    UserDetails findByEmail(String email);

    @Query("SELECT t.id FROM Treinador t WHERE t.email = :email")
    Long findIdByEmail(@Param("email") String email);

    Treinador findByEmailAndSenha(String email, String senha);
    Boolean existsByEmail(String email);
    Object findByCpf(String cpf);
    Boolean existsByCpf(String cpf);
}
