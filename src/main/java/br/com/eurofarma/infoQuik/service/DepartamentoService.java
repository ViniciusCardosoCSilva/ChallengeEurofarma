package br.com.eurofarma.infoQuik.service;

import br.com.eurofarma.infoQuik.dto.DepartamentoDTO;
import br.com.eurofarma.infoQuik.dto.TreinamentoDTO;
import br.com.eurofarma.infoQuik.model.Departamento;
import br.com.eurofarma.infoQuik.model.Funcionario;
import br.com.eurofarma.infoQuik.model.Treinamento;
import br.com.eurofarma.infoQuik.repository.DepartamentoRepository;
import br.com.eurofarma.infoQuik.repository.TreinamentoRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository repository;

    @Autowired
    private TreinamentoRepository treinamentoRepository;

    @Transactional(readOnly = true)
    public List<DepartamentoDTO> findAll() {
        List<Departamento> list = repository.findAll();
        return list.stream().map(DepartamentoDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public DepartamentoDTO insert(DepartamentoDTO dto) {
        Departamento entity = new Departamento();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new DepartamentoDTO(entity);
    }

    @Transactional(readOnly = true)
    public DepartamentoDTO findById(Long id) {

        Departamento departamento = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso inválido - " + id)
        );
        return new DepartamentoDTO(departamento);
    }

    @Transactional
    public DepartamentoDTO update(Long id, DepartamentoDTO dto) {
        try {
            Departamento departamento = repository.getReferenceById(id);
            copyDtoToEntity(dto, departamento);
            departamento = repository.save(departamento);
            return new DepartamentoDTO(departamento);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Recurso não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Departamento inválido - id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Departamento inválido - id: " + id);
        }
    }

    private void copyDtoToEntity(DepartamentoDTO dto, Departamento entity) {
        entity.setNome(dto.getNome());
        entity.setFuncionarios(dto.getFuncionarios());

        entity.getTreinamentos().clear();
        for(Treinamento item: dto.getTreinamentos()){
            Treinamento treinamento = treinamentoRepository.getReferenceById(item.getId());
            entity.getTreinamentos().add(treinamento);
        }
    }
}