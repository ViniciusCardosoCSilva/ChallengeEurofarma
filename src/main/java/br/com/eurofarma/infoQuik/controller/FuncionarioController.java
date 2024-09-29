package br.com.eurofarma.infoQuik.controller;

import br.com.eurofarma.infoQuik.dto.funcionarioDTO.FuncionarioDTO;
import br.com.eurofarma.infoQuik.service.FuncionarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

//    @Autowired
//    private DepartamentoService departamentoService;
//
//    @Autowired
//    private ListaDePresencaService listaDePresencaService;
//
//    @Autowired
//    private TreinamentoService treinamentoService;
//
//    @ModelAttribute("reinamentos")
//    public List<TreinamentoDTO> treinamentos(){
//        return treinamentoService.findAll();
//    }
//
//    @ModelAttribute("listaDePresencas")
//    public List<ListaDePresencaDTO> listaDePresencas(){
//        return listaDePresencaService.findAll();
//    }
//
//    @ModelAttribute("departamento")
//    public List<DepartamentoDTO> departamentos() {
//        return departamentoService.findAll();
//    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> findAll() {
        List<FuncionarioDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable @NotNull Long id) {
        FuncionarioDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> insert(@RequestBody @Valid FuncionarioDTO dto) {

        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> update(@PathVariable @NotNull Long id,
                                                 @RequestBody @Valid FuncionarioDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
