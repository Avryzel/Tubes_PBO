import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class IdApp extends Application {
    private ObservableList<String> idList = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        ListView<String> idListView = new ListView<>(idList);
        TextField idField = new TextField();
        idField.setPromptText("Enter ID");
        TextField passwordField = new TextField(); 
        passwordField.setPromptText("Enter Password");

        // Button to add ID
        Button addIdButton = new Button("Add ID");
        addIdButton.setOnAction(e -> {
            String id = idField.getText();
            String password = passwordField.getText();
            if (!id.isEmpty() && !password.isEmpty()) {
                idList.add(id + " (Password: " + password + ")");
                idField.clear();
                passwordField.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Input Warning");
                alert.setHeaderText(null);
                alert.setContentText("ID and Password cannot be empty!");
                alert.showAndWait();
            }
        });

        // Button to edit selected ID
Button editIdButton = new Button("Edit Selected ID");
editIdButton.setOnAction(e -> {
    String selectedId = idListView.getSelectionModel().getSelectedItem();
    int selectedIndex = idListView.getSelectionModel().getSelectedIndex();
    if (selectedId != null && selectedIndex >= 0) {
        String newId = idField.getText();
        String newPassword = passwordField.getText();
        if (!newId.isEmpty() && !newPassword.isEmpty()) {
            idList.set(selectedIndex, newId + " (Password: " + newPassword + ")");
            idField.clear();
            passwordField.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Warning");
            alert.setHeaderText(null);
            alert.setContentText("ID and Password cannot be empty!");
            alert.showAndWait();
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText(null);
        alert.setContentText("Please select an ID to edit!");
        alert.showAndWait();
    }
});

       

        // Button to delete selected ID
        Button deleteIdButton = new Button("Delete Selected ID");
        deleteIdButton.setOnAction(e -> {
            String selectedId = idListView.getSelectionModel().getSelectedItem();
            if (selectedId != null) {
                idList.remove(selectedId);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Selection");
                alert.setHeaderText(null);
                alert.setContentText("Please select an ID to delete!");
                alert.showAndWait();
            }
        });

        // Back button to return to the menu
        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> {
            MenuApp menuApp = new MenuApp();
            try {
                menuApp.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10, idListView, idField, passwordField, addIdButton, editIdButton, deleteIdButton, backButton);
        layout.setPadding(new Insets(10));
        Scene scene = new Scene(layout, 600, 400);

        primaryStage.setTitle("ID Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
