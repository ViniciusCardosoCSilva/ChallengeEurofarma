package br.com.eurofarma.infoQuik.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
@Table(name = "tb_treinamento")
public class Treinamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Date data_criacao;

    @Column(nullable = false)
    private Date data_ultima_alteracao;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String corpo_texto;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @OneToMany(mappedBy = "treinamento", fetch = FetchType.EAGER)
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "treinador_id", nullable = false)
    private Treinador treinador;

    @OneToMany(mappedBy = "treinamento", fetch = FetchType.EAGER)
    private List<ListaDePresenca> listaDePresencaList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_treinamento_funcionario",
            joinColumns = @JoinColumn(name = "treinamento_id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id"))
    private Set<Funcionario> funcionarios = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_treinamento_departamento",
            joinColumns = @JoinColumn(name = "treinamento_id"),
            inverseJoinColumns = @JoinColumn(name = "departamento_id"))
    private Set<Departamento> departamentos = new HashSet<>();

}
