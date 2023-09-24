package br.edu.unicesumar.ads.prog2.mapa.exceptions;

/**
 *
 * @author max.andriani
 */
public class UnauthorizedException extends Exception {
    private String username;

    public UnauthorizedException(String username, Throwable cause) {
        super("Usuário " + username + " não autorizado.", cause);
        this.username = username;
    }

    public UnauthorizedException(String username) {
        super("Usuário " + username + " não autorizado.");
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }
}
