import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Login extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Menambahkan gambar logo
        Image logo = new Image("C:\\KULIAH SEMESTER 3\\PBO\\Prak PBO\\Tubes_PBO\\lampu.PNG"); // Ganti dengan path gambar yang sesuai
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(100);
        logoView.setPreserveRatio(true);

        Label userLabel = new Label("Username:");
        TextField userField = new TextField();
        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();
        Button loginButton = new Button("Login");

        loginButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();

            if (username.equals("admin") && password.equals("1234")) {
                MenuApp menuApp = new MenuApp();
                try {
                    menuApp.start(primaryStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password!");
                alert.showAndWait();
            }
        });

        VBox layout = new VBox(10, logoView, userLabel, userField, passLabel, passField, loginButton);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f0f0f0;"); // Mengatur warna latar belakang
        Scene scene = new Scene(layout, 300, 300);

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}