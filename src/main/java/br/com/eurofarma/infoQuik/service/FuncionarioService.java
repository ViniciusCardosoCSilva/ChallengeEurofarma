package br.com.eurofarma.infoQuik.service;

import br.com.eurofarma.infoQuik.dto.FuncionarioDTO;
import br.com.eurofarma.infoQuik.model.Funcionario;
import br.com.eurofarma.infoQuik.model.ListaDePresenca;
import br.com.eurofarma.infoQuik.model.Treinamento;
import br.com.eurofarma.infoQuik.repository.FuncionarioRepository;
import br.com.eurofarma.infoQuik.repository.ListaDePresencaRepository;
import br.com.eurofarma.infoQuik.repository.TreinamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional(readOnly = true)
    public List<FuncionarioDTO> findAll() {
        List<Funcionario> list = repository.findAll();
        return list.stream().map(FuncionarioDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public FuncionarioDTO insert(FuncionarioDTO dto) {
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
        entity.setSenha(dto.getSenha());// estou em duvida se dever te isso
        entity.setDepartamento(dto.getDepartamento());

        entity.getTreinamentos().clear();
        for(Treinamento item: dto.getTreinamentos()){
            Treinamento treinamento = treinamentoRepository.getReferenceById(item.getId());
            entity.getTreinamentos().add(treinamento);
        }

        entity.getListaDePresencaSet().clear();
        for(ListaDePresenca item: dto.getListaDePresencaList()){
            //para colocar os dados completos da loja
            ListaDePresenca listaDePresenca = listaDePresencaRepository.getReferenceById(item.getId());
            entity.getListaDePresencaSet().add(listaDePresenca);
        }
    }

}
