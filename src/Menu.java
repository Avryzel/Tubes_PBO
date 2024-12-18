import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Application {
    private static Scene TampilanMenu;
    private Stage TampilanUtama;

    @Override
    public void start(Stage TampilanUtama) {
        this.TampilanUtama = TampilanUtama;

        // Membuat tombol dengan gaya
        Button TombolKontak = createStyledButton("Kontak Apk", "#4CAF50");
        Button TombolID = createStyledButton("ID Apk", "#2196F3");
        Button TombolCatatan = createStyledButton("Catatan Apk", "#FF9800");
        Button TombolKembali = createStyledButton("Keluar", "#F44336");

        // Menambahkan event handler untuk tombol
        TombolKontak.setOnAction(e -> KontakApk());
        TombolID.setOnAction(e -> IdApk());
        TombolCatatan.setOnAction(e -> CatatanApk());
        TombolKembali.setOnAction(e -> TampilanUtama.close());

        // Layout menu
        VBox menuLayout = new VBox(15, TombolKontak, TombolID, TombolCatatan, TombolKembali);
        menuLayout.setPadding(new Insets(30));
        menuLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f0f0, #e0e0e0); " +
                            "-fx-background-radius: 10;");
        menuLayout.setAlignment(Pos.CENTER);

        TampilanMenu = new Scene(menuLayout, 600, 400);

        // Atur dan tampilkan stage
        TampilanUtama.setTitle("Menu");
        TampilanUtama.setScene(TampilanMenu);
        TampilanUtama.show();
    }

    // Metode untuk membuat tombol bergaya
    private Button createStyledButton(String text, String color) {
        Button Tombol = new Button(text);
        Tombol.setStyle("-fx-background-color: " + color + "; " +
                        "-fx-text-fill: white; " +
                        "-fx-background-radius: 5; " +
                        "-fx-padding: 10px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-min-width: 200px;");
        return Tombol;
    }

    // Metode untuk membuka kontakApk
    private void KontakApk() {
        try {
            KontakApk kontakApk = new KontakApk();
            kontakApk.start(TampilanUtama);
        } catch (Exception ex) {
            showAlert("Error", "Tidak bisa membuka Kontak Apk");
        }
    }

    // Metode untuk membuka IdApp
    private void IdApk() {
        try {
            IdApk idApp = new IdApk();
            idApp.start(TampilanUtama);
        } catch (Exception ex) {
            showAlert("Error", "Tidak bisa membuka Id Apk");
        }
    }

    // Metode untuk membuka NoteApp
    private void CatatanApk() {
        try {
            CatatanApk noteApp = new CatatanApk();
            noteApp.start(TampilanUtama);
        } catch (Exception ex) {
            showAlert("Error", "Tidak bisa membuka Catatan Apk");
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

    public static Scene getTampilanMenu() {
        return TampilanMenu;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
