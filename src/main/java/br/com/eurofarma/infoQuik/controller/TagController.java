package br.com.eurofarma.infoQuik.controller;

import br.com.eurofarma.infoQuik.dto.TagDTO;
import br.com.eurofarma.infoQuik.service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagService service;

    @GetMapping("/form")
    public String loadFormTag(Model model) {
        model.addAttribute("tagDTO", new TagDTO());
        return "tag/nova-tag";
    }

    @PostMapping()
    public String insert(@Valid TagDTO tagDTO,
                         BindingResult result,
                         RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "tag/nova-tag";
        }
        tagDTO = service.insert(tagDTO);
        attributes.addFlashAttribute("mensagem", "Tag salva com sucesso!");
        return "redirect:/tags";
    }

    @GetMapping()
    public String findAll(Model model) {
        List<TagDTO> tagsDTO = service.findAll();
        model.addAttribute("tagsDTO", tagsDTO);
        return "/tag/listar-tags";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        TagDTO tagDTO = service.findById(id);
        model.addAttribute("tagDTO", tagDTO);
        return "/tag/editar-tag";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid TagDTO tagDTO,
                         BindingResult result) {
        if (result.hasErrors()) {
            tagDTO.setId(id);
            return "/tag/editar-tag";
        }
        service.update(id, tagDTO);
        return "redirect:/tags";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        service.delete(id);
        return "redirect:/tags";
    }
    
}
