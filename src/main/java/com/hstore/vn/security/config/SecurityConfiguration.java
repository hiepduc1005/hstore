package com.hstore.vn.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hstore.vn.security.CustomAuthenticationEntryPoint;
import com.hstore.vn.security.CustomUserDetailService;
import com.hstore.vn.security.DefaultAuthenticationProvider;
import com.hstore.vn.service.impl.DefaultUserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true )
public class SecurityConfiguration {
	
	@Autowired
	public final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public final DefaultUserService defaultUserService;
	
	
	@Autowired
	public CustomAuthenticationEntryPoint authenticationEntryPoint;


	public SecurityConfiguration(BCryptPasswordEncoder bCryptPasswordEncoder, DefaultUserService defaultUserService) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.defaultUserService = defaultUserService;
		
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailService();
	}


	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		return new DefaultAuthenticationProvider();
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
    }


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		    .csrf(csrf -> csrf.disable())
		    .sessionManagement(sessionManage -> 
		                       sessionManage
		                                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		    .authorizeHttpRequests(requestMatcher ->
		                       requestMatcher
		                                    .requestMatchers("api/v1/auth/**")
		                                    .permitAll())
		    .authorizeHttpRequests(requestMatcher -> 
		                       requestMatcher.anyRequest().authenticated())
		    .exceptionHandling(authEntryPoint ->
		                       authEntryPoint.authenticationEntryPoint(authenticationEntryPoint));
		
	
		    
		    return http.build();
	} 
	
	 @Bean
	   public WebMvcConfigurer corsConfigurer() {
	       return new WebMvcConfigurer() {
	           @Override
	           public void addCorsMappings(CorsRegistry registry) {
	               registry.addMapping("/**")
	                       .allowedMethods("*");
	           }
	       };
	   }
	
	

}
