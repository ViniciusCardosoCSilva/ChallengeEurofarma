package br.com.eurofarma.infoQuik.controller;

import br.com.eurofarma.infoQuik.dto.*;
import br.com.eurofarma.infoQuik.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping()
    public String insert(@Valid TreinamentoDTO treinamentoDTO,
                         BindingResult result,
                         RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "treinamento/novo-treinamento";
        }
        treinamentoDTO = service.insert(treinamentoDTO);
        attributes.addFlashAttribute("mensagem", "Treinamento salvo com sucesso");
        return "redirect:/treinamentos/form";
    }

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("treinamentos", service.findAll());
        return "/treinamento/listar-treinamentos";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        TreinamentoDTO treinamentoDTO = service.findById(id);
        model.addAttribute("treinamentoDTO", treinamentoDTO);
        return "/treinamento/editar-treinamento";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid TreinamentoDTO treinamentoDTO,
                         BindingResult result) {
        if (result.hasErrors()) {
            treinamentoDTO.setId(id);
            return "/treinamento/editar-treinamento";
        }
        service.update(id, treinamentoDTO);
        return "redirect:/treinamentos";
    }

}
