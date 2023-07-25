package com.example.javagroup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherViewController implements Initializable {

    @FXML
    private Label academicSeasonLabel;

    @FXML
    private Label academicYearLabel;

    @FXML
    private Button addTeacherButton;

    @FXML
    private ChoiceBox<?> courseMenu;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Button menuButton;

    @FXML
    private ListView<Teacher> teacherListPane;

    @FXML
    private Label teacherNameLabel;

    @FXML
    private Label teacherNameLabel1;

    @FXML
    private Label tidLabel;

    @FXML
    void returnToMenu(ActionEvent event) throws IOException {
        changeScene("main-view.fxml", event);
    }

    @FXML
    void selectTeacher(MouseEvent event) throws IOException {
        Teacher teacher = teacherListPane.getSelectionModel().getSelectedItem();

        // changing labels
        tidLabel.setText(Integer.toString(teacher.getTid()));
        firstNameLabel.setText(teacher.getFirstName());
        lastNameLabel.setText(teacher.getLastName());
    }

    public void createTeacher(ActionEvent event) throws IOException {
        changeScene("teacher-edit-view.fxml", event);
    }
    public void changeScene(String fxmlpath, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlpath));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Teacher[] teachers = new Teacher[5];

        // Initialize 5 dummy teacher data
        teachers[0] = new Teacher(1, "John", "Doe");
        teachers[1] = new Teacher(2, "Jane", "Smith");
        teachers[2] = new Teacher(3, "Michael", "Johnson");
        teachers[3] = new Teacher(4, "Emily", "Brown");
        teachers[4] = new Teacher(5, "David", "Williams");

        for (Teacher teacher: teachers) {
            teacherListPane.getItems().add(teacher);
        }
    }
}
