package br.com.eurofarma.infoQuik.dto.tagDTO;

import br.com.eurofarma.infoQuik.dto.treinamentoDTO.TreinamentoDTOComListaDeDepartamentosSemFuncionarios;
import br.com.eurofarma.infoQuik.dto.treinamentoDTO.TreinamentoDTOSemListaDePresencaEListaDepartamentos;
import br.com.eurofarma.infoQuik.model.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TagDTO {

    private Long id;

    @NotBlank(message = "Campo requerido")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caractéres")
    private String nome;

    private List<TreinamentoDTOComListaDeDepartamentosSemFuncionarios> treinamentos;

    public TagDTO(Tag entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        entity.getTreinamentos().forEach(treinamento -> treinamentos.add(new TreinamentoDTOComListaDeDepartamentosSemFuncionarios(treinamento)));
    }
}
