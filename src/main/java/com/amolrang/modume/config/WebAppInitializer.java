package com.amolrang.modume.config;

import javax.servlet.Filter;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// 어노테이션 스캔
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{MvcConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
        return new String[]{"/"};
	}
	
	@Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");

        return new Filter[]{encodingFilter};
    }
}
