package br.com.eurofarma.infoQuik.service;

import br.com.eurofarma.infoQuik.dto.FuncionarioDTO;
import br.com.eurofarma.infoQuik.dto.ListaDePresencaDTO;
import br.com.eurofarma.infoQuik.model.Funcionario;
import br.com.eurofarma.infoQuik.model.ListaDePresenca;
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
public class ListaDePresencaService {
    @Autowired
    private ListaDePresencaRepository repository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private TreinamentoRepository treinamentoRepository;

    @Transactional(readOnly = true)
    public List<ListaDePresencaDTO> findAll() {
        List<ListaDePresenca> list = repository.findAll();
        return list.stream().map(ListaDePresencaDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public ListaDePresencaDTO insert(ListaDePresencaDTO dto) {
        ListaDePresenca entity = new ListaDePresenca();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ListaDePresencaDTO(entity);
    }

    @Transactional(readOnly = true)
    public ListaDePresencaDTO findById(Long id) {

        ListaDePresenca listaDePresenca = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso inválido - " + id)
        );
        return new ListaDePresencaDTO(listaDePresenca);
    }

    @Transactional
    public ListaDePresencaDTO update(Long id, ListaDePresencaDTO dto) {
        try {
            ListaDePresenca listaDePresenca = repository.getReferenceById(id);
            copyDtoToEntity(dto, listaDePresenca);
            listaDePresenca = repository.save(listaDePresenca);
            return new ListaDePresencaDTO(listaDePresenca);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Recurso não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Lista De Presenca inválido - id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Lista De Presenca inválido - id: " + id);
        }
    }

    private void copyDtoToEntity(ListaDePresencaDTO dto, ListaDePresenca entity) {
        entity.setData_criacao(dto.getData_criacao());
        entity.setTreinamento(treinamentoRepository.findById(dto.getTreinamentoId()).orElseThrow(
                () -> new IllegalArgumentException("Recurso Não Encontrado id: " + dto.getTreinamentoId())
        ));

        entity.getFuncionarios().clear();
        dto.getFuncionarios().forEach(funcionarioDTO -> {
            Funcionario funcionario = funcionarioRepository.findById(funcionarioDTO.getId()).orElseThrow(
                    () -> new IllegalArgumentException("Recurso não encontrado id: " + dto.getTreinamentoId())
            );
            entity.getFuncionarios().add(funcionario);
        });
    }
}
