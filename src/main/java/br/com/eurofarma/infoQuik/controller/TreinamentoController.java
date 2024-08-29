package br.com.eurofarma.infoQuik.controller;

import br.com.eurofarma.infoQuik.dto.*;
import br.com.eurofarma.infoQuik.service.*;
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


    @ModelAttribute("tags")
    public List<TagDTO> tags(){
        return tagService.findAll();
    }
    @ModelAttribute("treinadors")
    public List<TreinadorDTO> treinadors(){
        return treinadorService.findAll();
    }
    @ModelAttribute("departamentos")
    public List<DepartamentoDTO> departamentos(){
        return departamentoService.findAll();
    }
    @ModelAttribute("funcionarios")
    public List<FuncionarioDTO> funcionarios(){
        return funcionarioService.findAll();
    }
    @ModelAttribute("listaDePresencas")
    public List<ListaDePresencaDTO> listaDePresencas(){
        return listaDePresencaService.findAll();
    }

    @GetMapping("/form")
    public String loadForm(Model model) {
        model.addAttribute("treinamentoDTO", new TreinamentoDTO());
        return "treinamento/novo-treinamento";
    }

    @GetMapping
    public ResponseEntity<List<TreinamentoDTO>> findAll() {
        List<TreinamentoDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinamentoDTO> findById(@PathVariable Long id) {
        TreinamentoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TreinamentoDTO> insert(@Valid TreinamentoDTO dto) {

        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TreinamentoDTO> update(@PathVariable @NotNull Long id,
                                                @Valid TreinamentoDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        service.delete(id);
//        return ResponseEntity.noContent().build();
//    }

}
