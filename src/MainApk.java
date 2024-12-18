import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainApk extends Application {
    private Stage TampilanUtama;
    private Scene TampilanLogin, TampilanMenu;

    @Override
    public void start(Stage TampilanUtama) {
        this.TampilanUtama = TampilanUtama;
        TampilanUtama.setTitle("Aplikasi Kontak");

        // Membuat tampilan login
        MembuatTampilanLogin();

        // Menampilkan tampilan login
        TampilanUtama.setScene(TampilanLogin);
        TampilanUtama.show();
    }

    private void MembuatTampilanLogin() {
        Label JudulLabel = MembuatLabel("Aplikasi Kontak");
        // Label dan field input
        Label userLabel = MembuatLabel("Username:");
        TextField User = MembuatTextField();
        
        Label passLabel = MembuatLabel("Password:");
        PasswordField Password = MembuatPasswordField();
        
        // Tombol login
        Button TombolLogin = MembuatTombolLogin(User, Password);

        // Layout untuk login
        JudulLabel.setStyle("-fx-font-size: 25;" +
                            "-fx-font-weight: bold;");
        userLabel.setStyle("-fx-font-size: 20;" +
                           "-fx-font-weight: bold");
        passLabel.setStyle("-fx-font-size: 20;" +
                           "-fx-font-weight: bold");
        VBox loginLayout = new VBox(15, JudulLabel, userLabel, User, passLabel, Password, TombolLogin);
        loginLayout.setPadding(new Insets(30));
        loginLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f0f0, #e0e0e0);" + "-fx-background-radius: 10;");
        loginLayout.setAlignment(Pos.CENTER);

        TampilanLogin = new Scene(loginLayout, 600, 400); // Ukuran disesuaikan
    }

    private Label MembuatLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-weight: bold;");
        return label;
    }

    private TextField MembuatTextField() {
        TextField textField = new TextField();
        textField.setStyle("-fx-background-radius: 5; -fx-padding: 8px;");
        return textField;
    }

    private PasswordField MembuatPasswordField() {
        PasswordField passwordField = new PasswordField();
        passwordField.setStyle("-fx-background-radius: 5; -fx-padding: 8px;");
        return passwordField;
    }

    private Button MembuatTombolLogin(TextField User, PasswordField Password) {
        Button TombolLogin = new Button("Login");
        TombolLogin.setStyle("-fx-background-color: #4CAF50;" + 
                             "-fx-text-fill: white;" + 
                             "-fx-background-radius: 5;" + 
                             "-fx-padding: 10px 12px;" +
                             "-fx-font-weight: bold;" +
                             "-fx-font-size: 15");
        TombolLogin.setOnAction(e -> {
            String username = User.getText();
            String password = Password.getText();
            if (username.equals("admin") && password.equals("1234")) {
                MembuatTampilanMenu();
                TampilanUtama.setScene(TampilanMenu);
            } else {
                showAlert("Login Failed", "Invalid username or password!");
            }
        });
        return TombolLogin;
    }

    private void MembuatTampilanMenu() {
        // Tombol untuk menu dengan styling yang lebih menarik
        Button contactButton = MembuatStyledButton("Kontak Apk", "#2196F3");
        Button idButton = MembuatStyledButton("Id Apk", "#4CAF50");
        Button noteButton = MembuatStyledButton("Catatan Apk", "#FF9800");
        Button exitButton = MembuatStyledButton("Keluar", "#F44336");

        contactButton.setOnAction(e -> openContactApp());
        idButton.setOnAction(e -> openIdApp());
        noteButton.setOnAction(e -> openNoteApp());
        exitButton.setOnAction(e -> TampilanUtama.close());

        // Layout untuk menu
        VBox menuLayout = new VBox(15, contactButton, idButton, noteButton, exitButton);
        menuLayout.setPadding(new Insets(30));
        menuLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f0f0, #e0e0e0);" + 
                             "-fx-background-radius: 10;");
        menuLayout.setAlignment(Pos.CENTER);

        TampilanMenu = new Scene(menuLayout, 600, 400);
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