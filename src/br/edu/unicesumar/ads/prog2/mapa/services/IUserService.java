package br.edu.unicesumar.ads.prog2.mapa.services;

import br.edu.unicesumar.ads.prog2.mapa.entities.Usuario;
import br.edu.unicesumar.ads.prog2.mapa.exceptions.BusinessException;
import java.sql.SQLException;
import java.util.stream.Stream;

/**
 *
 * @author max.andriani
 */
public interface IUserService {
    
    void cadastrar(Usuario user) throws SQLException, BusinessException;
    
    void remover(Usuario user) throws SQLException, BusinessException;
    
    void alterar(Usuario user) throws SQLException, BusinessException;
    
    Usuario getById(Long id) throws SQLException, BusinessException;
    
    Stream<Usuario> getAll() throws SQLException, BusinessException;
    
}
