
package br.edu.unicesumar.ads.prog2.mapa.views;

import br.edu.unicesumar.ads.prog2.mapa.services.AuthenticateRequest;
import br.edu.unicesumar.ads.prog2.mapa.services.AuthenticationService;
import br.edu.unicesumar.ads.prog2.mapa.services.IAuthenticationService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class LoginView extends JFrame {
    private IAuthenticationService authenticationService;
    
    private JTextField usuarioField;
    private JPasswordField senhaField;

    public LoginView() {
        authenticationService = new AuthenticationService();
        
        // Configurar a janela principal
        setTitle("Formulário de Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 220);

        // Painel para conter os campos de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        
        var labelsGbc = new GridBagConstraints();
        labelsGbc.insets = new Insets(10, 10, 10, 10); // global padding
        labelsGbc.gridx = 0;
        labelsGbc.gridy = 0;
        labelsGbc.gridwidth = 1;
        
        var fieldsGbc = new GridBagConstraints();
        fieldsGbc.insets = new Insets(10, 10, 10, 10); // global padding
        fieldsGbc.gridx = 1;
        fieldsGbc.gridy = 0;
        fieldsGbc.gridwidth = 2;

        JLabel usuarioLabel = new JLabel("Usuário:");
        usuarioField = new JTextField(20);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField(20);

        inputPanel.add(usuarioLabel, labelsGbc);
        inputPanel.add(usuarioField, fieldsGbc);
        
        labelsGbc.gridy++;
        fieldsGbc.gridy++;
        inputPanel.add(senhaLabel, labelsGbc);
        inputPanel.add(senhaField, fieldsGbc);

        // Painel para conter os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton loginButton = new JButton("Login");
        JButton criarUsuarioButton = new JButton("Criar novo usuário");

        buttonPanel.add(loginButton);
        buttonPanel.add(criarUsuarioButton);

        // Adicionar ação ao botão de Login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    var authReq = new AuthenticateRequest();
                    authReq
                        .setUsername(usuarioField.getText())
                        .setPassword(new String(senhaField.getPassword()));

                    if (!authenticationService.isAuthenticated()) {
                        authenticationService.authenticate(authReq);
                    }
                    
                    JOptionPane.showMessageDialog(rootPane, "Acesso autorizado!");

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            var appView = new AppView();
                            appView.setVisible(true);
                        }
                    });

                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LoginView.this, ex);
                }
            }
        });

        // Adicionar ação ao botão de Criar novo usuário
        criarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        var novoUsuario = new CadastroUsuarioForm();
                        novoUsuario.setVisible(true);
                    }
                });
            }
        });
        
        var contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        contentPanel.add(inputPanel);
        contentPanel.setMinimumSize(new Dimension(420, 320));
        contentPanel.setBorder(new EmptyBorder(20, 10, 20, 10));

        // Adicionar os painéis à janela principal
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        centralize();
    }
    
    public void centralize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int frameWidth = this.getWidth();
        int frameHeight = this.getHeight();

        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;

        this.setLocation(x, y);
    }
}
