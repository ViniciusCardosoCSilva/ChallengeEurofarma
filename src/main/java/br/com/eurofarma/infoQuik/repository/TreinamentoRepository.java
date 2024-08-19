package br.com.eurofarma.infoQuik.repository;

import br.com.eurofarma.infoQuik.model.Departamento;
import br.com.eurofarma.infoQuik.model.Status;
import br.com.eurofarma.infoQuik.model.Treinador;
import br.com.eurofarma.infoQuik.model.Treinamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreinamentoRepository extends JpaRepository<Treinamento, Long> {

    List<Treinamento> findByDepartamentosAndStatus(Departamento departamentoId, Status status);
    List<Treinamento> findByTreinador(Treinador treinador);
    List<Treinamento> findByStatus(Status status);
//    boolean existsByDepartamento(Departamento departamento);
}
