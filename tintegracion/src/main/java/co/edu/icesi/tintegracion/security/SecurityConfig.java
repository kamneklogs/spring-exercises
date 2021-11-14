package co.edu.icesi.tintegracion.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import co.edu.icesi.tintegracion.model.users.Usertypes;

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

		httpSecurity.formLogin().loginPage("/login").defaultSuccessUrl("/index.html").permitAll().and()
				.authorizeRequests().antMatchers("/people/**").hasRole(Usertypes.ADMINISTRATOR.toString())
				.antMatchers("/payment/**").hasRole(Usertypes.ADMINISTRATOR.toString()).antMatchers("/departments/**")
				.hasRole(Usertypes.OPERATOR.toString()).antMatchers("/departmentsHistory/**")
				.hasRole(Usertypes.OPERATOR.toString()).anyRequest().authenticated().and().httpBasic().and().logout()
				.invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
				.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}
}