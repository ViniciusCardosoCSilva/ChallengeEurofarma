package br.com.eurofarma.infoQuik.controller;

import br.com.eurofarma.infoQuik.dto.AuthenticationDTO;
import br.com.eurofarma.infoQuik.dto.LoginResponseDTO;
import br.com.eurofarma.infoQuik.dto.funcionarioDTO.FuncionarioDTO;
import br.com.eurofarma.infoQuik.dto.treinadorDTO.TreinadorDTO;
import br.com.eurofarma.infoQuik.infra.security.TokenService;
import br.com.eurofarma.infoQuik.model.Funcionario;
import br.com.eurofarma.infoQuik.model.Treinador;
import br.com.eurofarma.infoQuik.model.UserRole;
import br.com.eurofarma.infoQuik.service.FuncionarioService;
import br.com.eurofarma.infoQuik.service.TreinadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private TreinadorService treinadorService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        System.out.println("--------- "+ usernamePassword + auth.getCredentials());
        String token;
        UserRole role = UserRole.FUNCIONARIO;
        Long id = 90L;

        try{
            token = tokenService.generateToken((Treinador) auth.getPrincipal());
            id = treinadorService.findIdByEmail(data.email());
            if(auth.getAuthorities().stream()
                    .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))){
                role = UserRole.ADMIN;
            }
            else {
                role = UserRole.TREINADOR;
            }
        }catch (Exception e){
            token = tokenService.generateTokenFuncionario((Funcionario) auth.getPrincipal());
            id = funcionarioService.findIdByEmail(data.email());
            role = UserRole.FUNCIONARIO;
        }

        return ResponseEntity.ok(new LoginResponseDTO(token, id, role));
    }

    @PostMapping("/register")
    public ResponseEntity<TreinadorDTO> register(@RequestBody @Valid TreinadorDTO dto){

        dto = treinadorService.insert(dto);
        if(dto == null) return ResponseEntity.badRequest().build();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);

    }

    @PostMapping("/register/funcionarios")
    public ResponseEntity<FuncionarioDTO> register(@RequestBody @Valid FuncionarioDTO dto){

        dto = funcionarioService.insert(dto);
        if(dto == null) return ResponseEntity.badRequest().build();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);

    }
}
