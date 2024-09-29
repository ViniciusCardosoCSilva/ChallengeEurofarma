package br.com.eurofarma.infoQuik.dto.funcionarioDTO;

import br.com.eurofarma.infoQuik.dto.departamentoDTO.DepartamentoDTOSemListaFuncionariosETreinamentos;
import br.com.eurofarma.infoQuik.dto.treinamentoDTO.TreinamentoDTOSemListaDePresencaEListaDepartamentos;
import br.com.eurofarma.infoQuik.model.Departamento;
import br.com.eurofarma.infoQuik.model.Funcionario;
import br.com.eurofarma.infoQuik.model.ListaDePresenca;
import br.com.eurofarma.infoQuik.model.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Size(min = 11, message = "O cpf deve ter 11 caractéres")
    private String cpf;

    @NotBlank(message = "Campo requerido")
    @Email
    private String email;

    @NotBlank(message = "Campo requerido")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caractéres")
    private String senha;

//    @Enumerated(EnumType.STRING)
//    private UserRole role;

    private List<TreinamentoDTOSemListaDePresencaEListaDepartamentos> treinamentos = new ArrayList<>();

    private DepartamentoDTOSemListaFuncionariosETreinamentos departamento;

    private List<ListaDePresenca> listaDePresencaList = new ArrayList<>();

    public FuncionarioDTO(Funcionario entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cpf = entity.getCpf();
        this.email = entity.getEmail();
        this.senha = entity.getSenha();
//        this.role = entity.getRole();
        this.departamento = new DepartamentoDTOSemListaFuncionariosETreinamentos(entity.getDepartamento());
        this.listaDePresencaList.addAll(entity.getListaDePresencaSet());
        entity.getTreinamentos().forEach(treinamento -> treinamentos.add(new TreinamentoDTOSemListaDePresencaEListaDepartamentos(treinamento)));
//        entity.getListaDePresencaSet().forEach(listaDePresenca -> listaDePresencaList.add(new ListaDePresencaDTO(listaDePresenca)));
    }
}
