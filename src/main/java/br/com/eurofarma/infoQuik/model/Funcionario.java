package br.com.eurofarma.infoQuik.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

//    @OneToMany(mappedBy = "funcionario")
//    private List<Treinamento> treinamentos = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "treinamento_id", nullable = false)
//    private Treinamento treinamento;

    @ManyToMany(mappedBy = "funcionarios", fetch = FetchType.EAGER)
    private Set<Treinamento> treinamentos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;

    @ManyToMany(mappedBy = "funcionarios", fetch = FetchType.EAGER)
    private Set<ListaDePresenca> listaDePresencaSet = new HashSet<>();
}
