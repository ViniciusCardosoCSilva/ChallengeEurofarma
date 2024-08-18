package br.com.eurofarma.infoQuik.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.*;

@Getter
@Entity
@Table(name = "tb_lista_de_presenca")
public class ListaDePresenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date data_criacao;

    @ManyToOne
    @JoinColumn(name = "treinamento_id", nullable = false)
    private Treinamento treinamento;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_lista_presenca_funcionario", //tabela associativa
            joinColumns = @JoinColumn(name = "lista_de_presenca_id"), //ref. FK - mesma entidade da classe
            inverseJoinColumns = @JoinColumn(name = "funcionario_id")) //ref. PK - da outra classe
    private Set<Funcionario> funcionarios = new HashSet<>();

}
