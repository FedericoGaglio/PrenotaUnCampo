package it.uniroma3.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String usersQuery = "SELECT username,password,TRUE FROM utente WHERE username = ?";
    private final String rolesQuery = "SELECT username,role FROM utente WHERE username = ?";

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder())
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http
        	.csrf().disable()
        	.authorizeRequests()
        	.antMatchers( "/login").permitAll()
        	.antMatchers("/admin").hasRole("ADMIN")
        	.antMatchers("/", "/index", "/chisiamo", "/circoli","/registrazioneUtente","/oauth_login").permitAll()
        	.antMatchers("/prenotazioniList","/benvenuto").hasRole("USER")
        	.anyRequest().permitAll()
        	.anyRequest().authenticated()
        	.and()
        	.formLogin()
        	.loginPage("/login")
        	.defaultSuccessUrl("/role")
        	.and()
        	.logout()
        	.logoutSuccessUrl("/login")
        	.permitAll()
        	.and()
        	.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    	
    	 http.authorizeRequests()
    	 	.antMatchers("/oauth_login")
    	 	.permitAll()
    	 	.anyRequest()
    	 	.authenticated()
    	 	.and()
    	 	.oauth2Login()
    	 	.loginPage("/oauth_login")
            .defaultSuccessUrl("/loginSuccess")
    	    .failureUrl("/errore403");
    	  	
    }

    //Spring Boot configured this already.
    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/static/**", "/style.css" , "/assets/**", "/css/**", "/images/**", "/fonts/**", "/js/**");
    }
}