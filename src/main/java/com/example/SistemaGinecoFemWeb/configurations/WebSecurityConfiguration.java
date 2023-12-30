package com.example.SistemaGinecoFemWeb.configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.SistemaGinecoFemWeb.services.impl.LoginServiceImpl;



@Configuration
@EnableWebSecurity 
public class WebSecurityConfiguration {

	@Autowired
	private LoginServiceImpl ls;

	@Bean
  	public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
  	}

	@Value("${spring.security.debug:false}")
    boolean securityDebug;
	
	//http://localhost:8080/sistemadda/swagger-ui.html
	String[] resources = new String[]{"/css/**","/js/**","/v2/api-docs","/configuration/ui",
	"/swagger-resources/**","/configuration/security","/swagger-ui.html","/webjars/**","/error/","/nosotros","/api/usuarios"};

	@Bean
  	public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       
      authProvider.setUserDetailsService(ls);
      authProvider.setPasswordEncoder(passwordEncoder());
   
      return authProvider;
  }

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

		http.authorizeRequests().antMatchers("/api/auth/**").permitAll()
			.antMatchers("/api/test/**").permitAll()
			.anyRequest().authenticated().and()
			.formLogin()
			.permitAll()
			.loginPage("/login")
			.defaultSuccessUrl("/")
			.failureUrl("/login?error")
			.and()
			.authenticationProvider(authenticationProvider());

        return http.build();
    }

	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(securityDebug)
          .ignoring()
          .antMatchers(resources);
    }

}