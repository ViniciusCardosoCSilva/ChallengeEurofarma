package br.com.eurofarma.infoQuik.controller;

import br.com.eurofarma.infoQuik.dto.DepartamentoDTO;
import br.com.eurofarma.infoQuik.dto.FuncionarioDTO;
import br.com.eurofarma.infoQuik.dto.ListaDePresencaDTO;
import br.com.eurofarma.infoQuik.dto.TreinamentoDTO;
import br.com.eurofarma.infoQuik.repository.ListaDePresencaRepository;
import br.com.eurofarma.infoQuik.service.DepartamentoService;
import br.com.eurofarma.infoQuik.service.FuncionarioService;
import br.com.eurofarma.infoQuik.service.ListaDePresencaService;
import br.com.eurofarma.infoQuik.service.TreinamentoService;
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
@RequestMapping("/funcionarioos")
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
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long id) {
        FuncionarioDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> insert(@Valid FuncionarioDTO dto) {

        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> update(@PathVariable @NotNull Long id,
                         @Valid FuncionarioDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
