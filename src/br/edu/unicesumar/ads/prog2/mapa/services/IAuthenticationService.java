package br.edu.unicesumar.ads.prog2.mapa.services;

import br.edu.unicesumar.ads.prog2.mapa.entities.Usuario;
import br.edu.unicesumar.ads.prog2.mapa.exceptions.UnauthorizedException;
import java.util.Optional;

/**
 *
 * @author max.andriani
 */
public interface IAuthenticationService {

    void authenticate(AuthenticateRequest user) throws UnauthorizedException;

    void clear();

    Optional<Usuario> getAuthenticatedUser();

    Boolean isAuthenticated();
    
}
