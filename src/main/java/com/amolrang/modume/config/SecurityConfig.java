package com.amolrang.modume.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

import com.amolrang.modume.provider.CommonOAuth2Provider;
import com.amolrang.modume.provider.CustomOAuth2Provider;
import com.amolrang.modume.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity // Spring Security 설정을 이 클래스에서 하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Override
	public void configure(WebSecurity web) throws Exception {
		String[] ignoringUrls = {"/css/**","/js/**","/img/**"};
		// static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
//		web.ignoring().antMatchers("/favicon.ico");
		web.ignoring().antMatchers(ignoringUrls);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// passwordEncoder() -> spring security 5.0부터 필수!!
		// userDetailsService를 사용하려면 무조건 userService를 인자로 넣어줘야 함
		auth.userDetailsService(userService).passwordEncoder(encoder()); // 검색해보기!!!
		log.info("auth:{}", auth);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String[] permitAllurl = {"/","/main","/join","/join","/login","/login/oauth2/**"};
		
		http.csrf().disable(); // 활성화되어 있으면 위변조 사이트인지 판별해줌, 나중에 활성화시켜야 함
		//로그아웃추가
		http.logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/");
		
		// 권한 필요한 경로 추가
		// antMatchers("주소") -> 주소 매칭
		// /admin 주소는 ADMIN 권한을 가진 사람만 접속 가능
		http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN"); 
		// 권한 필요없는 경로 추가
		// loginPage("/login") -> 기본 spring security 로그인 페이지를 내 /login 주소로 매핑함
		// permitAll은 모든 권한 다 됨
		// 로그인 form에서 아이디는 name=username인 input을 기본으로 인식하는데, usernameParameter() 메서드를 통해 파라미터명을 변경할 수 있음
		http.formLogin().loginPage("/login").usernameParameter("user_id").defaultSuccessUrl("/loginAction").permitAll();
		http.authorizeRequests().antMatchers(permitAllurl).permitAll()
		.and()
		.oauth2Login().loginPage("/login").defaultSuccessUrl("/login_success")
		.clientRegistrationRepository(clientRegistrationRepository())
		.authorizedClientService(authorizedClientService())
		// 권한없이 접근한 페이지로 보내는 곳
		.and().exceptionHandling().accessDeniedPage("/denied");
	}
	
	// 패스워드 암호화
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	// OAuth2 토큰 주고 받는 서비스
    public OAuth2AuthorizedClientService authorizedClientService(){
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }
	
	/* 
	 * ClientRegistration - Oauth2에 등록된 Client의 정보를 나타내는 클래스
						  - registrationId를 통해 기초 설정이 된 ClientRegistration을 저장해두고 추후 사용하는 곳에서
						    ClientRegistration.getBuilder(registrationId)를 이용해서 가져와 추가 설정을 할 수 있음
	   ClientRegistrationRepository - Oauth2 제공자들(google, kakao 등)에게 등록된 우리 App의 정보들은 궁극적으로 Oauth2 제공자에 저장되기 때문에 
	      							    우리 App의 Server에서 요청하기 위해서는 이 정보들을 별도로 가지고 있어야 함
               						    이런 Oauth2에 등록된 App의 정보들의 복사본이 저장되어 관리되는 클래스
	 */
	@Bean
	// 인증 사이트(google, kakao 등)의 OAuth2의 등록 정보를 담고 있음
	public ClientRegistrationRepository clientRegistrationRepository() {
		List<ClientRegistration> registrations = new ArrayList<>();
		registrations.add(CommonOAuth2Provider.GOOGLE.getBuilder("google").build());
		registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao").build());
		registrations.add(CommonOAuth2Provider.TWITCH.getBuilder("twitch").build());
		registrations.add(CustomOAuth2Provider.NAVER.getBuilder("naver").build());
		log.info("registrations:{}" + registrations);
		return new InMemoryClientRegistrationRepository(registrations);
	}
}
