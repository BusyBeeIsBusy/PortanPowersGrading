package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import ch.makery.address.model.Student;


public class StudentEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField teacherNameField;
    @FXML
    private TextField classNameField;
    @FXML
    private TextField assignmentName1Field;
    @FXML
    private TextField assignmentName2Field;
    @FXML
    private TextField assignmentName3Field;
    @FXML
    private TextField assignmentGrade1Field;
    @FXML
    private TextField assignmentGrade2Field;
    @FXML
    private TextField assignmentGrade3Field;
    @FXML
    private TextField assignment1FeedbackField;
    @FXML
    private TextField assignment2FeedbackField;
    @FXML
    private TextField assignment3FeedbackField;


    private Stage dialogStage;
    private Student student;
    private boolean okClicked = false;


    @FXML
    private void initialize() {
    }

    /**
    // * Sets the stage of this dialog.
    // *
    // * @param dialogStage
    // */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
    // * Sets the person to be edited in the dialog.
    // *
    // * @param student
    // */
    public void setStudent(Student student) {
        this.student = student;

        firstNameField.setText(student.getFirstName());
        lastNameField.setText(student.getLastName());

        teacherNameField.setText(student.getTeacherName());
        classNameField.setText(student.getclassName());

        assignmentName1Field.setText(student.getAssignmentName1());
        assignmentName2Field.setText(student.getAssignmentName2());
        assignmentName3Field.setText(student.getAssignmentName3());

        assignmentGrade1Field.setText(Integer.toString(student.getassignmentGrade1()));
        assignmentGrade2Field.setText(Integer.toString(student.getassignmentGrade2()));
        assignmentGrade3Field.setText(Integer.toString(student.getassignmentGrade3()));

        assignment1FeedbackField.setText(student.getassignment1Feedback());
        assignment2FeedbackField.setText(student.getassignment2Feedback());
        assignment3FeedbackField.setText(student.getassignment3Feedback());

    }

    /**
    // * Returns true if the user clicked OK, false otherwise.
    // *
    // * @return
    // */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            student.setFirstName(firstNameField.getText());
            student.setLastName(lastNameField.getText());

            student.setTeacherName(teacherNameField.getText());
            student.setClassName(classNameField.getText());

            student.setAssignmentName1(assignmentName1Field.getText());
            student.setAssignmentName2(assignmentName2Field.getText());
            student.setAssignmentName3(assignmentName3Field.getText());

            student.setassignmentGrade1(Integer.parseInt(assignmentGrade1Field.getText()));
            student.setassignmentGrade2(Integer.parseInt(assignmentGrade1Field.getText()));
            student.setassignmentGrade3(Integer.parseInt(assignmentGrade1Field.getText()));

            student.setAssignment1Feedback(assignment1FeedbackField.getText());
            student.setAssignment1Feedback(assignment1FeedbackField.getText());
            student.setAssignment1Feedback(assignment1FeedbackField.getText());


            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (teacherNameField.getText() == null || teacherNameField.getText().length() == 0) {
            errorMessage += "No valid teacher name!\n";
        }

        if (assignmentName1Field.getText() == null || assignmentName1Field.getText().length() == 0) {
            errorMessage += "No valid assignment name!\n";
        }

        if (assignmentName2Field.getText() == null || assignmentName2Field.getText().length() == 0) {
            errorMessage += "No valid assignment name!\n";
        }

        if (assignmentName3Field.getText() == null || assignmentName3Field.getText().length() == 0) {
            errorMessage += "No valid assignment name!\n";
        }

        if (assignmentGrade1Field.getText() == null || assignmentGrade1Field.getText().length() == 0) {
            errorMessage += "No valid grade for assignment!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(assignmentGrade1Field.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No grade (must be an integer)!\n";
            }
        }

        if (assignmentGrade2Field.getText() == null || assignmentGrade2Field.getText().length() == 0) {
            errorMessage += "No valid grade for this assignment!\n";
            } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(assignmentGrade2Field.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No grade (must be an integer)!\n";
            }
        }

        if (assignmentGrade3Field.getText() == null || assignmentGrade3Field.getText().length() == 0) {
            errorMessage += "No valid grade!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(assignmentGrade3Field.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid grade (must be an integer)!\n";
            }
        }

        if (assignment1FeedbackField.getText() == null || assignment1FeedbackField.getText().length() == 0) {
            errorMessage += "No valid feedback!\n";
        }

        if (assignment2FeedbackField.getText() == null || assignment2FeedbackField.getText().length() == 0) {
            errorMessage += "No valid feedback!\n";
        }

        if (assignment3FeedbackField.getText() == null || assignment3FeedbackField.getText().length() == 0) {
            errorMessage += "No valid feedback!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}


