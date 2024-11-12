package it.softwaredoctor.api_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final String[] freeResourcesUrls = {"/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/api-docs/**", "/aggregate/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(freeResourcesUrls)
                        .permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .csrf(csrf -> csrf.disable())
                .build();
    }
}

/*
La configurazione di Spring Security che hai mostrato gestisce la protezione delle rotte tramite OAuth2 Resource Server e l'autenticazione delle richieste tramite JWT
OAuth2 Resource Server con JWT: La configurazione .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())) indica che l'applicazione è configurata come Resource Server per OAuth2.
OAuth2 Resource Server con JWT: La configurazione .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())) indica che l'applicazione è configurata come Resource Server per OAuth2. Questo significa che le richieste a risorse protette (come gli endpoint delle API) devono includere un token JWT valido nell'intestazione Authorization. Spring Security con la configurazione oauth2.jwt si occupa di validare il token JWT.

La configurazione Customizer.withDefaults() usa la configurazione predefinita per la gestione dei JWT. In pratica, Spring Security cercherà di convalidare il token JWT, che dovrebbe essere firmato da Keycloak o da un altro sistema di autenticazione (come un Identity Provider).


 */