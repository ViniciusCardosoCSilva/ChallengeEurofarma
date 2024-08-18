package br.com.eurofarma.infoQuik.dto;

import br.com.eurofarma.infoQuik.model.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TreinamentoDTO {

    private Long id;

    @NotBlank(message = "Campo requerido")
    @Size(min = 5, message = "A descrição deve ter pelo menos 5 caracteres.")
    private String descricao;

    @NotNull (message = "Data requerida.")
    private Date data_criacao;

    @NotNull (message = "Data requerida.")
    private Date data_ultima_alteracao;

    @NotBlank(message = "Campo requerido")
    @Size(min = 5, message = "O título deve ter pelo menos 5 caracteres.")
    private String titulo;

    @NotBlank(message = "Campo requerido")
    private String corpo_texto;

    @NotBlank(message = "Campo requerido")
    private String tipo;

    @Enumerated(EnumType.STRING)
    private Status status;

    private List<Tag> tags = new ArrayList<>();
    private Treinador treinador;
    private Funcionario funcionario;
    private List<ListaDePresenca> listaDePresencaList = new ArrayList<>();
    private List<Departamento> departamentos = new ArrayList<>();

    public TreinamentoDTO(Treinamento entity) {
        this.id = entity.getId();
        this.descricao = entity.getDescricao();
        this.data_criacao = entity.getData_criacao();
        this.data_ultima_alteracao = entity.getData_ultima_alteracao();
        this.titulo = entity.getTitulo();
        this.corpo_texto = entity.getCorpo_texto();
        this.tipo = entity.getTipo();
        this.status = entity.getStatus();
        this.tags.addAll(entity.getTags());
        this.treinador = entity.getTreinador();
        this.funcionario = entity.getFuncionario();
        this.listaDePresencaList.addAll(entity.getListaDePresencaList());
        this.departamentos.addAll(entity.getDepartamentos());
    }
}
