package co.edu.icesi.ci.thymeval.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import co.edu.icesi.ci.thymeval.model.UserType;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;

	// @Autowired
	// private MyCustomUserDetailsService userDetailsService;

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws Exception
	// {
	// auth.authenticationProvider(authenticationProvider());
	// }

	// @Bean
	// public DaoAuthenticationProvider authenticationProvider() {
	// DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	// authProvider.setUserDetailsService(userDetailsService);
	// authProvider.setPasswordEncoder(encoder());
	// return authProvider;
	// }
	//
	// @Bean
	// public PasswordEncoder encoder() {
	// return new BCryptPasswordEncoder(11);
	// }

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

//		httpSecurity.authorizeRequests().antMatchers("/secure/**").authenticated().anyRequest().authenticated().and()
//				.formLogin().loginPage("/login").permitAll().and().authorizeRequests().antMatchers("/users/**").hasRole(UserType.ADMIN.toString()).anyRequest().authenticated();
		/*
		 * .and().logout().invalidateHttpSession(true).clearAuthentication(true)
		 * .logoutRequestMatcher(new
		 * AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
		 * .permitAll().and().exceptionHandling().accessDeniedHandler(
		 * accessDeniedHandler)
		 */
		
		httpSecurity//.userDetailsService(myCustomUserDetailsService)
		// se deshabilita para que funcionen las peticiones a los rest controllers, es mala practica deshabilitarlo, corregirlo de otra manera
//		.authorizeRequests().anyRequest().authenticated().and()
		.formLogin()
		.loginPage("/login").permitAll()
		.and().authorizeRequests()
		.antMatchers("/users/**")
		.hasRole(UserType.ADMIN.toString()).antMatchers("/apps/**").hasRole(UserType.doctor.toString()).anyRequest().authenticated().and()
		.httpBasic().and().logout().invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout").permitAll().and().exceptionHandling()
		.accessDeniedHandler(accessDeniedHandler);
	}
}