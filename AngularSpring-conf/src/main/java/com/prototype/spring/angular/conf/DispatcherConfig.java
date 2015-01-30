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

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

@Configuration
@EnableWebMvc
@Import({ThymeleafConfig.class})
@ComponentScan(basePackages= {"com.prototype.spring.angular.controller"})
public class DispatcherConfig extends WebMvcConfigurerAdapter{
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("beforelogin/startapp");
		configureTemplateUrl(registry);
	}

	@Bean
	 public LocaleChangeInterceptor localeChangeInterceptor(){
	     LocaleChangeInterceptor localeChangeInterceptor=new LocaleChangeInterceptor();
	     localeChangeInterceptor.setParamName("lang");
	     return localeChangeInterceptor;
	 }
	 
	 @Bean(name = "localeResolver")
	 public LocaleResolver sessionLocaleResolver(){
		 CookieLocaleResolver localeResolver=new CookieLocaleResolver();
		 localeResolver.setDefaultLocale(new Locale("en"));
		 localeResolver.setCookieName("uim_locale");
		 localeResolver.setCookieMaxAge(36000);
	     return localeResolver;
	 }  
	  
	 public void addInterceptors(InterceptorRegistry registry) {
	     registry.addInterceptor(localeChangeInterceptor());
	     registry.addInterceptor(webContentInterceptor());
	 }
	  
	 @Bean
	 public MessageSource messageSource() {
		 ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		 messageSource.setBasename("classpath:locale/messages");
		 messageSource.setDefaultEncoding("utf-8");
		 return messageSource;
	 }
	 
	 @Bean
	 public WebContentInterceptor webContentInterceptor() {
		 WebContentInterceptor interceptor = new WebContentInterceptor();
		 interceptor.setCacheSeconds(0);
		 interceptor.setUseExpiresHeader(true);
		 interceptor.setUseCacheControlHeader(true);
		 interceptor.setUseCacheControlNoStore(true);
		 return interceptor;
	 }
	 
	 @Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/static/**/*").addResourceLocations("classpath:/assets/");
		 registry.addResourceHandler("/image/**/*").addResourceLocations("classpath:/image/");
		 registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(0);
	 }
	 
	 private void configureTemplateUrl(ViewControllerRegistry registry) {
		 registry.addViewController("/template/addTerminal").setViewName("template/addterminal::terminal");
		 registry.addViewController("/template/addTerminalGroup").setViewName("template/addterminal::terminalGroup");
	 }
	 
	
	
}