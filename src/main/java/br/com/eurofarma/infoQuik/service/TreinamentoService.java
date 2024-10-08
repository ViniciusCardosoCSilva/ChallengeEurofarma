package br.com.eurofarma.infoQuik.service;

import br.com.eurofarma.infoQuik.dto.treinamentoDTO.TreinamentoDTO;
import br.com.eurofarma.infoQuik.dto.treinamentoDTO.TreinamentoDTOSemListaDePresencaEListaDepartamentos;
import br.com.eurofarma.infoQuik.model.*;
import br.com.eurofarma.infoQuik.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreinamentoService {
    @Autowired
    TreinamentoRepository repository;

    @Autowired
    DepartamentoRepository departamentoRepository;

    @Autowired
    ListaDePresencaRepository listaDePresencaRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    TreinadorRepository treinadorRepository;

    @Transactional(readOnly = true)
    public List<TreinamentoDTO> findAll() {
        List<Treinamento> list = repository.findAll();
        return list.stream().map(TreinamentoDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public TreinamentoDTO findById(Long id){
        Treinamento entity = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso não encontrado")  
        );
        return new TreinamentoDTO(entity);
    }

    @Transactional
    public List<TreinamentoDTO> findByDepartamentoAndPublicado(Departamento departamento){
        List<Treinamento> list = new ArrayList<>();
//        if(repository.existsByDepartamento(departamento)) {
//            list = repository.findByDepartamento(departamento);
//        }
        list = repository.findByDepartamentosAndStatus(departamento, Status.PUBLICADO);
        return list.stream().map(TreinamentoDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public List<TreinamentoDTO> findPublicados(){
        List<Treinamento> list = new ArrayList<>();
        list = repository.findByStatus(Status.PUBLICADO);
        return list.stream().map(TreinamentoDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public List<TreinamentoDTO> findByTreinador(Treinador treinador){
        List<Treinamento> list = new ArrayList<>();
//        if(repository.existsByDepartamento(departamento)) {
//            list = repository.findByDepartamento(departamento);
//        }
        list = repository.findByTreinador(treinador);
        return list.stream().map(TreinamentoDTO::new).collect(Collectors.toList());
    }
    
    @Transactional
    public TreinamentoDTOSemListaDePresencaEListaDepartamentos insert (TreinamentoDTOSemListaDePresencaEListaDepartamentos dto){
        Treinamento entity = new Treinamento();

        copyDtoToEntitySemLista(dto, entity);
        entity.setStatus(Status.RASCUNHO);
        entity.setData_criacao(new Date());
        entity.setData_ultima_alteracao(new Date());
        if(entity.getStatus() == Status.PUBLICADO){
            if(!validarCampos(entity)){
                entity.setStatus(Status.RASCUNHO);

            }

        }
        entity = repository.save(entity);
        return new TreinamentoDTOSemListaDePresencaEListaDepartamentos(entity);
    }



    @Transactional
    public TreinamentoDTO update(Long id, TreinamentoDTO dto){
        try {
            Treinamento treinamento = repository.getReferenceById(id);
            copyDtoToEntity(dto, treinamento);
            treinamento.setData_ultima_alteracao(new Date());
            treinamento = repository.save(treinamento);
            return new TreinamentoDTO(treinamento);
        } catch (EntityNotFoundException e){
            throw new IllegalArgumentException("Recurso não encontrado");
        }
    }

    @Transactional
    public TreinamentoDTO publicar(Long id, TreinamentoDTO dto){
        try {
            Treinamento treinamento = repository.getReferenceById(id);
            if(!treinamento.getStatus().equals(Status.PUBLICADO)){
                if(validarCampos(treinamento)){
                    treinamento.setStatus(Status.PUBLICADO);
                    copyDtoToEntity(dto, treinamento);
                    treinamento.setData_ultima_alteracao(new Date());
                    treinamento = repository.save(treinamento);
                }
            }
            return new TreinamentoDTO(treinamento);
        } catch (EntityNotFoundException e){
            throw new IllegalArgumentException("Recurso não encontrado");
        }
    }

    private boolean validarCampos(Treinamento treinamento) {
        boolean valido = false;
        boolean titulo = !treinamento.getTitulo().isEmpty();
        boolean departamento = !treinamento.getDepartamentos().isEmpty();
        boolean descricao = !treinamento.getDescricao().isEmpty();
        boolean treinador = treinamento.getTreinador() != null;
        boolean status = treinamento.getStatus() != null;
        boolean tipo = treinamento.getTipo() != null;
        valido = titulo && departamento && descricao && treinador && status && tipo;

        return valido;
    }

    private void copyDtoToEntity(TreinamentoDTO dto, Treinamento entity) {
        entity.setCorpo_texto(dto.getCorpo_texto());
        entity.setDescricao(dto.getDescricao());
        entity.setTipo(dto.getTipo());
        entity.setData_criacao(dto.getData_criacao());
        entity.setData_ultima_alteracao(dto.getData_ultima_alteracao());
        entity.setStatus(dto.getStatus());
        entity.setTitulo(dto.getTitulo());
        entity.setTreinador(treinadorRepository.findById(dto.getTreinador().getId()).orElseThrow(
                () -> new IllegalArgumentException("Recurso não encontrado")
        ));

        dto.getListaDePresencaList().forEach(listaDePresencaDTO -> {
            ListaDePresenca listaDePresenca = listaDePresencaRepository.findById(listaDePresencaDTO.getId()).orElseThrow(
                    () -> new IllegalArgumentException("Recurso não encontrado")
            );
            entity.getListaDePresencaList().add(listaDePresenca);
        });

        dto.getDepartamentos().forEach(departamentoDTO -> {
            Departamento departamento = departamentoRepository.findById(departamentoDTO.getId()).orElseThrow(
                    () -> new IllegalArgumentException("Recurso não encontrado")
            );
            entity.getDepartamentos().add(departamento);
        });
        // somente treinador vê todos os func.
        // talvez dar clear qnd for direcionado a não treinadores
//        entity.getFuncionarios().clear();
//        dto.getFuncionarios().forEach(funcionarioDTO -> {
//            Funcionario funcionario = new Funcionario();
//            funcionario.setId(funcionarioDTO.getId());
//            entity.getFuncionarios().add(funcionario);
//        });
//
//        entity.getListaDePresencaList().clear();
//        dto.getListaDePresencaList().forEach(listaDePresencaDTO -> {
//            ListaDePresenca listaDePresenca = new ListaDePresenca();
//            listaDePresenca.setId(listaDePresencaDTO.getId());
//            entity.getListaDePresencaList().add(listaDePresenca);
//        });
//
//
//        entity.getDepartamentos().clear();
//        dto.getDepartamentos().forEach(departamentoDTO -> {
//            Departamento departamento = new Departamento();
//            departamento.setId(departamentoDTO.getId());
//            entity.getDepartamentos().add(departamento);
//        });
    }
    private void copyDtoToEntitySemLista(TreinamentoDTOSemListaDePresencaEListaDepartamentos dto, Treinamento entity) {
        entity.setCorpo_texto(dto.getCorpo_texto());
        entity.setDescricao(dto.getDescricao());
        entity.setTipo(dto.getTipo());
        entity.setData_criacao(dto.getData_criacao());
        entity.setData_ultima_alteracao(dto.getData_ultima_alteracao());
        entity.setStatus(dto.getStatus());
        entity.setTitulo(dto.getTitulo());
        entity.setTreinador(treinadorRepository.findById(dto.getTreinador_id()).orElseThrow(
                () -> new IllegalArgumentException("Recurso não encontrado")
        ));

        dto.getDepartamentos_id().forEach(departamento_id -> {
            Departamento departamento = departamentoRepository.findById(departamento_id).orElseThrow(
                    () -> new IllegalArgumentException("Recurso não encontrado")
            );
            entity.getDepartamentos().add(departamento);
        });
    }


}
