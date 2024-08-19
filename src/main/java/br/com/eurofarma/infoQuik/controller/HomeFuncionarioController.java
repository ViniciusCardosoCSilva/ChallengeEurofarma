package br.com.eurofarma.infoQuik.controller;


import br.com.eurofarma.infoQuik.dto.TreinamentoDTO;
import br.com.eurofarma.infoQuik.model.Departamento;
import br.com.eurofarma.infoQuik.model.Funcionario;
import br.com.eurofarma.infoQuik.model.Treinamento;
import br.com.eurofarma.infoQuik.service.TreinamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

public class HomeFuncionarioController {

    @Autowired
    private TreinamentoService treinamentoService;

    @RequestMapping("/")
    public String index(Model model){
        // dado mocado do funcionario
        Funcionario funcionario = new Funcionario();
        Departamento departamento = new Departamento();
        departamento.setNome("Marketing");
        departamento.setId(10L);
        funcionario.setNome("Juninho Play Soares");
        funcionario.setDepartamento(departamento);

        List<TreinamentoDTO> treinamentosByDepartamento = treinamentoService.findByDepartamentoAndPublicado(funcionario.getDepartamento());
        model.addAttribute("user", funcionario);
        model.addAttribute("treinamentosByDepartamento", treinamentosByDepartamento);
        model.addAttribute("msg","Bem-vindo(a), "+ funcionario.getNome());

        return "treinamento/index";
    }

}
