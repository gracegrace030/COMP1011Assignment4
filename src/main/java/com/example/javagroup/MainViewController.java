package com.example.javagroup;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {

    @FXML
    private Button exitButton;

    @FXML
    private Button studentButton;

    @FXML
    private Button teacherButton;

    @FXML
    void exitApp(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void openStudent(ActionEvent event) throws IOException {
        changeScene("teacher-edit-view.fxml", event);
    }

    @FXML
    void openTeacher(ActionEvent event) throws IOException {
        changeScene("teacher-view.fxml", event);
    }

    /**
     * Function used to change scenes on button press
     * @param fxmlpath - path to the fxml file that the scene will use
     * @param event - ActionEvent of the button being pressed
     * @throws IOException
     */
    public void changeScene(String fxmlpath, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlpath));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
