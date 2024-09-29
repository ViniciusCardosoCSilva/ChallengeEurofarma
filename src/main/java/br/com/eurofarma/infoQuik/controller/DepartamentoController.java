package br.com.eurofarma.infoQuik.controller;

import br.com.eurofarma.infoQuik.dto.departamentoDTO.DepartamentoDTO;
import br.com.eurofarma.infoQuik.service.DepartamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService service;

//    @Autowired
//    private FuncionarioService funcionarioService;
//
//    @Autowired
//    private TreinamentoService treinamentoService;
//
//    public List<TreinamentoDTO> treinamentos(){
//        return treinamentoService.findAll();
//    }
//
//    public List<FuncionarioDTO> funcionarios() {
//        return funcionarioService.findAll();
//    }

    @GetMapping
    public ResponseEntity<List<DepartamentoDTO>> findAll() {

        List<DepartamentoDTO> dto = service.findAll();

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> findById(@PathVariable @NotNull Long id) {
        DepartamentoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DepartamentoDTO> insert(@RequestBody @Valid DepartamentoDTO dto) {

        dto = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> update(@PathVariable @NotNull Long id,
                                                  @RequestBody @Valid DepartamentoDTO dto) {

        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
