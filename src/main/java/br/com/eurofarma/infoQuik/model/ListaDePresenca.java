package br.com.eurofarma.infoQuik.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Table(name = "tb_lista_de_presenca")
public class ListaDePresenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date data_criacao;

    private String codigo;

    @ManyToOne
    @JoinColumn(name = "treinamento_id", nullable = false)
    private Treinamento treinamento;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_lista_presenca_funcionario",
            joinColumns = @JoinColumn(name = "lista_de_presenca_id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id"))
    private Set<Funcionario> funcionarios = new HashSet<>();

}
