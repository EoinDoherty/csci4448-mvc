package spring.config;

import javax.servlet.*;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import mongo.DBCollectionFactory;

/**
 * Intialize the application
 * @author eoin
 *
 */
public class AppInitializer implements WebApplicationInitializer {
	/**
	 * Steps to take on startup
	 * configure servlet, database connection, and DBCollectionFactory
	 */
	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext context = 
				new AnnotationConfigWebApplicationContext();
		
		context.scan("spring.controllers");
		context.register(AppConfig.class);
		context.setServletContext(container);
		
		ServletRegistration.Dynamic servlet = 
				container.addServlet("dispatcher", new DispatcherServlet(context));
		
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
		DBCollectionFactory.initializeFactory();
	}
}
