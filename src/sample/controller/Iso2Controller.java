package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Iso2Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Iso2CloseButton;

    @FXML
    private Button switchIso2;

    @FXML
    void initialize() {
        switchIso2.setOnAction(event -> {
            openScene("/sample/fxml/iso1.fxml");
        });
    }

    private void openScene (String scene) {
        switchIso2.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(scene));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:resources/images/icon.png"));
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}

