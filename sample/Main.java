package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carrega o FXML do mesmo pacote
        Parent root = FXMLLoader.load(getClass().getResource("TelaDeLogin.fxml"));

        primaryStage.setTitle("ConnectNutri");

        // Scene sem tamanhos fixos
        primaryStage.setScene(new Scene(root));

        // Tamanhos mínimos
        primaryStage.setMinHeight(550.0);
        primaryStage.setMinWidth(650.0);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
