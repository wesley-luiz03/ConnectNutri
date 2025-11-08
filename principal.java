package br.unifafire.connectnutri.main;

import br.unifafire.connectnutri.tela.TelaLogin;
import javax.swing.SwingUtilities;

public class principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin();
            }
        });
    }
}