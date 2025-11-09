package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.awt.Desktop;
import java.net.URI;

import java.io.IOException;

public class CadastroController {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtCEP;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtComplemento;
    @FXML
    private CheckBox chkTermos;
    @FXML
    private Button btnFinalizar;
    @FXML
    private Hyperlink hyperlinkTermos;



    @FXML
    private void initialize() {
        // Efeito de clique no botão
        btnFinalizar.setOnMousePressed(e -> btnFinalizar.setScaleY(0.95));
        btnFinalizar.setOnMouseReleased(e -> btnFinalizar.setScaleY(1.0));
    }

    @FXML
    private void handleFinalizarButtonAction(ActionEvent event) {
        // Validação simples dos campos
        if (txtNome.getText().isEmpty() || txtCPF.getText().isEmpty() || !chkTermos.isSelected()) {
            System.out.println("Preencha todos os campos obrigatórios e aceite os termos.");
            return;
        }


        // Aqui você pode salvar os dados ou enviar para o banco
        System.out.println("Cadastro finalizado!");
        System.out.println("Nome: " + txtNome.getText());
        System.out.println("CPF: " + txtCPF.getText());
        System.out.println("CEP: " + txtCEP.getText());
        System.out.println("Endereço: " + txtEndereco.getText() + ", Nº " + txtNumero.getText());
        System.out.println("Complemento: " + txtComplemento.getText());

    }
    @FXML
    private void abrirTermos() {
        try {
            // URL dos termos de uso
            URI uri = new URI("https://docs.google.com/document/d/1CFS7WEWgC71SoEhPA25WSr86oSqzuHuS/edit");

            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(uri);
            } else {
                System.out.println("Não é possível abrir o navegador neste sistema.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


