package com.springsecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

public class MVCConfiguration extends WebMvcConfigurerAdapter {

	 @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addViewController("home").setViewName("home");
	        //registry.addViewController("/hello").setViewName("helloworld");
	        //registry.addRedirectViewController("/home", "/hello");
	        //registry.addStatusController("/detail", HttpStatus.BAD_REQUEST);        
	    }    
}
