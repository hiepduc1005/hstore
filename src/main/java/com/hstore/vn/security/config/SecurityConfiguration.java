package com.hstore.vn.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hstore.vn.security.JwtAuthEntryPoint;
import com.hstore.vn.security.CustomUserDetailService;
import com.hstore.vn.security.DefaultAuthenticationProvider;
import com.hstore.vn.security.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true , securedEnabled = true )
public class SecurityConfiguration {
	
	@Autowired
	public final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public CustomUserDetailService customUserDetailService;
	
	
	@Autowired
	public JwtAuthEntryPoint jwtAuthEntryPoint;


	public SecurityConfiguration(BCryptPasswordEncoder bCryptPasswordEncoder, CustomUserDetailService customUserDetailService , JwtAuthEntryPoint jwtAuthEntryPoint) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.customUserDetailService = customUserDetailService;
		this.jwtAuthEntryPoint = jwtAuthEntryPoint;
	}

	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		return new DefaultAuthenticationProvider();
	}
	
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new JwtAuthEntryPoint();
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public JWTAuthenticationFilter authenticationFilter() {
		return new JWTAuthenticationFilter();
	}
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		    .csrf(csrf -> csrf.disable())
		    
		    .exceptionHandling(authEntryPoint ->
		                       authEntryPoint.authenticationEntryPoint(authenticationEntryPoint()))
		    
		    .sessionManagement(sessionManage -> 
		                       sessionManage
		                                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		    .authorizeHttpRequests(requestMatcher ->
		                       requestMatcher
		                                    .requestMatchers("api/v1/auth/**")
		                                    .permitAll())
		    .authorizeHttpRequests(requestMatcher -> 
		                       requestMatcher.anyRequest().authenticated())
		    .authenticationProvider(authenticationProvider());
		    
		
		http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		    
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
