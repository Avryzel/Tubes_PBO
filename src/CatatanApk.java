import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class CatatanApk extends Application {
    private ObservableList<Catatan> CatatanList = FXCollections.observableArrayList();
    private ListView<Catatan> CatatanListView = new ListView<>();

    @Override
    public void start(Stage TampilanCatatan) {
        CatatanListView.setItems(CatatanList);

        TextField Catatan = new TextField();
        Catatan.setPromptText("Tulisakan Catatan Anda..");

        // Add Note Buttons
        Button TombolTambah = new Button("Tambah Catatan");
        TombolTambah.setOnAction(e -> {
            String note = Catatan.getText();
            if (!note.isEmpty()) {
                CatatanList.add(new Catatan(note));
                Catatan.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Salah Memasukkan");
                alert.setHeaderText(null);
                alert.setContentText("Silahkan Periksa Kembali!");
                alert.showAndWait();
            }
        });

        // Edit Note Button
        Button TombolEdit = new Button("Edit Catatan");
        TombolEdit.setOnAction(e -> {
            Catatan selectedNote = CatatanListView.getSelectionModel().getSelectedItem();
            int selectedIndex = CatatanListView.getSelectionModel().getSelectedIndex();
            if (selectedNote != null && selectedIndex >= 0) {
                Catatan.setText(selectedNote.getCatatan()); // Populate the TextArea with the selected note
                CatatanList.remove(selectedIndex); // Remove the selected note from the list
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Tidak Ada Pilihan");
                alert.setHeaderText(null);
                alert.setContentText("Pilih Yang Ingin Di Edit!");
                alert.showAndWait();
            }
        });

        // Delete Note Button
        Button TombolHapus = new Button("Hapus Catatan");
        TombolHapus.setOnAction(e -> {
            Catatan selectedNote = CatatanListView.getSelectionModel().getSelectedItem();
            if (selectedNote != null) {
                CatatanList.remove(selectedNote);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Tidak Ada Pilihan");
                alert.setHeaderText(null);
                alert.setContentText("Pilih Yang Ingin Di Hapus!");
                alert.showAndWait();
            }
        });

        // Back Button
        Button TombolKembali = new Button("Kembali");
        TombolKembali.setOnAction(e -> {
            Menu menuApp = new Menu();
            try {
                menuApp.start(TampilanCatatan);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10, CatatanListView, Catatan, TombolTambah, TombolEdit, TombolHapus, TombolKembali);
        layout.setPadding(new Insets(10));
        Scene scene = new Scene(layout, 600, 400);

        TampilanCatatan.setTitle("Catatan");
        TampilanCatatan.setScene(scene);
        TampilanCatatan.show();
    }
}
