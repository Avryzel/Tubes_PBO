import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class Note extends Application {
    private ObservableList<String> notesList = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        ListView<String> notesListView = new ListView<>(notesList);
        TextArea noteField = new TextArea();
        noteField.setPromptText("Write your note here...");

        // Add Note Buttons
        Button addNoteButton = new Button("Add Note");
        addNoteButton.setOnAction(e -> {
            String note = noteField.getText();
            if (!note.isEmpty()) {
                notesList.add(note);
                noteField.clear();
            }
        });

        // Delete Note Button
        Button deleteNoteButton = new Button("Delete Note");
        deleteNoteButton.setOnAction(e -> {
            String selectedNote = notesListView.getSelectionModel().getSelectedItem();
            if (selectedNote != null) {
                notesList.remove(selectedNote);
            }
        });

        // Edit Note Button
        Button editNoteButton = new Button("Edit Note");
        editNoteButton.setOnAction(e -> {
            String selectedNote = notesListView.getSelectionModel().getSelectedItem();
            int selectedIndex = notesListView.getSelectionModel().getSelectedIndex();
            if (selectedNote != null && selectedIndex >= 0) {
                noteField.setText(selectedNote); // Populate the TextArea with the selected note
                notesList.remove(selectedIndex); // Remove the selected note from the list
            }
        });

        // Back Button
        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> {
            MenuApp menuApp = new MenuApp();
            try {
                menuApp.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10, notesListView, noteField, addNoteButton, editNoteButton, deleteNoteButton, backButton);
        layout.setPadding(new Insets(10));
        Scene scene = new Scene(layout, 400, 400);

        primaryStage.setTitle("Notes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
