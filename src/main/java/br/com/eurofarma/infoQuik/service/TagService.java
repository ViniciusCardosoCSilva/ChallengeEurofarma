package br.com.eurofarma.infoQuik.service;

import br.com.eurofarma.infoQuik.dto.tagDTO.TagDTO;
import br.com.eurofarma.infoQuik.model.Tag;
import br.com.eurofarma.infoQuik.model.Treinamento;
import br.com.eurofarma.infoQuik.repository.TagRepository;
import br.com.eurofarma.infoQuik.repository.TreinamentoRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private TagRepository repository;

    @Autowired
    private TreinamentoRepository treinamentoRepository;

    @Transactional(readOnly = true)
    public List<TagDTO> findAll() {
        List<Tag> list = repository.findAll();
        return list.stream().map(TagDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public TagDTO insert(TagDTO dto) {
        Tag entity = new Tag();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new TagDTO(entity);
    }

    @Transactional(readOnly = true)
    public TagDTO findById(Long id) {

        Tag tag = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso inválido - " + id)
        );
        return new TagDTO(tag);
    }

    @Transactional
    public TagDTO update(Long id, TagDTO dto) {

        try {
            Tag entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new TagDTO(entity);
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

    private void copyDtoToEntity(TagDTO dto, Tag entity) {
        entity.setNome(dto.getNome());
        entity.setTreinamento(new Treinamento());
        entity.getTreinamento().setId(dto.getTreinamentoId());
    }
}
