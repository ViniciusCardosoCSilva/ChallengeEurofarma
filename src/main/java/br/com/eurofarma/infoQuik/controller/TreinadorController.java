package br.com.eurofarma.infoQuik.controller;

import br.com.eurofarma.infoQuik.dto.TreinadorDTO;
import br.com.eurofarma.infoQuik.service.TreinadorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/treinadores")
public class TreinadorController {
    @Autowired

    private TreinadorService service;

    @GetMapping
    public ResponseEntity<List<TreinadorDTO>> findAll() {
        List<TreinadorDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinadorDTO> findById(@PathVariable Long id) {
        TreinadorDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TreinadorDTO> insert(@Valid TreinadorDTO dto) {

        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TreinadorDTO> update(@PathVariable @NotNull Long id,
                                               @Valid TreinadorDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}