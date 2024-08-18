package br.com.eurofarma.infoQuik.repository;

import br.com.eurofarma.infoQuik.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
