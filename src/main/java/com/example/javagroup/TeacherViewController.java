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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class TeacherViewController implements Initializable {

    @FXML
    private Label academicSeasonLabel;

    @FXML
    private Label academicYearLabel;

    @FXML
    private Button addTeacherButton;

    @FXML
    private Label courseLabel;

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
    private VBox academicInfoBox1;

    @FXML
    private VBox academicInfoBox2;

    @FXML
    private VBox academicInfoBox3;
    @FXML
    private Button editTeacherButton;

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

        // Clear the courses
        academicInfoBox1.getChildren().clear();
        academicInfoBox2.getChildren().clear();
        academicInfoBox3.getChildren().clear();
        // Render the courses
        Label title1 = new Label("Course");
        Label title2 = new Label("Academic Season");
        Label title3 = new Label("Academic Year");
        academicInfoBox1.getChildren().add(title1);
        academicInfoBox2.getChildren().add(title2);
        academicInfoBox3.getChildren().add(title3);

        // Get Course Information
        List<List<Object>> courseInfo = Teacher.getTeacherCoursesFromDB(teacher.getTid());
        for (List course : courseInfo){
            Label label1 = new Label(String.valueOf(course.get(0)));
            Label label2 = new Label(String.valueOf(course.get(1)));
            Label label3 = new Label(String.valueOf(course.get(2)));

            academicInfoBox1.getChildren().add(label1);
            academicInfoBox2.getChildren().add(label2);
            academicInfoBox3.getChildren().add(label3);
        }
    }

    public void createTeacher(ActionEvent event) throws IOException {
        changeScene("teacher-create-view.fxml", event);
    }

    @FXML
    void editTeacher(ActionEvent event) throws IOException {
        Teacher teacher = teacherListPane.getSelectionModel().getSelectedItem();
        changeSceneWithData("teacher-edit-view.fxml", event, teacher);
    }

    public void changeScene(String fxmlpath, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlpath));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeSceneWithData(String fxmlpath, ActionEvent event, Teacher data) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlpath));
        Parent root = loader.load();

        // Get the controller of the new scene
        TeacherEditController controller = loader.getController();
        // Set the data in the controller
        controller.setData(data);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initialize 5 dummy teacher data
//        Teacher[] teachers = new Teacher[5];
//        teachers[0] = new Teacher(1, "John", "Doe");
//        teachers[1] = new Teacher(2, "Jane", "Smith");

        // Display all the teachers in DB
        Teacher.resetId();
        for (Teacher teacher: Teacher.getTeacherFromDB()) {
            teacherListPane.getItems().add(teacher);
        }
    }
}
