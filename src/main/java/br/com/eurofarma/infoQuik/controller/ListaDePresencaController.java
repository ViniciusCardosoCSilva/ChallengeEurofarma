package br.com.eurofarma.infoQuik.controller;

import br.com.eurofarma.infoQuik.dto.FuncionarioDTO;
import br.com.eurofarma.infoQuik.dto.ListaDePresencaDTO;
import br.com.eurofarma.infoQuik.dto.TreinamentoDTO;
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
@RequestMapping("/listaDePresencas")
public class ListaDePresencaController {
    @Autowired
    private ListaDePresencaService service;

    @Autowired
    private TreinamentoService treinamentoService;

    @Autowired
    private FuncionarioService funcionarioService;

    @ModelAttribute("funcionarios")
    public List<FuncionarioDTO> funcionarios(){
        return funcionarioService.findAll();
    }

    @ModelAttribute("treinamentos")
    public List<TreinamentoDTO> treinamentos() {
        return treinamentoService.findAll();
    }

    @GetMapping("/form")
    public String loadForm(Model model) {
        model.addAttribute("listaDePresencaDTO", new ListaDePresencaDTO());
        return "listaDePresenca/novo-listaDePresenca";
    }

    @PostMapping()
    public String insert(@Valid ListaDePresencaDTO listaDePresencaDTO,
                         BindingResult result,
                         RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "listaDePresenca/novo-listaDePresenca";
        }
        listaDePresencaDTO = service.insert(listaDePresencaDTO);
        attributes.addFlashAttribute("mensagem", "Lista De Presenca salvo com sucesso");
        return "redirect:/listaDePresencas/form";
    }

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("listaDePresencas", service.findAll());
        return "/listaDePresenca/listar-listaDePresencas";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        ListaDePresencaDTO listaDePresencaDTO = service.findById(id);
        model.addAttribute("listaDePresencaDTO", listaDePresencaDTO);
        return "/listaDePresenca/editar-listaDePresenca";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid ListaDePresencaDTO listaDePresencaDTO,
                         BindingResult result) {
        if (result.hasErrors()) {
            listaDePresencaDTO.setId(id);
            return "/listaDePresenca/editar-listaDePresenca";
        }
        service.update(id, listaDePresencaDTO);
        return "redirect:/listaDePresencas";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        service.delete(id);
        return "redirect:/listaDePresencas";
    }

}
