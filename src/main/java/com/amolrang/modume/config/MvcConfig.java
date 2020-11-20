package com.amolrang.modume.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
// servlet-context.xml 역할을 함 

public class MvcConfig implements WebMvcConfigurer {
	// 설정 활성화 유무
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// 경로와 확장자 설정 registry.jsp(prefix, suffix)
		registry.jsp("/WEB-INF/jsp", ".jsp");
		// 그다음 controller를 만들어서 controller 어노테이션 붙인 후 requestmapping으로 주소 mapping을 해주고 
		// return 값을 jsp이름으로 설정해주면 이 메소드로 인해 간단하게(경로, 확장자 없이) requestmapping한 주소와 jsp 파일이 연결됨
	}

	// 경로 설정
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/main");
	}

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/").setCachePeriod(20);
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/").setCachePeriod(20);
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/").setCachePeriod(20);
		registry.addResourceHandler("/upload/**").addResourceLocations("file:///usr/local/modume/img/").setCachePeriod(20);
	}
	
	/*
	// 파일 업로드 위한 빈등록
	@Bean 
	public MultipartResolver multipartResolver() { 
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(); 
		multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10 = 10 MB return multipartResolver; }
		 return multipartResolver;
	}
	*/
	

}
