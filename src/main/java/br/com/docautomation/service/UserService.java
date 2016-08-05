package br.com.docautomation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.docautomation.model.UserSys;
import br.com.docautomation.repository.UserRepository;

public class UserService implements UserDetailsService {
	
	private UserRepository usuarioRepository;
	
	@Autowired
	public UserService(UserRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserSys usuario = usuarioRepository.findByUsername(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Login inválido!");
		}
		return usuario;
	}
	

}
