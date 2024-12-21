import java.sql.Connection;
import java.sql.SQLException;

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

    private void ambilCatatan() {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM catatan";
            var statement = connection.prepareStatement(sql);
            var resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                String catatan = resultSet.getString("catatan");
                CatatanList.add(new Catatan(catatan));
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("Gagal memuat catatan dari database: " + ex.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public void start(Stage TampilanCatatan) {
        CatatanListView.setItems(CatatanList);
        ambilCatatan();

        TextField Catatan = new TextField();
        Catatan.setPromptText("Tulisakan Catatan Anda..");

        // Add Note Buttons
        Button TombolTambah = new Button("Tambah Catatan");
        TombolTambah.setOnAction(e -> {
            String note = Catatan.getText();
            if (!note.isEmpty()) {
                try (Connection connection = database.connect()) {
                    String sql = "INSERT INTO catatan (catatan) VALUES (?)";
                    var statement = connection.prepareStatement(sql);
                    statement.setString(1, note);
                    statement.executeUpdate();
                    
                    CatatanList.add(new Catatan(note));

                    Catatan.clear();
                } catch (SQLException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Database Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Gagal menambahkan catatan ke database: " + ex.getMessage());
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Salah Memasukkan");
                alert.setHeaderText(null);
                alert.setContentText("Silahkan Periksa Kembali!");
                alert.showAndWait();
            }
        });

        // Edit Note Button
        Button TombolSimpanEdit = new Button("Simpan Perubahan");
        TombolSimpanEdit.setDisable(true);

        Button TombolEdit = new Button("Edit Catatan");
        TombolEdit.setOnAction(e -> {
            Catatan selectedNote = CatatanListView.getSelectionModel().getSelectedItem();
            int selectedIndex = CatatanListView.getSelectionModel().getSelectedIndex();
            
            if (selectedNote != null && selectedIndex >= 0) {
                Catatan.setText(selectedNote.getCatatan());
 
                TombolSimpanEdit.setDisable(false);
        
                TombolSimpanEdit.setOnAction(saveEvent -> {
                String newCatatan = Catatan.getText();
                
                if (!newCatatan.isEmpty()) {
                    try (Connection connection = database.connect()) {
                        String sql = "UPDATE catatan SET catatan = ? WHERE catatan = ?";
                        var statement = connection.prepareStatement(sql);
                        statement.setString(1, newCatatan);
                        statement.setString(2, selectedNote.getCatatan());
                        statement.executeUpdate();

                        CatatanList.set(selectedIndex, new Catatan(newCatatan));

                        Catatan.clear();

                        TombolSimpanEdit.setDisable(true);
        
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Berhasil");
                        alert.setHeaderText(null);
                        alert.setContentText("Catatan berhasil diperbarui!");
                        alert.showAndWait();
                    } catch (SQLException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Database Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Gagal memperbarui catatab di database: " + ex.getMessage());
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Input Kosong");
                    alert.setHeaderText(null);
                    alert.setContentText("Silakan isi semua kolom!");
                    alert.showAndWait();
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Tidak Ada Pilihan");
            alert.setHeaderText(null);
            alert.setContentText("Pilih catatan yang ingin diubah!");
            alert.showAndWait();
        }
    });  

        // Delete Note Button
        Button TombolHapus = new Button("Hapus Catatan");
        TombolHapus.setOnAction(e -> {
            Catatan selectedNote = CatatanListView.getSelectionModel().getSelectedItem();
            if (selectedNote != null) {
                try (Connection connection = database.connect()) {
                    String sql = "DELETE FROM catatan WHERE catatan = ?";
                    var statement = connection.prepareStatement(sql);
                    statement.setString(1, selectedNote.getCatatan());
                    statement.executeUpdate();
                    
                    CatatanList.remove(selectedNote);
                } catch (SQLException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Database Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Gagal menghapus kontak dari database: " + ex.getMessage());
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Tidak Ada Pilihan");
                alert.setHeaderText(null);
                alert.setContentText("Pilih kontak yang ingin dihapus!");
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

        VBox layout = new VBox(10, CatatanListView, Catatan, TombolTambah, TombolEdit, TombolSimpanEdit, TombolHapus, TombolKembali);
        layout.setPadding(new Insets(10));
        Scene scene = new Scene(layout, 600, 400);

        TampilanCatatan.setTitle("Catatan");
        TampilanCatatan.setScene(scene);
        TampilanCatatan.show();
    }
}
