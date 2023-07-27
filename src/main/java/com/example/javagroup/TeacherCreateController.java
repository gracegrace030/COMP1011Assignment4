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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;

public class TeacherCreateController implements Initializable {

    @FXML
    private ChoiceBox<String> academicSeasonMenu;

    @FXML
    private TextField academicYearField;

    @FXML
    private Button addCourseButton;

    @FXML
    private ChoiceBox<String> courseMenu;

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

    @FXML
    void createProfile(ActionEvent event) throws IOException {
        int tid = Teacher.count;
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        boolean isDBSuccess = Teacher.createTeacherToDB(new Teacher(tid, firstName, lastName));

        // Go back to view if update successfully
        if (isDBSuccess){
            changeScene("teacher-view.fxml", event);
        }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        // Course Menu
//        for (Course course : Course.getCourseFromDB()){
//            String item = String.format("%s, %s", course.getCid(), course.getCourseName());
//            courseMenu.getItems().add(item);
//        }
//
//        // Season Menu
//        for (Course.seasonEnum season : Course.seasonEnum.values()){
//            academicSeasonMenu.getItems().add(season.name());
//        }

        // Tid
        tidLabel.setText(String.valueOf(Teacher.count));
    }
}
