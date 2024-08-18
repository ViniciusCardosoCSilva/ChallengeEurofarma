package br.com.eurofarma.infoQuik.dto;

import br.com.eurofarma.infoQuik.model.Funcionario;
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

    private Treinamento treinamento;

    private Set<Funcionario> funcionarios = new HashSet<>();

}
