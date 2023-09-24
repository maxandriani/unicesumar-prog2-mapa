package br.edu.unicesumar.ads.prog2.mapa.views;

import br.edu.unicesumar.ads.prog2.mapa.entities.Usuario;
import br.edu.unicesumar.ads.prog2.mapa.services.AuthenticateRequest;
import br.edu.unicesumar.ads.prog2.mapa.services.IUserService;
import br.edu.unicesumar.ads.prog2.mapa.services.UserService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author max.andriani
 */


public class CadastroUsuarioForm extends JFrame {
    private IUserService userService;
    
    private JTextField nomeField;
    private JTextField emailField;
    private JTextField loginField;
    private JPasswordField senhaField;

    public CadastroUsuarioForm() {
        userService = new UserService();
        
        // Configurar a janela principal
        setTitle("Formulário de Cadastro de Usuário");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(640, 480);

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

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        JLabel loginLabel = new JLabel("Login:");
        loginField = new JTextField(20);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField(20);

        inputPanel.add(nomeLabel, labelsGbc);
        inputPanel.add(nomeField, fieldsGbc);
        labelsGbc.gridy++;
        fieldsGbc.gridy++;
        inputPanel.add(emailLabel, labelsGbc);
        inputPanel.add(emailField, fieldsGbc);
        labelsGbc.gridy++;
        fieldsGbc.gridy++;
        inputPanel.add(loginLabel, labelsGbc);
        inputPanel.add(loginField, fieldsGbc);
        labelsGbc.gridy++;
        fieldsGbc.gridy++;
        inputPanel.add(senhaLabel, labelsGbc);
        inputPanel.add(senhaField, fieldsGbc);

        // Painel para conter os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton salvarButton = new JButton("Salvar");
        JButton cancelarButton = new JButton("Cancelar");

        buttonPanel.add(salvarButton);
        buttonPanel.add(cancelarButton);

        // Adicionar ação ao botão de Salvar
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    var authReq = new AuthenticateRequest();
                    authReq.setPassword(new String(senhaField.getPassword()));

                    var usuario = new Usuario()
                        .setNome(nomeField.getText())
                        .setEmail(emailField.getText())
                        .setLogin(loginField.getText())
                        .setHashSenha(authReq.getPassword());

                    userService.cadastrar(usuario);

                    JOptionPane.showMessageDialog(CadastroUsuarioForm.this, "Usuário " + usuario.getNome() + " cadastrado com sucesso!");
                    
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CadastroUsuarioForm.this, ex);
                }
            }
        });

        // Adicionar ação ao botão de Cancelar
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fechar a janela
                dispose();
            }
        });
        
        var formContainer = new JPanel();
        formContainer.add(inputPanel);
        formContainer.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Adicionar os painéis à janela principal
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(formContainer, BorderLayout.CENTER);
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

