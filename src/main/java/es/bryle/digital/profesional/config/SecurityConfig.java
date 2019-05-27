package es.bryle.digital.profesional.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import es.bryle.digital.profesional.service.interfaces.AuthUserService;

@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private AuthUserService authUserService;
	 
	private static final String[] AUTH_LIST = {
	        // -- swagger ui
	        "**/swagger-resources/**",
	        "/swagger-ui.html",
	        "/v2/api-docs",
	        "/webjars/**",
	        "/css/**", "/js/**", "/images/**", "/reset-password"
	};

	@Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserService)
            .passwordEncoder(encoder);
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	    .authorizeRequests().antMatchers(AUTH_LIST).permitAll()
	    //.antMatchers("/oauth/token").permitAll()
	    .anyRequest().authenticated()
	    .and().formLogin().loginPage("/login")
	    .defaultSuccessUrl("/controller/professional-operations/professional-list").permitAll()
	    .and()
	    .logout().permitAll()
	    .and()
	    .exceptionHandling().accessDeniedPage("/error_404")
	    .and();
	    /*.and()
	    .httpBasic().authenticationEntryPoint(swaggerAuthenticationEntryPoint())
	    .and()
	    .csrf().disable()
	    .cors()*/;
	}

	/*@Bean
	public BasicAuthenticationEntryPoint swaggerAuthenticationEntryPoint() {
	    BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
	    entryPoint.setRealmName("Swagger Realm");
	    return entryPoint;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}*/
	
}
