import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Stage primaryStage;
    private Scene loginScene, menuScene;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Aplikasi Kontak");

        // Membuat tampilan login
        createLoginScene();

        // Menampilkan tampilan login
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void createLoginScene() {
        // Label dan field input
        Label userLabel = createLabel("Username:");
        TextField userField = createTextField();
        
        Label passLabel = createLabel("Password:");
        PasswordField passField = createPasswordField();
        
        // Tombol login
        Button loginButton = createLoginButton(userField, passField);

        // Layout untuk login
        VBox loginLayout = new VBox(15, userLabel, userField, passLabel, passField, loginButton);
        loginLayout.setPadding(new Insets(30));
        loginLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f0f0, #e0e0e0);" + 
                              "-fx-background-radius: 10;");
        loginLayout.setAlignment(Pos.CENTER);

        loginScene = new Scene(loginLayout, 600, 400); // Ukuran disesuaikan
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-weight: bold;");
        return label;
    }

    private TextField createTextField() {
        TextField textField = new TextField();
        textField.setStyle("-fx-background-radius: 5; -fx-padding: 8px;");
        return textField;
    }

    private PasswordField createPasswordField() {
        PasswordField passwordField = new PasswordField();
        passwordField.setStyle("-fx-background-radius: 5; -fx-padding: 8px;");
        return passwordField;
    }

    private Button createLoginButton(TextField userField, PasswordField passField) {
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #4CAF50;" + 
                             "-fx-text-fill: white;" + 
                             "-fx-background-radius: 5;" + 
                             "-fx-padding: 10px;" +
                             "-fx-font-weight: bold;");
        loginButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();
            if (username.equals("admin") && password.equals("1234")) {
                createMenuScene();
                primaryStage.setScene(menuScene);
            } else {
                showAlert("Login Failed", "Invalid username or password!");
            }
        });
        return loginButton;
    }

    private void createMenuScene() {
        // Tombol untuk menu dengan styling yang lebih menarik
        Button contactButton = createStyledButton("Contact App", "#2196F3");
        Button idButton = createStyledButton("ID App", "#4CAF50");
        Button noteButton = createStyledButton("Note App", "#FF9800");
        Button exitButton = createStyledButton("Exit", "#F44336");

        contactButton.setOnAction(e -> openContactApp());
        idButton.setOnAction(e -> openIdApp());
        noteButton.setOnAction(e -> openNoteApp());
        exitButton.setOnAction(e -> primaryStage.close());

        // Layout untuk menu
        VBox menuLayout = new VBox(15, contactButton, idButton, noteButton, exitButton);
        menuLayout.setPadding(new Insets(30));
        menuLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f0f0, #e0e0e0);" + 
                             "-fx-background-radius: 10;");
        menuLayout.setAlignment(Pos.CENTER);

        menuScene = new Scene(menuLayout, 600, 400);
    }

    // Metode helper untuk membuat tombol dengan styling
    private Button createStyledButton(String text, String color) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + color + ";" + 
                         "-fx-text-fill: white;" + 
                         "-fx-background-radius: 5;" + 
                         "-fx-padding: 10px;" +
                         "-fx-font-weight: bold;" +
                         "-fx-min-width: 200px;");
        return button;
    }

    // Metode untuk membuka aplikasi

    private void openContactApp() {
        try {
            ContactApp contactApp = new ContactApp();
            contactApp.start(primaryStage);
        } catch (Exception ex) {
            showAlert("Error", "Could not open Contact App");
        }
    }

    private void openIdApp() {
        try {
            IdApp idApp = new IdApp();
            idApp.start(primaryStage);
        } catch (Exception ex) {
            showAlert("Error", "Could not open ID App");
        }
    }

    private void openNoteApp() {
        try {
            Note noteApp = new Note();
            noteApp.start(primaryStage);
        } catch (Exception ex) {
            showAlert("Error", "Could not open Note App");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}