package ch.makery.address;

import ch.makery.model.Student;
import ch.makery.model.StudentListWrapper;
import ch.makery.view.RootLayoutController;
import ch.makery.view.StudentEditDialogController;
import ch.makery.view.StudentOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showStudentOverview();
    }


    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMain(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try to load last opened person file.
        File file = getStudentFilePath();
        if (file != null) {
            loadStudentDataFromFile(file);
        }
    }


    public void showStudentOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/StudentOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            StudentOverviewController controller = loader.getController();
            controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   // /**
   //  * Returns the main stage.
   //  *
   //  * @return
   //  */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }


    private ObservableList<Student> studentData = FXCollections.observableArrayList();


     //Constructor

    public Main() {
        // Add some sample data
        studentData.add(new Student("Hans", "Muster"));
        studentData.add(new Student("Ruth", "Mueller"));
        studentData.add(new Student("Heinz", "Kurz"));
        studentData.add(new Student("Cornelia", "Meier"));
        studentData.add(new Student("Werner", "Meyer"));
        studentData.add(new Student("Lydia", "Kunz"));
        studentData.add(new Student("Anna", "Best"));
        studentData.add(new Student("Stefan", "Meier"));
        studentData.add(new Student("Martin", "Mueller"));
    }

    ///**
    // * Returns the data as an observable list of Persons.
    // *
    // * @return
    // */
    public ObservableList<Student> getStudentData() {
        return studentData;
    }


    ///**
    // * Opens a dialog to edit details for the specified student. If the user
    // * clicks OK, the changes are saved into the provided student object and true
    // * is returned.
    // *
    // * @param student the student object to be edited
    // * @return true if the user clicked OK, false otherwise.
    // */
    public boolean showStudentEditDialog(Student student) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/StudentEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Student");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the student into the controller.
            StudentEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStudent(student);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    ///**
    // * Returns the person file preference, i.e. the file that was last opened.
    // * The preference is read from the OS specific registry. If no such
    // * preference can be found, null is returned.
    // *
    // * @return
    // */
    public File getStudentFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    ///**
    // * Sets the file path of the currently loaded file. The path is persisted in
    // * the OS specific registry.
    // *
    // * @param file the file or null to remove the path
    // */
    public void setStudentFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("AddressApp");
        }
    }
    ///**
    // * Loads person data from the specified file. The current person data will
    // * be replaced.
    // *
    // * @param file
   //  */
    public void loadStudentDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(StudentListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            StudentListWrapper wrapper = (StudentListWrapper) um.unmarshal(file);

            studentData.clear();
            studentData.addAll(wrapper.getStudents());

            // Save the file path to the registry.
            setStudentFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    ///**
    // * Saves the current person data to the specified file.
    // *
    // * @param file
    // */
    public void saveStudentDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(StudentListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            StudentListWrapper wrapper = new StudentListWrapper();
            wrapper.setStudents(studentData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setStudentFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

}
       // Parent root = FXMLLoader.load(getClass().getResource("StudentOverview.fxml"));
               // primaryStage.setTitle("Hello World");
               // primaryStage.setScene(new Scene(root, 300, 275));
               // primaryStage.show();