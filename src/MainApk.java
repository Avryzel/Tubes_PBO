import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;

public class MainApk extends Application {
    private Stage TampilanUtama;
    private Scene TampilanLogin, TampilanMenu, TampilanRegister;

    @Override
    public void start(Stage TampilanUtama) {
        this.TampilanUtama = TampilanUtama;
        TampilanUtama.setTitle("Aplikasi Kontak");

        // Create login and register scenes
        MembuatTampilanMenu();
        MembuatTampilanRegister();

        // Show login scene
        TampilanUtama.setScene(TampilanLogin);
        TampilanUtama.show();
    }

    private void MembuatTampilanMenu() {
        Label JudulLabel = new Label("Login");
        JudulLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField uerLabel = new TextField();
        uerLabel.setPromptText("Username");
        uerLabel.setStyle("-fx-background-radius: 5; -fx-padding: 8px;");

        PasswordField passLabel = new PasswordField();
        passLabel.setPromptText("Password");
        passLabel.setStyle("-fx-background-radius: 5; -fx-padding: 8px;");

        Button TombolLogin = new Button("Login");
        TombolLogin.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 5; -fx-padding: 10px 12px; -fx-font-weight: bold; -fx-font-size: 15;");
        TombolLogin.setOnAction(e -> {
            String username = uerLabel.getText();
            String password = passLabel.getText();
            if (authenticateUser(username, password)) {
                createTampilanMenu();
                TampilanUtama.setScene(TampilanMenu);
            } else {
                showAlert("Login Failed", "Invalid username or password.");
            }
        });

        Button TombolRegister = new Button("Register");
        TombolRegister.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-background-radius: 5; -fx-padding: 10px 12px; -fx-font-weight: bold; -fx-font-size: 15;");
        TombolRegister.setOnAction(e -> TampilanUtama.setScene(TampilanRegister));

        VBox menuLayout = new VBox(10, JudulLabel, uerLabel, passLabel, TombolLogin, TombolRegister);
        menuLayout.setPadding(new Insets(20));
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f0f0, #e0e0e0); -fx-background-radius: 10;");

        TampilanLogin = new Scene(menuLayout, 600, 400);
    }

    private void MembuatTampilanRegister() {
        Label JudulLabel = new Label("Register");
        JudulLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField uerLabel = new TextField();
        uerLabel.setPromptText("Username");
        uerLabel.setStyle("-fx-background-radius: 5; -fx-padding: 8px;");

        PasswordField passLabel = new PasswordField();
        passLabel.setPromptText("Password");
        passLabel.setStyle("-fx-background-radius: 5; -fx-padding: 8px;");

        Button TombolRegister = new Button("Register");
        TombolRegister.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 5; -fx-padding: 10px 12px; -fx-font-weight: bold; -fx-font-size: 15;");
        TombolRegister.setOnAction(e -> {
            String username = uerLabel.getText();
            String password = passLabel.getText();
            if (registerUser(username, password)) {
                showAlert("Success", "Registration successful. Please login.");
                TampilanUtama.setScene(TampilanLogin);
            } else {
                showAlert("Registration Failed", "Username already exists.");
            }
        });

        Button backButton = new Button("Back to Login");
        backButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: white; -fx-background-radius: 5; -fx-padding: 10px 12px; -fx-font-weight: bold; -fx-font-size: 15;");
        backButton.setOnAction(e -> TampilanUtama.setScene(TampilanLogin));

        VBox menuLayout = new VBox(10, JudulLabel, uerLabel, passLabel, TombolRegister, backButton);
        menuLayout.setPadding(new Insets(20));
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f0f0, #e0e0e0); -fx-background-radius: 10;");

        TampilanRegister = new Scene(menuLayout, 600, 400);
    }

    private void createTampilanMenu() {
        // Tombol untuk menu dengan styling yang lebih menarik
        Button contactButton = MembuatStyledButton("Kontak Apk", "#2196F3");
        Button idButton = MembuatStyledButton("Id Apk", "#4CAF50");
        Button noteButton = MembuatStyledButton("Catatan Apk", "#FF9800");
        Button exitButton = MembuatStyledButton("Keluar", "#F44336");

        contactButton.setOnAction(e -> openContactApp());
        idButton.setOnAction(e -> openIdApp());
        noteButton.setOnAction(e -> openNoteApp());
        exitButton.setOnAction(e -> TampilanUtama.close());

        // menuLayout untuk menu
        VBox menumenuLayout = new VBox(15, contactButton, idButton, noteButton, exitButton);
        menumenuLayout.setPadding(new Insets(30));
        menumenuLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f0f0, #e0e0e0);" + 
                             "-fx-background-radius: 10;");
        menumenuLayout.setAlignment(Pos.CENTER);
        
        TampilanMenu = new Scene(menumenuLayout, 600, 400);
    }

    // Metode helper untuk membuat tombol dengan styling
    private Button MembuatStyledButton(String text, String color) {
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
            KontakApk contactApp = new KontakApk();
            contactApp.start(TampilanUtama);
        } catch (Exception ex) {
            showAlert("Error", "Tidak bisa membuka Kontak Apk");
        }
    }

    private void openIdApp() {
        try {
            IdApk idApp = new IdApk();
            idApp.start(TampilanUtama);
        } catch (Exception ex) {
            showAlert("Error", "Tidak bisa membuka Id Apk");
        }
    }

    private void openNoteApp() {
        try {
            CatatanApk noteApp = new CatatanApk();
            noteApp.start(TampilanUtama);
        } catch (Exception ex) {
            showAlert("Error", "Tidak bisa membuka Catatan Apk");
        }
    }

    private boolean authenticateUser(String username, String password) {
        try (Connection connection = database.connect()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            showAlert("Database Error", "Error during login: " + e.getMessage());
            return false;
        }
    }

    private boolean registerUser(String username, String password) {
        try (Connection connection = database.connect()) {
            String insertSQL = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(insertSQL);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                return false;
            } else {
                showAlert("Database Error", "Error during registration: " + e.getMessage());
                return false;
            }
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
