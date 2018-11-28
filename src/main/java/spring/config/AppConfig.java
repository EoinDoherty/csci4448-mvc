package spring.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.*;


/**
 * Configures the application
 * @author eoin
 *
 */
@Configuration
@EnableWebMvc
@EnableWebSecurity
@ComponentScan(basePackages = "spring.controllers") // Scans for controllers in specified package
public class AppConfig implements WebMvcConfigurer {
	
	/**
	 * Create view classes for each jsp in /WEB-INF/views/
	 * @return ViewResolver
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = 
				new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	/**
	 * Add viewControllers for the specified pages
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//registry.addViewController("/index");
		registry.addViewController("/login");
		registry.addRedirectViewController("/index", "/login");
	}
	
}
