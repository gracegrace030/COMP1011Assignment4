package com.example.javagroup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherEditController implements Initializable {

    @FXML
    private Button editProfileButton;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Label teacherNameLabel;

    @FXML
    private Label tidLabel;
    @FXML
    Teacher tempTeacher;

    @FXML
    void editProfile(ActionEvent event) throws IOException {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        tempTeacher.setFirstName(firstName);
        tempTeacher.setLastName(lastName);

        boolean isDBSuccess = Teacher.updateTeacherToDB(tempTeacher);

        // Go back to view if update successfully
        if (isDBSuccess){
            changeScene("teacher-view.fxml", event);
        }
    }

    @FXML
    void returnToTeachers(ActionEvent event) throws IOException {
        changeScene("teacher-view.fxml", event);
    }

    public void changeScene(String fxmlpath, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlpath));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Data from previous window
    public void setData(Teacher data){
        tempTeacher = data;
        tidLabel.setText(String.valueOf(data.getTid()));
        firstNameField.setText(data.getFirstName());
        lastNameField.setText(data.getLastName());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
