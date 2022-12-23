package br.com.rng.dscatalog.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

   @Autowired
   private JwtTokenStore jwtTokenStore;

   private static final String[] PUBLIC = { "/oauth/token" };

   private static final String[] OPERATOR_OR_ADMIN = { "products/**", "/categories/**" };

   private static final String[] ADMIN = { "/users/**" };

   @Override
   public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

      resources.tokenStore(jwtTokenStore);
   }

   @Override
   public void configure(HttpSecurity http) throws Exception {
      http.csrf().disable();
      http.authorizeRequests()
            .antMatchers(PUBLIC)
            .permitAll()
            .antMatchers(HttpMethod.GET, OPERATOR_OR_ADMIN)
            .permitAll()
            .antMatchers(OPERATOR_OR_ADMIN)
            .hasAnyRole("OPERATOR", "ADMIN")
            .antMatchers(ADMIN)
            .hasRole("ADMIN")
            .anyRequest()
            .authenticated();
   }
}