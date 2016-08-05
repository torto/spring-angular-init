package br.com.docautomation.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.docautomation.repository.UserRepository;
import br.com.docautomation.service.UserService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled= true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserRepository usuarioRepository;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 
		// desabilitar configuração externa e caso acesso negado ir para "erro403"
		http.csrf().disable().exceptionHandling().accessDeniedPage("/erro403")
		
			// senão, para login sucesso ir para index
			.and().formLogin().loginPage("/login")
					.defaultSuccessUrl("/")
					.failureUrl("/login?error=bad_credentials").permitAll()
		
			// ou verifica se autorização requerida para outros
			.and().authorizeRequests()
				.antMatchers("/publico/**").permitAll()
				.antMatchers("/public/**").permitAll()
				.antMatchers("/privado/**").hasRole("USER")
				.antMatchers("/restrito/**").hasRole("ADMIN")
				.antMatchers("/**").permitAll();
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// liberar acesso as seguinte pastas para não bloquear evitando erros
		web.ignoring().antMatchers("/webjars/**");
		web.ignoring().antMatchers("/static/**");
	}
	
	@Bean
	public UserDetailsService userDetailService() {
		return new UserService(usuarioRepository);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService()).passwordEncoder(passwordEncoder());
		//Testar sem criptografia cadastrando direto no banco
		//auth.inMemoryAuthentication().withUser("pedro").password("123").roles("USER", "ADMIN");
	}
	
	
}
