package br.com.eurofarma.infoQuik.controller;

import br.com.eurofarma.infoQuik.dto.TreinadorDTO;
import br.com.eurofarma.infoQuik.service.TreinadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/treinadors")
public class TreinadorController {
    @Autowired
    private TreinadorService service;

    @GetMapping("/form")
    public String loadFormTreinador(Model model) {
        model.addAttribute("treinadorDTO", new TreinadorDTO());
        return "treinador/nova-treinador";
    }

    @PostMapping()
    public String insert(@Valid TreinadorDTO treinadorDTO,
                         BindingResult result,
                         RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "treinador/nova-treinador";
        }
        treinadorDTO = service.insert(treinadorDTO);
        attributes.addFlashAttribute("mensagem", "Treinador salva com sucesso!");
        return "redirect:/treinadors";
    }

    @GetMapping()
    public String findAll(Model model) {
        List<TreinadorDTO> treinadorsDTO = service.findAll();
        model.addAttribute("treinadorsDTO", treinadorsDTO);
        return "/treinador/listar-treinadors";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        TreinadorDTO treinadorDTO = service.findById(id);
        model.addAttribute("treinadorDTO", treinadorDTO);
        return "/treinador/editar-treinador";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid TreinadorDTO treinadorDTO,
                         BindingResult result) {
        if (result.hasErrors()) {
            treinadorDTO.setId(id);
            return "/treinador/editar-treinador";
        }
        service.update(id, treinadorDTO);
        return "redirect:/treinadors";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        service.delete(id);
        return "redirect:/treinadors";
    }
    
}
