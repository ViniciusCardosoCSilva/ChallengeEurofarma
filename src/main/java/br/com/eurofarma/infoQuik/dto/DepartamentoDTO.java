package br.com.eurofarma.infoQuik.dto;

import br.com.eurofarma.infoQuik.model.Departamento;
import br.com.eurofarma.infoQuik.model.Funcionario;
import br.com.eurofarma.infoQuik.model.Treinamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartamentoDTO {
    private Long id;

    @NotBlank(message = "Campo requerido")
    @Size(min = 5, message = "O nome do departamento deve ter no mínimo 5 caractéres")
    private String nome;

    private List<Funcionario> funcionarios = new ArrayList<>();

    private List<Treinamento> treinamentos = new ArrayList<>();

    public DepartamentoDTO(Departamento entity){
        this.id = entity.getId();
        this.nome = entity.getNome();

        this.funcionarios.addAll(entity.getFuncionarios());

        this.treinamentos.addAll(entity.getTreinamentos());

//        versao da professora caso o metodo addAll() nao funcione:
//        for(Funcionario funcionario : entity.getFuncionarios()){
//            this.funcionarios.add(funcionario);
//        }
    }
}
