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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;

public class StudentCreateController implements Initializable {

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
    private TextField graduateYearField;

    @FXML
    private TextField programField;

    @FXML
    private ChoiceBox<String> intakeSeasonMenu;

    @FXML
    private TextField intakeYearField;

    @FXML
    private TextField gradeField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Label sidLabel;

    @FXML
    private Label teacherNameLabel;

    @FXML
    private Label teacherNameLabel1;

    @FXML
    private VBox academicInfoBox1;

    @FXML
    private VBox academicInfoBox2;

    @FXML
    private VBox academicInfoBox3;

    @FXML
    private VBox academicInfoBox4;

    @FXML
    void addCourse(ActionEvent event) {
        ChoiceBox<String> newCourseChoiceBox = new ChoiceBox<String>();
        ChoiceBox<String> newAcademicSeasonChoiceBox = new ChoiceBox<String>();
        TextField newAcademicYearLabel = new TextField();
        TextField newGradeLabel = new TextField();

        newCourseChoiceBox.setPrefWidth(202);
        newAcademicSeasonChoiceBox.setPrefWidth(202);
        newAcademicYearLabel.setPrefWidth(202);
        newGradeLabel.setPrefWidth(202);

        academicInfoBox1.getChildren().add(newCourseChoiceBox);
        academicInfoBox2.getChildren().add(newAcademicSeasonChoiceBox);
        academicInfoBox3.getChildren().add(newAcademicYearLabel);
        academicInfoBox4.getChildren().add(newGradeLabel);

        for (Course.seasonEnum season : Course.seasonEnum.values()){
            newAcademicSeasonChoiceBox.getItems().add(season.name());
        }
        // Course Menu
        for (Course course : Course.getCourseFromDB()){
            newCourseChoiceBox.getItems().add(course.getCid());
        }
    }

    @FXML
    void createProfile(ActionEvent event) throws IOException {
        int sid = Student.count;
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String program = programField.getText();
        Year intakeYear = Year.of(Integer.parseInt(intakeSeasonMenu.getValue()));
        String validIntakeSeason = academicSeasonMenu.getValue();
        Year graduateYear = Year.of(Integer.parseInt(graduateYearField.getText()));

        boolean isDBSuccess = Student.createStudentToDB(new Student(sid, firstName, lastName, program, intakeYear, validIntakeSeason, graduateYear));

        // Go back to view if update successfully
        if (isDBSuccess){
            changeScene("student-view.fxml", event);
        }
    }

    @FXML
    void returnToStudents(ActionEvent event) throws IOException {
        changeScene("student-view.fxml", event);
    }

    public void changeScene(String fxmlpath, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlpath));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void reFreshChoiceBoxes(){
        // Intake Season Menu
        // Academic Season Menu
        for (Course.seasonEnum season : Course.seasonEnum.values()){
            academicSeasonMenu.getItems().add(season.name());
            intakeSeasonMenu.getItems().add(season.name());
        }
        // Course Menu
        for (Course course : Course.getCourseFromDB()){
            courseMenu.getItems().add(course.getCid());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Tid
        sidLabel.setText(String.valueOf(Student.count));
        reFreshChoiceBoxes();
    }
}
