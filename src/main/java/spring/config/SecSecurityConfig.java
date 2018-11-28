package spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;;

// Source: https://www.baeldung.com/spring-security-login

/**
 * Security configuration for login and signup
 * @author eoin
 *
 */
@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/**
	 * Configure authentication manager 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("user").password("password");
	}
	
	/**
	 * Configure http security
	 */
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/login*").anonymous()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/user")
		.failureUrl("/login?error=true")
		.and()
		.logout().logoutSuccessUrl("/login");
	}
}
