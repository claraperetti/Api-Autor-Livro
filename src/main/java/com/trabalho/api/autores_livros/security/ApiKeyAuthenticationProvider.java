package com.trabalho.api.autores_livros.security;
import com.trabalho.api.autores_livros.models.User;
import com.trabalho.api.autores_livros.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyAuthenticationProvider implements AuthenticationProvider{
    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        String apiKey = (String) authentication.getCredentials();

        User user = userRepository.findByApiKey(apiKey).orElseThrow(() -> new BadCredentialsException("Invalid API Key"));

        ApiKeyAuthentication authenticated = new ApiKeyAuthentication(user.getApiKey());
        authenticated.setAuthenticated(true);

        return authenticated;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.isAssignableFrom(authentication);
    }
}
