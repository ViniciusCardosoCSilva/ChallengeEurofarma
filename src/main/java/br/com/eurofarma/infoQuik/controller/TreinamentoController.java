package br.com.eurofarma.infoQuik.controller;

import br.com.eurofarma.infoQuik.dto.treinamentoDTO.TreinamentoDTO;
import br.com.eurofarma.infoQuik.service.*;
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
@RequestMapping("/treinamentos")
public class TreinamentoController {
    @Autowired
    private TreinamentoService service;

    @Autowired
    private TagService tagService;

    @Autowired
    private TreinadorService treinadorService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ListaDePresencaService listaDePresencaService;

    @GetMapping
    public ResponseEntity<List<TreinamentoDTO>> findAll() {
        List<TreinamentoDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinamentoDTO> findById(@PathVariable @NotNull Long id) {
        TreinamentoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TreinamentoDTO> insert(@RequestBody @Valid TreinamentoDTO dto) {

        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TreinamentoDTO> update(@PathVariable @NotNull Long id,
                                                 @RequestBody @Valid TreinamentoDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        service.delete(id);
//        return ResponseEntity.noContent().build();
//    }

}
