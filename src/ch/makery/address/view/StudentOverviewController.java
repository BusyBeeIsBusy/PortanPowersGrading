package ch.makery.address.view;

import ch.makery.address.Main;
import ch.makery.address.model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class StudentOverviewController {
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label teacherNameLabel;
    @FXML
    private Label classNameLabel;
    @FXML
    private Label assignmentName1Label;
    @FXML
    private Label assignmentName2Label;
    @FXML
    private Label assignmentName3Label;
    @FXML
    private Label assignmentGrade1Label;
    @FXML
    private Label assignmentGrade2Label;
    @FXML
    private Label assignmentGrade3Label;
    @FXML
    private Label assignment1FeedbackLabel;
    @FXML
    private Label assignment2FeedbackLabel;
    @FXML
    private Label assignment3FeedbackLabel;

    // Reference to the main application.
    private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public StudentOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());

        // Clear student details.
        showStudentDetails(null);

        // Listen for selection changes and show the person details when changed.
        studentTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showStudentDetails(newValue));
    }


   // /**
   //  * Is called by the main application to give a reference back to itself.
   //  *
   //  * @param main
   //  *
   //*/
    public void setMain(Main main) {
        this.main = main;

        // Add observable list data to the table
        studentTable.setItems(main.getStudentData());
    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param student the person or null
     */
    private void showStudentDetails(Student student) {
        if (student != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(student.getFirstName());
            lastNameLabel.setText(student.getLastName());

            teacherNameLabel.setText(student.getTeacherName());
            classNameLabel.setText(student.getclassName());

            assignmentName1Label.setText(student.getAssignmentName1());
            assignmentName2Label.setText(student.getAssignmentName2());
            assignmentName3Label.setText(student.getAssignmentName3());

            assignmentGrade1Label.setText(Integer.toString(student.getassignmentGrade1()));
            assignmentGrade2Label.setText(Integer.toString(student.getassignmentGrade2()));
            assignmentGrade3Label.setText(Integer.toString(student.getassignmentGrade3()));

            assignment1FeedbackLabel.setText(student.getassignment1Feedback());
            assignment2FeedbackLabel.setText(student.getassignment2Feedback());
            assignment3FeedbackLabel.setText(student.getassignment3Feedback());

        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            teacherNameLabel.setText("");
            assignmentName1Label.setText("");
            assignmentName2Label.setText("");
            assignmentName3Label.setText("");

            assignmentGrade1Label.setText("");
            assignmentGrade2Label.setText("");
            assignmentGrade3Label.setText("");

            assignment1FeedbackLabel.setText("");
            assignment2FeedbackLabel.setText("");
            assignment3FeedbackLabel.setText("");
        }
    }
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteStudent() {
        int selectedIndex = studentTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            studentTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Student Selected");
            alert.setContentText("Please select a student in the table.");

            alert.showAndWait();
        }
}
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewStudent() {
        Student tempStudent = new Student();
        boolean okClicked = main.showStudentEditDialog(tempStudent);
        if (okClicked) {
            main.getStudentData().add(tempStudent);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            boolean okClicked = main.showStudentEditDialog(selectedStudent);
            if (okClicked) {
                showStudentDetails(selectedStudent);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Student Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

}
