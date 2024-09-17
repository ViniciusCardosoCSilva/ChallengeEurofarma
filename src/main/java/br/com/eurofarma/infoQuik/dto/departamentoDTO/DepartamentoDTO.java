package br.com.eurofarma.infoQuik.dto.departamentoDTO;

import br.com.eurofarma.infoQuik.dto.funcionarioDTO.FuncionarioDTOSemListaTreinamentos;
import br.com.eurofarma.infoQuik.dto.treinamentoDTO.TreinamentoDTOSemListaDePresencaEListaDepartamentos;
import br.com.eurofarma.infoQuik.model.Departamento;
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

    private List<FuncionarioDTOSemListaTreinamentos> funcionarios = new ArrayList<>();

    private List<TreinamentoDTOSemListaDePresencaEListaDepartamentos> treinamentos = new ArrayList<>();

    public DepartamentoDTO(Departamento entity){
        this.id = entity.getId();
        this.nome = entity.getNome();
        entity.getFuncionarios().forEach(funcionario -> funcionarios.add(new FuncionarioDTOSemListaTreinamentos(funcionario)));
        entity.getTreinamentos().forEach(treinamento -> treinamentos.add(new TreinamentoDTOSemListaDePresencaEListaDepartamentos(treinamento)));
    }
}
