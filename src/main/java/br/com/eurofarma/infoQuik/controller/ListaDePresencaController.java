package br.com.eurofarma.infoQuik.controller;

import br.com.eurofarma.infoQuik.dto.listadepresencaDTO.ListaDePresencaDTO;
import br.com.eurofarma.infoQuik.service.ListaDePresencaService;
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
@RequestMapping("/listaDePresencas")
public class ListaDePresencaController {
    @Autowired
    private ListaDePresencaService service;

    @GetMapping
    public ResponseEntity<List<ListaDePresencaDTO>> findAll() {
        List<ListaDePresencaDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaDePresencaDTO> findById(@PathVariable @NotNull Long id) {
        ListaDePresencaDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ListaDePresencaDTO> insert(@RequestBody @Valid ListaDePresencaDTO dto) {

        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ListaDePresencaDTO> update(@PathVariable @NotNull Long id,
                                                     @RequestBody @Valid ListaDePresencaDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
