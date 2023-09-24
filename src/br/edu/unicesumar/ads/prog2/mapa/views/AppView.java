package br.edu.unicesumar.ads.prog2.mapa.views;

import br.edu.unicesumar.ads.prog2.mapa.services.AuthenticationService;
import br.edu.unicesumar.ads.prog2.mapa.services.IAuthenticationService;
import br.edu.unicesumar.ads.prog2.mapa.services.IUserService;
import br.edu.unicesumar.ads.prog2.mapa.services.UserService;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author max.andriani
 */
public class AppView extends JFrame {
    
    private IAuthenticationService authenticationService;
    private IUserService userService;
    
    public AppView() {
        authenticationService = new AuthenticationService();
        userService = new UserService();
        
        // Configurar a janela principal
        setTitle("Aplicativo de Gerenciamento de Usuários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Abrir em tela cheia

        // Painel principal com layout de duas colunas
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        getContentPane().add(mainPanel);

        addCurrentUserWidget(mainPanel);
        addUserListColumn(mainPanel);
    }
    
    private void addCurrentUserWidget(JPanel mainPanel) {
        var currentUser = authenticationService.getAuthenticatedUser();
        
        JPanel leftColumn = new JPanel(new BorderLayout());
        mainPanel.add(leftColumn);

        JLabel usuarioAtivoLabel = new JLabel("Usuário Ativo");
        usuarioAtivoLabel.setHorizontalAlignment(JLabel.CENTER);
        leftColumn.add(usuarioAtivoLabel, BorderLayout.NORTH);

        JPanel usuarioAtivoInfoPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        leftColumn.add(usuarioAtivoInfoPanel, BorderLayout.CENTER);

        usuarioAtivoInfoPanel.add(new JLabel("Nome:"));
        usuarioAtivoInfoPanel.add(new JLabel(currentUser.get().getNome()));

        usuarioAtivoInfoPanel.add(new JLabel("Email:"));
        usuarioAtivoInfoPanel.add(new JLabel(currentUser.get().getEmail()));

        usuarioAtivoInfoPanel.add(new JLabel("Login:"));
        usuarioAtivoInfoPanel.add(new JLabel(currentUser.get().getLogin()));
    }
    
    private void addUserListColumn(JPanel mainPanel) {
        JPanel rightColumn = new JPanel(new BorderLayout());
        mainPanel.add(rightColumn);

        JLabel listaUsuariosLabel = new JLabel("Lista de Usuários");
        listaUsuariosLabel.setHorizontalAlignment(JLabel.CENTER);
        rightColumn.add(listaUsuariosLabel, BorderLayout.NORTH);

        // Exemplo de lista de usuários (substitua com seus dados reais)
        DefaultListModel<String> listaModel = new DefaultListModel<>();
        
        try {
            userService.getAll()
                .forEach(item -> {
                    listaModel.addElement("Nome: " + item.getNome() + ", Email: " + item.getEmail());
                });
        } catch (Exception ex) {
            Logger.getLogger(AppView.class.getName()).log(Level.SEVERE, null, ex);
        }

        JList<String> listaUsuarios = new JList<>(listaModel);
        JScrollPane scrollPane = new JScrollPane(listaUsuarios);
        rightColumn.add(scrollPane, BorderLayout.CENTER);
    }
}

