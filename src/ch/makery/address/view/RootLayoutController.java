package ch.makery.address.view;

import ch.makery.address.Main;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

public class RootLayoutController {
    // Reference to the main application
    private Main main;

    /**
    // * @param main
    // */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNew() {
        main.getStudentData().clear();
        main.setStudentFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());

        if (file != null) {
            main.loadStudentDataFromFile(file);
        }
    }

    /**
     * Saves the file to the person file that is open
     */
    @FXML
    private void handleSave() {
        File studentFile = main.getStudentFilePath();
        if (studentFile != null) {
            main.saveStudentDataToFile(studentFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * Opens user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(main.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            main.saveStudentDataToFile(file);
        }
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}

