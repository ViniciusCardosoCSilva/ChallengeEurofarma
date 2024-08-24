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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/funcionarioos")
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private ListaDePresencaService listaDePresencaService;

    @Autowired
    private TreinamentoService treinamentoService;

    @ModelAttribute("reinamentos")
    public List<TreinamentoDTO> treinamentos(){
        return treinamentoService.findAll();
    }
    
    @ModelAttribute("listaDePresencas")
    public List<ListaDePresencaDTO> listaDePresencas(){
        return listaDePresencaService.findAll();
    }

    @ModelAttribute("departamento")
    public List<DepartamentoDTO> departamentos() {
        return departamentoService.findAll();
    }
    @GetMapping("/form")
    public String loadForm(Model model) {
        model.addAttribute("funcionarioDTO", new FuncionarioDTO());
        return "funcionario/novo-funcionario";
    }

    @PostMapping()
    public String insert(@Valid FuncionarioDTO funcionarioDTO,
                         BindingResult result,
                         RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "funcionario/novo-funcionario";
        }
        funcionarioDTO = service.insert(funcionarioDTO);
        attributes.addFlashAttribute("mensagem", "Funcionario salvo com sucesso");
        return "redirect:/funcionarios/form";
    }

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("funcionarios", service.findAll());
        return "/funcionario/listar-funcionarios";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        FuncionarioDTO funcionarioDTO = service.findById(id);
        model.addAttribute("funcionarioDTO", funcionarioDTO);
        return "/funcionario/editar-funcionario";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid FuncionarioDTO funcionarioDTO,
                         BindingResult result) {
        if (result.hasErrors()) {
            funcionarioDTO.setId(id);
            return "/funcionario/editar-funcionario";
        }
        service.update(id, funcionarioDTO);
        return "redirect:/funcionarios";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        service.delete(id);
        return "redirect:/funcionarios";
    }
}
