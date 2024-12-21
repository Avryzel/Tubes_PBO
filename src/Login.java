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
    public void start(Stage TampilanLogin) {
        // Menambahkan gambar logo
        Image logo = new Image("./lampu.png");
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(100);
        logoView.setPreserveRatio(true);

        Label UserJudul = new Label("Username:");
        TextField User = new TextField();
        Label PassJudul = new Label("Password:");
        PasswordField Password = new PasswordField();
        Button loginButton = new Button("Login");

        loginButton.setOnAction(e -> {
            String username = User.getText();
            String password = Password.getText();

            if (username.equals("") && password.equals("")) {
                Menu menuApp = new Menu();
                try {
                    menuApp.start(TampilanLogin);
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

        VBox layout = new VBox(10, logoView, UserJudul, User, PassJudul, Password, loginButton);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f0f0f0;");
        Scene scene = new Scene(layout, 600, 400);

        TampilanLogin.setTitle("Login");
        TampilanLogin.setScene(scene);
        TampilanLogin.show();
    }
}
