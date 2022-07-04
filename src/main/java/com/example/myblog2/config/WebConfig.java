package com.example.myblog2.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

@Configuration
public class WebConfig {
	
	/**
	 * 
	 * 스프링 컨테이너는 @Configuration이 붙어있는 클래스를 자동으로 빈으로 등록해주고,
	 * 해당 클래스를 파싱해서 @Bean이 있는 메서드를 찾아서 빈을 생성해준다.
	 * @Bean을 사용하는 클래스에 반드시 @Configuration 어노테이션을 활용하여
	 * 해당 클래스에서 Bean을 등록하고자 함을 명시해주어야 한다.
	 */

	@Bean // 싱글톤
	public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean() {
		FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new XssEscapeServletFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/*"); // 필터 적용할 url 설정
		
		return filterRegistrationBean;
	}
}
