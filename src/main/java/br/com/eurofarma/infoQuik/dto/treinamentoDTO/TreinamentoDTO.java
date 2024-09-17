package br.com.eurofarma.infoQuik.dto.treinamentoDTO;

import br.com.eurofarma.infoQuik.dto.departamentoDTO.DepartamentosDTOSemListaTreinamentos;
import br.com.eurofarma.infoQuik.dto.listadepresencaDTO.ListaDePresencaDTO;
import br.com.eurofarma.infoQuik.dto.tagDTO.TagDTO;
import br.com.eurofarma.infoQuik.model.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
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

    @NotNull(message = "Data requerida.")
    private Date data_criacao;

    @NotNull(message = "Data requerida.")
    private Date data_ultima_alteracao;

    @NotBlank(message = "Campo requerido")
    @Size(min = 5, message = "O título deve ter pelo menos 5 caracteres.")
    private String titulo;

    @NotBlank(message = "Campo requerido")
    private String corpo_texto;

    @NotBlank(message = "Campo requerido")
    private Tipo tipo;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Long treinadorId;

    private List<TagDTO> tags = new ArrayList<>();
    private List<ListaDePresencaDTO> listaDePresencaList = new ArrayList<>();
    private List<DepartamentosDTOSemListaTreinamentos> departamentos = new ArrayList<>();

    public TreinamentoDTO(Treinamento entity) {
        this.id = entity.getId();
        this.descricao = entity.getDescricao();
        this.data_criacao = entity.getData_criacao();
        this.data_ultima_alteracao = entity.getData_ultima_alteracao();
        this.titulo = entity.getTitulo();
        this.corpo_texto = entity.getCorpo_texto();
        this.tipo = entity.getTipo();
        this.status = entity.getStatus();
        this.treinadorId = entity.getTreinador().getId();

//        this.tags.addAll(entity.getTags());
//        this.funcionarios.addAll(entity.getFuncionarios());
//        this.listaDePresencaList.addAll(entity.getListaDePresencaList());
//        this.departamentos.addAll(entity.getDepartamentos());
        entity.getTags().forEach(tag -> tags.add(new TagDTO(tag)));
        entity.getListaDePresencaList().forEach(listaDePresenca -> listaDePresencaList.add(new ListaDePresencaDTO(listaDePresenca)));
        entity.getDepartamentos().forEach(departamento -> departamentos.add(new DepartamentosDTOSemListaTreinamentos(departamento)));
    }
}
