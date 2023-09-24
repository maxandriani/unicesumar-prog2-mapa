package br.edu.unicesumar.ads.prog2.mapa.exceptions;

/**
 *
 * @author max.andriani
 */
public class EntityNotFoundException extends BusinessException {
    private String entity;

    public EntityNotFoundException(String entity, Object key) {
        super("Entidade " + entity + " não encontrada na chave " + key.toString());
        this.entity = entity;
    }

    public EntityNotFoundException(String entity, Object key, Throwable cause) {
        super("Entidade " + entity + " não encontrada na chave " + key.toString(), cause);
        this.entity = entity;
    }
    
    public String getEntity() {
        return entity;
    }
    
    @Override
    public String toString() {
        return getMessage();
    }
}
