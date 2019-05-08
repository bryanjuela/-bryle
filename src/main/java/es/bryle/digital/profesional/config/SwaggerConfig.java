package es.bryle.digital.profesional.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${bryle.oauth.client}")
	private String CLIENT_ID;
	@Value("${bryle.oauth.secret}")
    private String CLIENT_SECRET;
	
	private static final String AUTH_SERVER = "/oauth/token";
	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("es.bryle.digital.profesional.controller"))
				.paths(PathSelectors.any())
				.build()
				.securitySchemes(Arrays.asList(userOAUthScheme()))
				.securityContexts(Arrays.asList(securityContext()));
	}
	
	private SecurityContext securityContext() {
		 return SecurityContext.builder()
		            .securityReferences(
		                Arrays.asList(new SecurityReference("spring_oauth", scopes())))
		            .forPaths(PathSelectors.regex(".*"))
		            .build();
	}

	private AuthorizationScope[] scopes() {
		AuthorizationScope[] scopes = {
	            new AuthorizationScope("read", "for read operations"),
	            new AuthorizationScope("write", "for write operations"),
	            new AuthorizationScope("trust", "for trust operations")};
	        return scopes;
	}

	private OAuth userOAUthScheme() {
		List<AuthorizationScope> authorizationScopeList = new ArrayList<AuthorizationScope>();
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant("http://localhost:9000/oauth/token");
        return new OAuth("oauth2", authorizationScopeList, Arrays.asList(grantType));
	}

	@Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
            .clientId(CLIENT_ID)
            .clientSecret(CLIENT_SECRET)
            .scopeSeparator(" ")
            .useBasicAuthenticationWithAccessCodeGrant(true)
            .build();
    }
}
