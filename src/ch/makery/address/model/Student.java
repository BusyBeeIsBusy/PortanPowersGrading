package ch.makery.address.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty teacherName;
    private final StringProperty className;

    private final StringProperty assignmentName1;
    private final StringProperty assignmentName2;
    private final StringProperty assignmentName3;

    private final IntegerProperty assignmentGrade1;
    private final IntegerProperty assignmentGrade2;
    private final IntegerProperty assignmentGrade3;


    private final SimpleStringProperty assignment1Feedback;
    private final SimpleStringProperty assignment2Feedback;
    private final SimpleStringProperty assignment3Feedback;

    /**
     * Default constructor.
     */
    public Student() {
        this(null, null);
    }

    /**
    // * Constructor with some initial data.
    // *
    // * @param firstName
    // * @param lastName
    // */
    public Student(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        // Some initial dummy data, just for convenient testing.
        this.teacherName = new SimpleStringProperty("Teacher Name");
        this.className = new SimpleStringProperty("CS 1");

        this.assignmentName1 = new SimpleStringProperty("Assignment 1");
        this.assignmentName2 = new SimpleStringProperty("Assignment 2");
        this.assignmentName3 = new SimpleStringProperty("Assignment 3");

        this.assignmentGrade1 = new SimpleIntegerProperty(2);
        this.assignmentGrade2 = new SimpleIntegerProperty(1);
        this.assignmentGrade3 = new SimpleIntegerProperty(3);

        this.assignment1Feedback = new SimpleStringProperty(":/");
        this.assignment2Feedback = new SimpleStringProperty(":(");
        this.assignment3Feedback = new SimpleStringProperty(":)");

    }
//First Name, Last Name, Teacher Name, Class, Assignment, Grade, Feedback
    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getTeacherName() {
        return teacherName.get();
    }

    public void setTeacherName(String teacherName) {
        this.teacherName.set(teacherName);
    }

    public StringProperty teacherNameProperty() {
        return teacherName;
    }

    public String getAssignmentName1() {
        return assignmentName1.get();
    }

    public void setAssignmentName1(String assignmentName1) {
        this.assignmentName1.set(assignmentName1);
    }

    public StringProperty AssignmentName1Property() {
        return assignmentName1;
    }

    public String getAssignmentName2() {
        return assignmentName2.get();
    }

    public void setAssignmentName2(String assignmentName2) {
        this.assignmentName2.set(assignmentName2);
    }

    public StringProperty AssignmentName2Property() {
        return assignmentName2;
    }

    public String getAssignmentName3() {
        return assignmentName3.get();
    }

    public void setAssignmentName3(String assignmentName3) {
        this.assignmentName3.set(assignmentName3);
    }

    public StringProperty AssignmentName3Property() {
        return assignmentName3;
    }

    public int getassignmentGrade1() {
        return assignmentGrade1.get();
    }

    public void setassignmentGrade1(int assignmentGrade1) {
        this.assignmentGrade1.set(assignmentGrade1);
    }

    public IntegerProperty assignmentGrade1Property() {
        return assignmentGrade1;
    }

    public int getassignmentGrade2() {
        return assignmentGrade2.get();
    }

    public void setassignmentGrade2(int assignmentGrade2) {
        this.assignmentGrade2.set(assignmentGrade2);
    }

    public IntegerProperty assignmentGrade2Property() {
        return assignmentGrade2;
    }

    public int getassignmentGrade3() {
        return assignmentGrade3.get();
    }

    public void setassignmentGrade3(int assignmentGrade3) {
        this.assignmentGrade3.set(assignmentGrade3);
    }

    public IntegerProperty assignmentGrade3Property() {
        return assignmentGrade3;
    }

    public String getclassName() {
        return className.get();
    }

    public void setClassName(String className) {
        this.className.set(className);
    }

    public String getassignment1Feedback() {
        return assignment1Feedback.get();
    }

    public void setAssignment1Feedback(String assignment1Feedback) {
        this.assignment1Feedback.set(assignment1Feedback);
    }

    public StringProperty assignment1FeedbackProperty() {
        return assignment1Feedback;
    }

    public String getassignment2Feedback() {
        return assignment1Feedback.get();
    }

    public void setAssignment2Feedback(String assignment2Feedback) {
        this.assignment2Feedback.set(assignment2Feedback);
    }

    public StringProperty assignment2FeedbackProperty() {
        return assignment2Feedback;

    }

    public String getassignment3Feedback() {
        return assignment3Feedback.get();
    }

    public void setAssignment3Feedback(String assignment3Feedback) {
        this.assignment3Feedback.set(assignment3Feedback);
    }

    public StringProperty assignment3FeedbackProperty() {
        return assignment3Feedback;
    }


}

