package br.com.eurofarma.infoQuik.service;

import br.com.eurofarma.infoQuik.repository.FuncionarioRepository;
import br.com.eurofarma.infoQuik.repository.TreinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    TreinadorRepository treinadorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (funcionarioRepository.existsByEmail(email)) return funcionarioRepository.findByEmail(email);
        else return treinadorRepository.findByEmail(email);

    }
}
