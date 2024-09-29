package br.com.eurofarma.infoQuik.repository;

import br.com.eurofarma.infoQuik.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    UserDetails findByEmail(String email);

    @Query("SELECT t.id FROM Funcionario t WHERE t.email = :email")
    Long findIdByEmail(@Param("email") String email);
    Boolean existsByEmail(String email);
    Object findByCpf(String cpf);
    Boolean existsByCpf(String cpf);
}
