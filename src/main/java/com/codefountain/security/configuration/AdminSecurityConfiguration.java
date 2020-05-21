package com.codefountain.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@Order(1)
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/support/admin/**")
		.addFilter(getDigestFilter())
		.exceptionHandling()
		.authenticationEntryPoint(getDigestAuthenticationEntryPoint())
		.and()
		.authorizeRequests().antMatchers("/support/admin/**").hasRole("ADMIN");
	}
	
	public DigestAuthenticationFilter getDigestFilter() {
		DigestAuthenticationFilter digestAuthenticationFilter = new DigestAuthenticationFilter();
		digestAuthenticationFilter.setUserDetailsService(userDetailsService());
		digestAuthenticationFilter.setAuthenticationEntryPoint(getDigestAuthenticationEntryPoint());
		return digestAuthenticationFilter;
		
	}
	private DigestAuthenticationEntryPoint getDigestAuthenticationEntryPoint() {

		DigestAuthenticationEntryPoint digestAuthenticationEntryPoint = new DigestAuthenticationEntryPoint();
		digestAuthenticationEntryPoint.setRealmName("admin-digest-realm");
		digestAuthenticationEntryPoint.setKey("8877665ffggt_/");
		return digestAuthenticationEntryPoint;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("john").password("password").roles("USER").and().withUser("admin")
				.password("password2").roles("ADMIN");
	}
	
	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsServiceBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
