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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StudentViewController implements Initializable {

    @FXML
    private VBox academicInfoBox1;

    @FXML
    private VBox academicInfoBox2;

    @FXML
    private VBox academicInfoBox3;

    @FXML
    private VBox academicInfoBox4;

    @FXML
    private Label academicSeasonLabel;

    @FXML
    private Label academicYearLabel;

    @FXML
    private Button addStudentButton;

    @FXML
    private Label courseLabel;

    @FXML
    private Button editStudentButton;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label graduateYearLabel;

    @FXML
    private Label intakeSeasonLabel;

    @FXML
    private Label intakeYearLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Button menuButton;

    @FXML
    private Label programLabel;

    @FXML
    private Label sidLabel;

    @FXML
    private ListView<Student> studentListPane;

    @FXML
    private Label teacherNameLabel;

    @FXML
    private Label teacherNameLabel1;

    @FXML
    private Label gradeLabel;


    @FXML
    void returnToMenu(ActionEvent event) throws IOException {
        changeScene("main-view.fxml", event);
    }

    @FXML
    void selectStudent(MouseEvent event) throws IOException {
        Student student = studentListPane.getSelectionModel().getSelectedItem();

        // changing labels
        sidLabel.setText(Integer.toString(student.getSid()));
        firstNameLabel.setText(student.getFirstName());
        lastNameLabel.setText(student.getLastName());
        programLabel.setText(student.getProgram());
        intakeYearLabel.setText(String.valueOf(student.getIntakeYear()));
        intakeSeasonLabel.setText(String.valueOf(student.getIntakeSeason()));
        graduateYearLabel.setText(String.valueOf(student.getGraduateYear()));

        // Clear the courses
        academicInfoBox1.getChildren().clear();
        academicInfoBox2.getChildren().clear();
        academicInfoBox3.getChildren().clear();
        academicInfoBox4.getChildren().clear();

        // Render the courses
        Label title1 = new Label("Course");
        Label title2 = new Label("Academic Season");
        Label title3 = new Label("Academic Year");
        Label title4 = new Label("Grade");
        academicInfoBox1.getChildren().add(title1);
        academicInfoBox2.getChildren().add(title2);
        academicInfoBox3.getChildren().add(title3);
        academicInfoBox4.getChildren().add(title4);

        // Get Course Information
        List<List<Object>> courseInfo = Student.getStudentCoursesFromDB(student.getSid());
        for (List course : courseInfo){
            Label label1 = new Label(String.valueOf(course.get(0)));
            Label label2 = new Label(String.valueOf(course.get(1)));
            Label label3 = new Label(String.valueOf(course.get(2)));
            Label label4 = new Label(String.valueOf(course.get(3)));

            academicInfoBox1.getChildren().add(label1);
            academicInfoBox2.getChildren().add(label2);
            academicInfoBox3.getChildren().add(label3);
            academicInfoBox4.getChildren().add(label4);
        }

    }

    @FXML
    void createStudent(ActionEvent event) throws IOException {
        changeScene("student-create-view.fxml", event);
    }

    @FXML
    void editStudent(ActionEvent event) throws IOException {
        Student student = studentListPane.getSelectionModel().getSelectedItem();
        changeSceneWithData("student-edit-view.fxml", event, student);
    }

    public void changeScene(String fxmlpath, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlpath));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeSceneWithData(String fxmlpath, ActionEvent event, Student data) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlpath));
        Parent root = loader.load();

        // Get the controller of the new scene
        StudentEditController controller = loader.getController();
        // Set the data in the controller
        controller.setData(data);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Student.resetId();
        for (Student student: Student.getStudentFromDB()) {
            studentListPane.getItems().add(student);
        }
    }
}
