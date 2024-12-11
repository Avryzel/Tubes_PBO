import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class MenuApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button contactButton = new Button("Contact App");
        Button idButton = new Button("ID App");
        Button noteButton = new Button("Note App");
        Button exitButton = new Button("Exit");

        contactButton.setOnAction(e -> {
            ContactApp contactApp = new ContactApp();
            try {
                contactApp.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        idButton.setOnAction(e -> {
            IdApp idApp = new IdApp();
            try {
                idApp.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        noteButton.setOnAction(e -> {
            Note noteApp = new Note();
            try {
                noteApp.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        exitButton.setOnAction(e -> primaryStage.close());

        VBox layout = new VBox(10, contactButton, idButton, noteButton, exitButton);
        layout.setPadding(new Insets(10));
        Scene scene = new Scene(layout, 300, 200);

        primaryStage.setTitle("Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}