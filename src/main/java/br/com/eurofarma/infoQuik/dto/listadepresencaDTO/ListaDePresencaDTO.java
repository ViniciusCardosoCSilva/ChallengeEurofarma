package br.com.eurofarma.infoQuik.dto.listadepresencaDTO;

import br.com.eurofarma.infoQuik.dto.funcionarioDTO.FuncionarioDTOSemListaTreinamentos;
import br.com.eurofarma.infoQuik.model.Funcionario;
import br.com.eurofarma.infoQuik.model.ListaDePresenca;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListaDePresencaDTO {

    private Long id;

    private Date data_criacao;

    private String codigo;

    private Long treinamentoId;

    private List<FuncionarioDTOSemListaTreinamentos> funcionarios = new ArrayList<>();

    public ListaDePresencaDTO(ListaDePresenca entity) {
        this.id = entity.getId();
        this.data_criacao = entity.getData_criacao();
        this.codigo = entity.getCodigo();
        this.treinamentoId = entity.getTreinamento().getId();
        entity.getFuncionarios().forEach(funcionario -> funcionarios.add(new FuncionarioDTOSemListaTreinamentos(funcionario)));
//        this.funcionarios.addAll(entity.getFuncionarios());
//        entity.getFuncionarios().forEach(funcionario -> funcionarios.add(new FuncionarioDTO(funcionario)));
    }
}
