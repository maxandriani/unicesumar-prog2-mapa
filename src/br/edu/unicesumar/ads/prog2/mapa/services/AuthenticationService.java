package br.edu.unicesumar.ads.prog2.mapa.services;

import br.edu.unicesumar.ads.prog2.mapa.dao.JdbcConnection;
import br.edu.unicesumar.ads.prog2.mapa.entities.Usuario;
import br.edu.unicesumar.ads.prog2.mapa.exceptions.UnauthorizedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author max.andriani
 */
public class AuthenticationService implements IAuthenticationService {
    private static final String SQL_AUTHENTICATION = "SELECT u.id, u.nome, u.senha, u.email, u.login FROM usuario u WHERE u.login = ? AND u.senha = ? ";
    
    private static Connection jdbcConnection;
    private static Usuario authenticatedUser;

    public AuthenticationService() {
        jdbcConnection = JdbcConnection.getConnection();
    }
    
    @Override
    public Boolean isAuthenticated() {
        return (authenticatedUser != null);
    }
    
    @Override
    public Optional<Usuario> getAuthenticatedUser() {
        if (!isAuthenticated()) return Optional.empty();
        
        return Optional.of(authenticatedUser);
    }
    
    @Override
    public void clear() {
        authenticatedUser = null;
    }
    
    @Override
    public void authenticate(AuthenticateRequest user) throws UnauthorizedException {
        try {
            clear();
            
            var statement = jdbcConnection.prepareStatement(SQL_AUTHENTICATION);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());

            var result = statement.executeQuery();
            if (!result.next())
                throw new UnauthorizedException(user.getUsername());
            
            authenticatedUser = new Usuario();
            authenticatedUser
                .setId(result.getLong("id"))
                .setEmail(result.getString("email"))
                .setLogin(result.getString("login"))
                .setNome(result.getString("nome"))
                .setHashSenha(result.getString("senha"));
            
        } catch (SQLException ex) {
            throw new UnauthorizedException(user.getUsername(), ex);
        }
    }
}
