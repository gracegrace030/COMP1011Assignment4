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
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;

public class CourseViewController implements Initializable {

    @FXML
    private Label academicSeasonLabel;

    @FXML
    private Label academicYearLabel;

    @FXML
    private Button addCourseButton;

    @FXML
    private Label cidLabel;

    @FXML
    private ListView<Course> courseListPane;

    @FXML
    private Label courseNameLabel;

    @FXML
    private Button menuButton;

    @FXML
    private Label teacherNameLabel;

    @FXML
    private Label tidLabel;

    @FXML
    void returnToMenu(ActionEvent event) throws IOException {
        changeScene("main-view.fxml", event);
    }

    @FXML
    void selectCourse(MouseEvent event) {
        Course course = courseListPane.getSelectionModel().getSelectedItem();

        // changing labels
        cidLabel.setText(course.getCid());
        courseNameLabel.setText(course.getCourseName());
        tidLabel.setText(Integer.toString(course.getTid()));
        teacherNameLabel.setText((course.gettFirstName()+ " " + course.gettLastName()));
        academicSeasonLabel.setText(String.valueOf(course.getSeason()));
        academicYearLabel.setText(String.valueOf(course.getAcademicYear()));
    }

    @FXML
    void createCourse(ActionEvent event) throws IOException {
        changeScene("course-edit-view.fxml", event);
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
        // Dummy Data
//        Course[] courses = new Course[2];
//
//        courses[0] = new Course("COMP1011", "OOP", 2,"Summer", Year.of(2000));
//        courses[1] = new Course("COMP1000", "AAA", 1,"Winter", Year.of(2002));

        // Display all the courses in DB
        Course.resetId();
        for (Course course: Course.getCourseFromDB()) {
            courseListPane.getItems().add(course);
        }
    }
}