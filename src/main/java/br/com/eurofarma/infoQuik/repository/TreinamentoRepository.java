package br.com.eurofarma.infoQuik.repository;

import br.com.eurofarma.infoQuik.model.Treinamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TreinamentoRepository extends JpaRepository<Treinamento, Long> {
}
