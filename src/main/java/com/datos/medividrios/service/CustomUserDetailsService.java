package com.datos.medividrios.service;

import com.datos.medividrios.model.Usuario;
import com.datos.medividrios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        if (!"Rubiel".equalsIgnoreCase(input) && !"rubiel@cliente.com".equalsIgnoreCase(input)) {
            throw new UsernameNotFoundException("No autorizado para acceder");
        }

        Usuario usuario = usuarioRepository.findByNombreOrCorreo(input, input)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getCorreo())
                .password(usuario.getHashClave())
                .roles(usuario.getRol().replace("ROLE_", ""))
                .build();
    }
}
