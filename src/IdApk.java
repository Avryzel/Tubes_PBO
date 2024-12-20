import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class IdApk extends Application {
    private ObservableList<Id> IdList = FXCollections.observableArrayList();
    private ListView<Id> IdListView = new ListView<>();

    @Override
    public void start(Stage TampilanID) {
        IdListView.setItems(IdList);

        TextField NamaAPK = new TextField();
        NamaAPK.setPromptText("Nama Aplikasi");
        TextField ID = new TextField();
        ID.setPromptText("Masukkan ID");
        TextField PasswordId = new TextField(); 
        PasswordId.setPromptText("Masukkan Password");

        // Button to add ID
        Button TombolTambah = new Button("Tambah Id");
        TombolTambah.setOnAction(e -> {
            String NamaApk = NamaAPK.getText();
            String id = ID.getText();
            String Password = PasswordId.getText();
            if (!id.isEmpty() && !Password.isEmpty()) {
                IdList.add(new Id(id, Password, NamaApk));
                NamaAPK.clear();
                ID.clear();
                PasswordId.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Salah Memasukkan");
                alert.setHeaderText(null);
                alert.setContentText("Silahkan Periksa Kembali!!");
                alert.showAndWait();
            }
        });

        // Button to edit selected ID
        Button TombolEdit = new Button("Edit ID");
        TombolEdit.setOnAction(e -> {
            Id selectedId = IdListView.getSelectionModel().getSelectedItem();
            int selectedIndex = IdListView.getSelectionModel().getSelectedIndex();
            if (selectedId != null && selectedIndex >= 0) {
                ID.setText(selectedId.getId());
                PasswordId.setText(selectedId.getPassword());
                NamaAPK.setText(selectedId.getNamaApk());
                IdList.remove(selectedIndex);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Tidak Ada Pilihan");
                alert.setHeaderText(null);
                alert.setContentText("Pilih Yang Ingin Di Edit!");
                alert.showAndWait();
            }
        });

        // Button to delete selected ID
        Button TombolHapus = new Button("Hapus ID");
        TombolHapus.setOnAction(e -> {
            Id selectedId = IdListView.getSelectionModel().getSelectedItem();
            if (selectedId != null) {
                IdList.remove(selectedId);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Tidak Ada Pilihan");
                alert.setHeaderText(null);
                alert.setContentText("Pilih Yang Ingin Di Hapus!");
                alert.showAndWait();
            }
        });

        // Back button to return to the menu
        Button TombolKembali = new Button("Kembali");
        TombolKembali.setOnAction(e -> {
            Menu menuApp = new Menu();
            try {
                menuApp.start(TampilanID);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10, IdListView, NamaAPK, ID, PasswordId, TombolTambah, TombolEdit, TombolHapus, TombolKembali);
        layout.setPadding(new Insets(10));
        Scene scene = new Scene(layout, 600, 400);

        TampilanID.setTitle("ID");
        TampilanID.setScene(scene);
        TampilanID.show();
    }
}
