package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button btnLogin;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnEmail;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtSenha;


    @FXML
    public void initialize() {
        // Estilos dos botões
        String normalStyle = "-fx-background-color: #272727; -fx-background-radius: 30; -fx-text-fill: white; -fx-cursor: hand;";
        String emailStyle = "-fx-background-color: #eae9e8; -fx-background-radius: 30; -fx-text-fill: black; -fx-cursor: hand;";

        btnLogin.setStyle(normalStyle);
        btnCadastrar.setStyle(normalStyle);
        btnEmail.setStyle(emailStyle);

        applyButtonClickEffect(btnLogin);
        applyButtonClickEffect(btnCadastrar);
        applyButtonClickEffect(btnEmail);
    }

    private void applyButtonClickEffect(Button button) {
        button.setOnMousePressed(e -> button.setScaleY(0.95));
        button.setOnMouseReleased(e -> button.setScaleY(1.0));
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        System.out.println("LOG: Botão Login Clicado!");
        System.out.println("Email: " + txtEmail.getText());
    }

    @FXML
    private void handleEmailButtonAction(ActionEvent event) {
        System.out.println("LOG: Botão Logar Com E-mail Clicado!");
    }

    @FXML
    private void handleCadastroButtonAction(ActionEvent event) {
        try {
            // Carrega o FXML da tela de cadastro
            Parent cadastroRoot = FXMLLoader.load(getClass().getResource("Cadastro.fxml"));

            // Pega a Stage atual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Define a nova cena
            Scene scene = new Scene(cadastroRoot);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

