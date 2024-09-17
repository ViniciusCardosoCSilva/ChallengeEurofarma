package br.com.eurofarma.infoQuik.dto.treinadorDTO;

import br.com.eurofarma.infoQuik.dto.treinamentoDTO.TreinamentoDTOComListaDeDepartamentosSemFuncionarios;
import br.com.eurofarma.infoQuik.model.Treinador;
import jakarta.validation.constraints.Email;
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
public class TreinadorDTO {

    private Long id;

    @NotBlank(message = "Campo requerido")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caractéres")
    private String nome;

    @NotBlank(message = "Campo requerido")
    @Size(min = 11, message = "O cpf deve ter no mínimo 11 caractéres")
    private String cpf;

    @NotBlank(message = "Campo requerido")
    @Email
    private String email;

    @NotBlank(message = "Campo requerido")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caractéres")
    private String senha;

    private List<TreinamentoDTOComListaDeDepartamentosSemFuncionarios> treinamentos = new ArrayList<>();

    public TreinadorDTO(Treinador entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cpf = entity.getCpf();
        this.email = entity.getEmail();
        this.senha = entity.getSenha();
//        this.treinamentos.addAll(entity.getTreinamentos());
        entity.getTreinamentos().forEach(treinamento -> treinamentos.add(new TreinamentoDTOComListaDeDepartamentosSemFuncionarios(treinamento)));
    }
}
