/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Configuration
@ComponentScan("com.photoshop")
@PropertySource("classpath:uploads.properties")
@EnableWebMvc   
public class WebConfig extends WebMvcConfigurerAdapter {
      
    @Bean  
    public UrlBasedViewResolver setupViewResolver() {  
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();  
        resolver.setPrefix("/WEB-INF/jsp/");  
        resolver.setSuffix(".jsp");  
        resolver.setViewClass(JstlView.class);
        return resolver;  
    }  
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/*");
    }
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource result
            = new ResourceBundleMessageSource();
 
        String[] basenames = {
            "i18n.setA.setA",
            "i18n.setB.setB"
        };
 
        result.setBasenames(basenames);
 
        return result;
 
    }
 
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
 
        LocaleChangeInterceptor result = new LocaleChangeInterceptor();
        result.setParamName("lang");
 
        return result;
 
    }
 
    @Bean
    public LocaleResolver localeResolver() {
 
        SessionLocaleResolver result = new SessionLocaleResolver();
        result.setDefaultLocale(Locale.ENGLISH);
 
        return result;
 
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        commonsMultipartResolver.setMaxUploadSize(50000000);
        return commonsMultipartResolver;
    }

    @Bean
    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
        List<MediaType> mediaTypeList = new ArrayList();
        mediaTypeList.add(MediaType.IMAGE_JPEG);
        byteArrayHttpMessageConverter.setSupportedMediaTypes(mediaTypeList);
        return new ByteArrayHttpMessageConverter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}  

