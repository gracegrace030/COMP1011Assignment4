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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;

public class CourseEditController implements Initializable {

    @FXML
    private ChoiceBox<String> academicSeasonMenu;

    @FXML
    private TextField academicYearField;

    @FXML
    private Label cidField;

    @FXML
    private Label courseNameLabel;

    @FXML
    private Button createCourseButton;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField courseNameField;

    @FXML
    private ChoiceBox<String> teacherMenu;

    @FXML
    Course tempCourse;

    @FXML
    void editCourse(ActionEvent event) throws IOException {

//        String cid = cidField.getText();
        String courseName = courseNameField.getText();
        String tidText = teacherMenu.getValue().split(",")[0];
        int tid = Integer.parseInt(tidText);
        String season = academicSeasonMenu.getValue();
        Year academicYear = Year.of(Integer.parseInt(academicYearField.getText()));

//        tempCourse.setCid(cid);
        tempCourse.setCourseName(courseName);
        tempCourse.setTid(tid);
        tempCourse.setSeason(season);
        tempCourse.setAcademicYear(academicYear);


        boolean isDBSuccess = Course.updateCourseToDB(tempCourse);

        // Go back to view if update successfully
        if (isDBSuccess){
            changeScene("course-view.fxml", event);
        }
    }
    @FXML
    void returnToCourse(ActionEvent event) throws IOException {
        changeScene("course-view.fxml", event);
    }

    public void changeScene(String fxmlpath, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlpath));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Data from previous window
    public void setData(Course data){
        tempCourse = data;
        cidField.setText(data.getCid());
        courseNameField.setText(data.getCourseName());
        String item = String.format("%s, %s %s", data.getTid(), data.gettFirstName(), data.gettLastName());
        teacherMenu.setValue(item);
        academicSeasonMenu.setValue(data.getSeason().name());
        academicYearField.setText(String.valueOf(data.getAcademicYear()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Teacher Menu
        for (Teacher teacher : Teacher.getTeacherFromDB()){
            String item = String.format("%s, %s %s", teacher.getTid(), teacher.getFirstName(), teacher.getLastName());
            teacherMenu.getItems().add(item);
        }

        // Season Menu
        for (Course.seasonEnum season : Course.seasonEnum.values()){
            academicSeasonMenu.getItems().add(season.name());
        }
    }
}
