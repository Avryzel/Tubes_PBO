import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuApp extends Application {
    private static Scene menuScene;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Membuat tombol dengan gaya
        Button contactButton = createStyledButton("Contact App", "#4CAF50");
        Button idButton = createStyledButton("ID App", "#2196F3");
        Button noteButton = createStyledButton("Note App", "#FF9800");
        Button exitButton = createStyledButton("Exit", "#F44336");

        // Menambahkan event handler untuk tombol
        contactButton.setOnAction(e -> openContactApp());
        idButton.setOnAction(e -> openIdApp());
        noteButton.setOnAction(e -> openNoteApp());
        exitButton.setOnAction(e -> primaryStage.close());

        // Layout menu
        VBox menuLayout = new VBox(15, contactButton, idButton, noteButton, exitButton);
        menuLayout.setPadding(new Insets(30));
        menuLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f0f0, #e0e0e0); " +
                            "-fx-background-radius: 10;");
        menuLayout.setAlignment(Pos.CENTER);

        menuScene = new Scene(menuLayout, 600, 400);

        // Atur dan tampilkan stage
        primaryStage.setTitle("Menu");
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    // Metode untuk membuat tombol bergaya
    private Button createStyledButton(String text, String color) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + color + "; " +
                        "-fx-text-fill: white; " +
                        "-fx-background-radius: 5; " +
                        "-fx-padding: 10px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-min-width: 200px;");
        return button;
    }

    // Metode untuk membuka ContactApp
    private void openContactApp() {
        try {
            ContactApp contactApp = new ContactApp();
            contactApp.start(primaryStage);
        } catch (Exception ex) {
            showAlert("Error", "Could not open Contact App");
        }
    }

    // Metode untuk membuka IdApp
    private void openIdApp() {
        try {
            IdApp idApp = new IdApp();
            idApp.start(primaryStage);
        } catch (Exception ex) {
            showAlert("Error", "Could not open ID App");
        }
    }

    // Metode untuk membuka NoteApp
    private void openNoteApp() {
        try {
            Note noteApp = new Note();
            noteApp.start(primaryStage);
        } catch (Exception ex) {
            showAlert("Error", "Could not open Note App");
        }
    }

    // Menampilkan alert untuk error
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static Scene getMenuScene() {
        return menuScene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
