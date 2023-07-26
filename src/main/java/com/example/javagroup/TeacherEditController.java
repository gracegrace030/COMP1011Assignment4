package com.example.javagroup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class TeacherEditController {

    @FXML
    private ChoiceBox<?> academicSeasonMenu;

    @FXML
    private TextField academicYearField;

    @FXML
    private Button addCourseButton;

    @FXML
    private ChoiceBox<?> courseMenu;

    @FXML
    private Button createProfileButton;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Label teacherNameLabel;

    @FXML
    private Label teacherNameLabel1;

    @FXML
    private Label tidLabel;

    @FXML
    void addCourse(MouseEvent event) {
        // to be implemented
        // changeScene("course-create-view.fxml", event);
    }

    public void returnToTeachers(ActionEvent event) throws IOException {
        changeScene("teacher-view.fxml", event);
    }

    public void changeScene(String fxmlpath, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlpath));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
