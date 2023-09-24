package br.edu.unicesumar.ads.prog2.mapa;

import br.edu.unicesumar.ads.prog2.mapa.views.LoginView;
import javax.swing.SwingUtilities;

/**
 *
 * @author max.andriani
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginView loginForm = new LoginView();
                loginForm.setVisible(true);
            }
        });
    }
    
}
