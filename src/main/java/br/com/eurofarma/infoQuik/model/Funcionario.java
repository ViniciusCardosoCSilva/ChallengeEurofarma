package br.com.eurofarma.infoQuik.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
@Table(name = "tb_funcionario")
public class Funcionario implements UserDetails {

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

//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private UserRole role;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_FUNCIONARIO"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
