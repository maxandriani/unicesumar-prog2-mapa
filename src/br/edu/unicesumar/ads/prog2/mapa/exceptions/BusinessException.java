package br.edu.unicesumar.ads.prog2.mapa.exceptions;

/**
 *
 * @author max.andriani
 */
public class BusinessException extends Exception {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
