
package br.edu.unicesumar.ads.prog2.mapa.services;

import br.edu.unicesumar.ads.prog2.mapa.dao.JdbcConnection;
import br.edu.unicesumar.ads.prog2.mapa.entities.Usuario;
import br.edu.unicesumar.ads.prog2.mapa.exceptions.BusinessException;
import br.edu.unicesumar.ads.prog2.mapa.exceptions.EntityNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author max.andriani
 */
public class UserService implements IUserService {

    private static final String GET_USER_BY_ID = "SELECT u.id, u.login, u.nome, u.senha, u.email FROM usuario u WHERE u.id = ? LIMIT 1";
    private static final String GET_ALL_USERS = "SELECT u.id, u.login, u.nome, u.senha, u.email FROM usuario u";
    private static final String UPDATE_USER = "UPDATE usuario u SET u.nome = ?, u.email = ? u.login = ?, u.senha = ? WHERE u.id = ?";
    private static final String DELETE_USER = "DELETE FROM usuario u WHERE u.id = ?";
    private static final String CREATE_USER = "INSERT INTO usuario (nome, email, login, senha) VALUES (?, ?, ?, ?)";
    private static final String LAST_ID = "SELECT LAST_INSERT_ID()";
    
    private static Connection jdbcConnection;

    public UserService() {
        jdbcConnection = JdbcConnection.getConnection();
    }
    
    @Override
    public void cadastrar(Usuario user) throws SQLException, BusinessException {
        var statement = jdbcConnection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
        
        statement.setString(1, user.getNome());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getLogin());
        statement.setString(4, user.getHashSenha());
        
        var result = statement.executeUpdate();
        
        if (result == 0)
            throw new BusinessException("Não foi possível gravar os dados do usuário " + user.getLogin());
        
        var keys = statement.getGeneratedKeys();
        keys.next();
        
        user.setId(keys.getLong(1));
    }

    @Override
    public void remover(Usuario user) throws SQLException, BusinessException {
        var statement = jdbcConnection.prepareStatement(DELETE_USER);
        
        statement.setLong(0, user.getId());
        
        if (!statement.execute())
            throw new BusinessException("Não foi possível remover o usuário " + user.getLogin());
    }

    @Override
    public void alterar(Usuario user) throws SQLException, BusinessException {
        var statement = jdbcConnection.prepareStatement(UPDATE_USER);
        
        statement.setString(1, user.getNome());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getLogin());
        statement.setString(4, user.getHashSenha());
        statement.setLong(5, user.getId());
        
        if (!statement.execute())
            throw new BusinessException("Não foi possível atualizar os dados do usuário " + user.getLogin());
    }

    @Override
    public Usuario getById(Long id) throws SQLException, BusinessException {
        var statement = jdbcConnection.prepareStatement(GET_USER_BY_ID);
        
        statement.setLong(1, id);
        
        var result = statement.executeQuery();
        
        if (!result.first())
            throw new EntityNotFoundException(Usuario.class.getName(), id);
        
        var user = new Usuario();
        
        return user
                .setId(result.getLong("id"))
                .setNome(result.getString("nome"))
                .setEmail(result.getString("email"))
                .setLogin(result.getString("login"))
                .setHashSenha(result.getString("senha"));
    }

    @Override
    public Stream<Usuario> getAll() throws SQLException, BusinessException {
        var statement = jdbcConnection.prepareStatement(GET_ALL_USERS);
        
        var result = statement.executeQuery();
        var iterator = new Iterator<Usuario>() {
            Boolean hasNext;
            
            @Override
            public boolean hasNext() {
                if (hasNext == null) {
                    try {
                        hasNext = result.next();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                return hasNext;
            }

            @Override
            public Usuario next() {
                hasNext = null;
                var user = new Usuario();
                
                try {
                    return user
                        .setId(result.getLong("id"))
                        .setLogin(result.getString("login"))
                        .setNome(result.getString("nome"))
                        .setEmail(result.getString("email"))
                        .setHashSenha(result.getString("senha"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }  
        };
        
        Iterable<Usuario> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), false);
    }
    
}