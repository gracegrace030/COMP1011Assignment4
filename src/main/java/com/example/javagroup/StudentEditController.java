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
import java.util.List;
import java.util.ResourceBundle;

public class StudentEditController implements Initializable {

    @FXML
    private VBox academicInfoBox1;

    @FXML
    private VBox academicInfoBox2;

    @FXML
    private VBox academicInfoBox3;

    @FXML
    private VBox academicInfoBox4;

    @FXML
    private ChoiceBox<String> academicSeasonMenu;

    @FXML
    private TextField academicYearField;

    @FXML
    private ChoiceBox<String> courseMenu;

    @FXML
    private Button editProfileButton;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField gradeField;

    @FXML
    private TextField graduateYearField;
    @FXML
    private TextField programField;

    @FXML
    private ChoiceBox<String> intakeSeasonMenu;

    @FXML
    private TextField intakeYearField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Label sidLabel;

    @FXML
    private Label teacherNameLabel;

    @FXML
    private Label teacherNameLabel1;
    @FXML
    Student tempStudent;

    @FXML
    void editProfile(ActionEvent event) throws IOException {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String program = programField.getText();
        Year intakeYear = Year.of(Integer.parseInt(intakeYearField.getText()));
        String validIntakeSeason = intakeSeasonMenu.getValue();
        Year graduateYear = Year.of(Integer.parseInt(graduateYearField.getText()));
        float grade = Float.parseFloat(gradeField.getText());
        String cid = courseMenu.getValue();

        tempStudent.setFirstName(firstName);
        tempStudent.setLastName(lastName);
        tempStudent.setIntakeYear(intakeYear);
        tempStudent.setIntakeSeason(validIntakeSeason);
        tempStudent.setGraduateYear(graduateYear);

        boolean isDBSuccess = Student.updateStudentToDB(tempStudent, grade, cid);

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

    // Data from previous window
    public void setData(Student data){
        tempStudent = data;

        sidLabel.setText(String.valueOf(data.getSid()));
        firstNameField.setText(data.getFirstName());
        lastNameField.setText(data.getLastName());
        programField.setText(data.getProgram());
        intakeYearField.setText(String.valueOf(data.getIntakeYear()));
        intakeSeasonMenu.setValue(String.valueOf(data.getIntakeSeason()));
        graduateYearField.setText(String.valueOf(data.getGraduateYear()));

        List<List<Object>> courseInfo = Student.getStudentCoursesFromDB(data.getSid());
        for (List course : courseInfo){
            courseMenu.setValue(String.valueOf(course.get(0)));
            academicSeasonMenu.setValue(String.valueOf(course.get(1)));
            academicYearField.setText(String.valueOf(course.get(2)));
            gradeField.setText(String.valueOf(course.get(3)));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
}
