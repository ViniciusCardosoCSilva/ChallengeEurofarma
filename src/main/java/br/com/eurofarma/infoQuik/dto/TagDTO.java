package br.com.eurofarma.infoQuik.dto;

import br.com.eurofarma.infoQuik.model.Tag;
import br.com.eurofarma.infoQuik.model.Treinamento;
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
public class TagDTO {

    private Long id;

    @NotBlank(message = "Campo requerido")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caractéres")
    private String nome;

    private Long treinamentoId;

    public TagDTO(Tag entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.treinamentoId = entity.getTreinamento().getId();
    }
}
