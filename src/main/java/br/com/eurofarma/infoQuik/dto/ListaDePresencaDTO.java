package br.com.eurofarma.infoQuik.dto;

import br.com.eurofarma.infoQuik.model.Funcionario;
import br.com.eurofarma.infoQuik.model.ListaDePresenca;
import br.com.eurofarma.infoQuik.model.Treinamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListaDePresencaDTO {

    private Long id;

    private Date data_criacao;

    private String codigo;

    private Treinamento treinamento;

    private Set<Funcionario> funcionarios = new HashSet<>();

    public ListaDePresencaDTO(ListaDePresenca entity) {
        this.id = entity.getId();
        this.data_criacao = entity.getData_criacao();
        this.codigo = entity.getCodigo();
        this.treinamento = entity.getTreinamento();
        this.funcionarios = entity.getFuncionarios();
    }
}
