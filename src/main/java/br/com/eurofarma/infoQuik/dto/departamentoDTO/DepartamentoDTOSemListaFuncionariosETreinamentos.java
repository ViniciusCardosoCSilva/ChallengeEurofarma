package br.com.eurofarma.infoQuik.dto.departamentoDTO;

import br.com.eurofarma.infoQuik.model.Departamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartamentoDTOSemListaFuncionariosETreinamentos {

    private Long id;

    @NotBlank(message = "Campo requerido")
    @Size(min = 5, message = "O nome do departamento deve ter no mínimo 5 caractéres")
    private String nome;

    public DepartamentoDTOSemListaFuncionariosETreinamentos(Departamento entity){
        this.id = entity.getId();
        this.nome = entity.getNome();
    }

}
