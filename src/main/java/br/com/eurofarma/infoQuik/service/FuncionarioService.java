package br.com.eurofarma.infoQuik.service;

import br.com.eurofarma.infoQuik.dto.funcionarioDTO.FuncionarioDTO;
import br.com.eurofarma.infoQuik.model.Departamento;
import br.com.eurofarma.infoQuik.model.Funcionario;
import br.com.eurofarma.infoQuik.model.ListaDePresenca;
import br.com.eurofarma.infoQuik.model.Treinamento;
import br.com.eurofarma.infoQuik.repository.DepartamentoRepository;
import br.com.eurofarma.infoQuik.repository.FuncionarioRepository;
import br.com.eurofarma.infoQuik.repository.ListaDePresencaRepository;
import br.com.eurofarma.infoQuik.repository.TreinamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private TreinamentoRepository treinamentoRepository;

    @Autowired
    private ListaDePresencaRepository listaDePresencaRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Transactional(readOnly = true)
    public List<FuncionarioDTO> findAll() {
        List<Funcionario> list = repository.findAll();
        return list.stream().map(FuncionarioDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public FuncionarioDTO insert(FuncionarioDTO dto) {
        if(repository.existsByEmail(dto.getEmail()) || repository.existsByCpf(dto.getCpf())) return null;
        Funcionario entity = new Funcionario();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new FuncionarioDTO(entity);
    }

    @Transactional(readOnly = true)
    public FuncionarioDTO findById(Long id) {

        Funcionario funcionario = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso inválido - " + id)
        );
        return new FuncionarioDTO(funcionario);
    }

    @Transactional
    public FuncionarioDTO update(Long id, FuncionarioDTO dto) {
        try {
            Funcionario funcionario = repository.getReferenceById(id);
            copyDtoToEntity(dto, funcionario);
            funcionario = repository.save(funcionario);
            return new FuncionarioDTO(funcionario);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Recurso não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Produto inválido - id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Produto inválido - id: " + id);
        }
    }

    private void copyDtoToEntity(FuncionarioDTO dto, Funcionario entity) {
        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());

        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.getSenha());
        entity.setSenha(senhaCriptografada);

//        entity.setRole(dto.getRole());

        Departamento departamento = departamentoRepository.findById(dto.getDepartamentoId()).orElseThrow(
                () -> new IllegalArgumentException("Recurso nao encontrado: id " + dto.getDepartamentoId())
        );
        entity.setDepartamento(departamento);

        dto.getTreinamentos().forEach(treinamentoDTO -> {
            Treinamento treinamento = treinamentoRepository.findById(treinamentoDTO.getId()).orElseThrow(
                () -> new IllegalArgumentException("Recurso nao encontrado: id " + treinamentoDTO.getId())
            );
            entity.getTreinamentos().add(treinamento);
        });

        dto.getListaDePresencaList().forEach(listaDePresencaDTO -> {
            ListaDePresenca listaDePresenca = listaDePresencaRepository.findById(listaDePresencaDTO.getId()).orElseThrow(
                    () -> new IllegalArgumentException("Recurso nao encontrado: id " + listaDePresencaDTO.getId())
            );
            entity.getListaDePresencaSet().add(listaDePresenca);
        });
//        entity.getTreinamentos().clear();
//        dto.getTreinamentos().forEach(treinamentoDTO -> {
//            Treinamento treinamento = new Treinamento();
//            treinamento.setId(treinamentoDTO.getId());
//            entity.getTreinamentos().add(treinamento);
//        });
//
//        entity.getListaDePresencaSet().clear();
//        dto.getListaDePresencaList().forEach(listaDePresencaDTO -> {
//            ListaDePresenca listaDePresenca = new ListaDePresenca();
//            listaDePresenca.setId(listaDePresencaDTO.getId());
//            entity.getListaDePresencaSet().add(listaDePresenca);
//        });
    }

}
