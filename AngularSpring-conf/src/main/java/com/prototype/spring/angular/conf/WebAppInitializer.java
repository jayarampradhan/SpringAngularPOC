/*******************************************************************************
 * Copyright (c) 2015 Uimirror.com.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of Apache V2.0
 * which accompanies this distribution, and is available at
 * 
 *
 * Contributors:
 * Uimirror.com. - initial API and implementation
 *******************************************************************************/
package com.prototype.spring.angular.conf;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * <p>Configures the servlet container for the context uri path
 * @author Jayaram
 */
@Configuration
public class WebAppInitializer extends SpringBootServletInitializer {
	
	@Override  
    public void onStartup(ServletContext container) throws ServletException {  
		super.onStartup(container);
    } 

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebAppInitializer.class);
    }
	
//	@Override
//	  protected Filter[] getServletFilters() {
//	    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//	    characterEncodingFilter.setEncoding(StandardCharsets.UTF_8.name());
//	    return new Filter[] { characterEncodingFilter };
//	  }

    @Bean
    public DispatcherServlet dispatcherServlet() {
    	// Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(DispatcherConfig.class);
        return new DispatcherServlet(dispatcherContext);
    }

    @Bean
    public ServletRegistrationBean dispatcherServletRegistration() {
    	
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet());
        registration.addUrlMappings("/*");
        registration.setLoadOnStartup(1);
        //registration.setAsyncSupported(Boolean.TRUE);
        return registration;
    }
    
}