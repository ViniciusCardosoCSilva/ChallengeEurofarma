package br.com.eurofarma.infoQuik.service;

import br.com.eurofarma.infoQuik.dto.treinadorDTO.TreinadorDTO;
import br.com.eurofarma.infoQuik.model.Treinador;
import br.com.eurofarma.infoQuik.model.Treinamento;
import br.com.eurofarma.infoQuik.repository.TreinadorRepository;
import br.com.eurofarma.infoQuik.repository.TreinamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TreinadorService {
    @Autowired
    private TreinadorRepository repository;

    @Autowired
    private TreinamentoRepository treinamentoRepository;

    @Transactional(readOnly = true)
    public List<TreinadorDTO> findAll() {
        List<Treinador> list = repository.findAll();
        return list.stream().map(TreinadorDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public TreinadorDTO insert(TreinadorDTO dto) {
        if(repository.existsByEmail(dto.getEmail()) || repository.existsByCpf(dto.getCpf())) return null;
        Treinador entity = new Treinador();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new TreinadorDTO(entity);
    }

    @Transactional(readOnly = true)
    public TreinadorDTO findById(Long id) {

        Treinador treinador = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso inválido - " + id)
        );
        return new TreinadorDTO(treinador);
    }

    @Transactional(readOnly = true)
    public Long findIdByEmail(String email) {

        return repository.findIdByEmail(email);

    }

    @Transactional
    public TreinadorDTO update(Long id, TreinadorDTO dto) {

        try {
            Treinador entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new TreinadorDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Recurso não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Recurso inválido - id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Falha de integridade referencial - id: " + id);
        }
    }

    private void copyDtoToEntity(TreinadorDTO dto, Treinador entity) {
        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());

        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.getSenha());
        entity.setSenha(senhaCriptografada);

        entity.setRole(dto.getRole());

        dto.getTreinamentos().forEach(treinamentoDTO -> {
            Treinamento treinamento = treinamentoRepository.findById(treinamentoDTO.getId()).orElseThrow(
                    () -> new IllegalArgumentException("Recurso nao encontrado id: " + treinamentoDTO.getId())
            );
            entity.getTreinamentos().add(treinamento);
        });
//        dto.getTreinamentos().forEach(treinamentoDTO -> {
//            Treinamento treinamento = new Treinamento();
//            treinamento.setId(dto.getId());
//            entity.getTreinamentos().add(treinamento);
//        });
    }
}
