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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
//@PropertySource("classpath:thymeleaf.properties")
public class ThymeleafConfig {


  @Bean
  public TemplateResolver defaultTemplateResolver(){
  	ClassLoaderTemplateResolver clTemplateResolver = new ClassLoaderTemplateResolver();
		clTemplateResolver.setPrefix("pages/");
		clTemplateResolver.setSuffix(".html");
		clTemplateResolver.setTemplateMode("HTML5");
		clTemplateResolver.setCharacterEncoding("utf-8");
		//clTemplateResolver.setOrder(1);
		clTemplateResolver.setCacheable(Boolean.TRUE);
		clTemplateResolver.initialize();
		return clTemplateResolver;
  }

  @Bean
  public SpringTemplateEngine templateEngine(){
      SpringTemplateEngine templateEngine = new SpringTemplateEngine();
      templateEngine.setTemplateResolver(defaultTemplateResolver());
      return templateEngine;
  }

  @Bean
  public ViewResolver viewResolver(){
      ThymeleafViewResolver viewResolver = new ThymeleafViewResolver() ;
      viewResolver.setTemplateEngine(templateEngine());
//      viewResolver.setOrder(1);
//      viewResolver.setViewNames(new String[]{"*.html","*.xhtml"});

      return viewResolver;
  }

  /**
   //Example for JSP
   @Bean
   public ViewResolver viewResolver() {
       InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
       viewResolver.setViewClass(JstlView.class);
       viewResolver.setPrefix("/WEB-INF/pages/");
       viewResolver.setSuffix(".jsp");
       return viewResolver;
   }
   **/
}