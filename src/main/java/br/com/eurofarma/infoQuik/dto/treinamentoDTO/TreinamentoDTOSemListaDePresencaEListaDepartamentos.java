package br.com.eurofarma.infoQuik.dto.treinamentoDTO;

import br.com.eurofarma.infoQuik.dto.tagDTO.TagDTO;
import br.com.eurofarma.infoQuik.model.Departamento;
import br.com.eurofarma.infoQuik.model.Status;
import br.com.eurofarma.infoQuik.model.Tipo;
import br.com.eurofarma.infoQuik.model.Treinamento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TreinamentoDTOSemListaDePresencaEListaDepartamentos {

    private Long id;

    @NotBlank(message = "Campo requerido")
    @Size(min = 5, message = "A descrição deve ter pelo menos 5 caracteres.")
    private String descricao;

    private Date data_criacao;

    private Date data_ultima_alteracao;

    @NotBlank(message = "Campo requerido")
    @Size(min = 5, message = "O título deve ter pelo menos 5 caracteres.")
    private String titulo;

    @NotBlank(message = "Campo requerido")
    private String corpo_texto;

    @NotNull(message = "Campo requerido")
    private Tipo tipo;

    @NotNull
    private List<Long> departamentos_id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Long treinador_id;

    private List<TagDTO> tags = new ArrayList<>();

    public TreinamentoDTOSemListaDePresencaEListaDepartamentos(Treinamento entity) {
        this.id = entity.getId();
        this.descricao = entity.getDescricao();
        this.data_criacao = entity.getData_criacao();
        this.data_ultima_alteracao = entity.getData_ultima_alteracao();
        this.titulo = entity.getTitulo();
        this.corpo_texto = entity.getCorpo_texto();
        this.tipo = entity.getTipo();
        this.status = entity.getStatus();
        this.treinador_id = entity.getTreinador().getId();
        this.departamentos_id = entity.getDepartamentos()
                .stream()
                .map(Departamento::getId) // Assume que Departamento tem um método getId()
                .collect(Collectors.toList());
        entity.getTags().forEach(tag -> tags.add(new TagDTO(tag)));
    }
}
