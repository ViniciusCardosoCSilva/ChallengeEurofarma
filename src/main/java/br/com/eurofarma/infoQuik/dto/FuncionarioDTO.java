package br.com.eurofarma.infoQuik.dto;

import br.com.eurofarma.infoQuik.model.Departamento;
import br.com.eurofarma.infoQuik.model.Funcionario;
import br.com.eurofarma.infoQuik.model.ListaDePresenca;
import br.com.eurofarma.infoQuik.model.Treinamento;
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
public class FuncionarioDTO {

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
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caractéres")
    private String senha;

    private List<Treinamento> treinamentos = new ArrayList<>();

    private Long departamentoId;

    private List<ListaDePresenca> listaDePresencaList = new ArrayList<>();

    public FuncionarioDTO(Funcionario entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cpf = entity.getCpf();
        this.email = entity.getEmail();
        this.senha = entity.getSenha();
        this.departamentoId = entity.getDepartamento().getId();
        this.treinamentos.addAll(entity.getTreinamentos());
        this.listaDePresencaList.addAll(entity.getListaDePresencaSet());
//        entity.getTreinamentos().forEach(treinamento -> treinamentos.add(new TreinamentoDTO(treinamento)));
//        entity.getListaDePresencaSet().forEach(listaDePresenca -> listaDePresencaList.add(new ListaDePresencaDTO(listaDePresenca)));
    }
}
