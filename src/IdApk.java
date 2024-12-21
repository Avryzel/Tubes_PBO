import java.net.IDN;
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

public class IdApk extends Application {
    private ObservableList<Id> IdList = FXCollections.observableArrayList();
    private ListView<Id> IdListView = new ListView<>();

    private void ambilId() {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM id";
            var statement = connection.prepareStatement(sql);
            var resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                String NamaApk = resultSet.getString("nama_aplikasi");
                String id = resultSet.getString("id");
                String Password = resultSet.getString("password");
                IdList.add(new Id(id, Password, NamaApk));
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("Gagal memuat ID dari database: " + ex.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public void start(Stage TampilanID) {
        IdListView.setItems(IdList);
        ambilId();

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
                try (Connection connection = database.connect()) {
                    String sql = "INSERT INTO id (id, password, nama_aplikasi) VALUES (?, ?, ?)";
                    var statement = connection.prepareStatement(sql);
                    statement.setString(1, id);
                    statement.setString(2, Password);
                    statement.setString(3, NamaApk);
                    statement.executeUpdate();
                    
                    IdList.add(new Id(id, Password, NamaApk));

                    NamaAPK.clear();
                    ID.clear();
                    PasswordId.clear();
                } catch (SQLException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Database Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Gagal menambahkan ID ke database: " + ex.getMessage());
                    alert.showAndWait();
                }

                
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
        Button TombolSimpanEdit = new Button("Simpan Perubahan");
        TombolSimpanEdit.setDisable(true);

        Button TombolEdit = new Button("Edit ID");
        TombolEdit.setOnAction(e -> {
            Id selectedId = IdListView.getSelectionModel().getSelectedItem();
            int selectedIndex = IdListView.getSelectionModel().getSelectedIndex();

            if (selectedId != null && selectedIndex >= 0) {
                NamaAPK.setText(selectedId.getNamaApk());
                ID.setText(selectedId.getId());
                PasswordId.setText(selectedId.getPassword());

                TombolSimpanEdit.setDisable(false);

                TombolSimpanEdit.setOnAction(saveEvent -> {
                    String newNamaAPK = NamaAPK.getText();
                    String newID = ID.getText();
                    String newPassword = PasswordId.getText();
                    
                if (!newID.isEmpty() && !newNamaAPK.isEmpty() && !newPassword.isEmpty()) {
                    
                    try (Connection connection = database.connect()) {
                        String sql = "UPDATE id SET nama_aplikasi = ?, id = ?, password = ? WHERE nama_aplikasi = ? AND id = ? AND password = ?";
                        var statement = connection.prepareStatement(sql);
                        statement.setString(1, newNamaAPK);
                    statement.setString(2, newID);
                    statement.setString(3, newPassword);
                    statement.setString(4, selectedId.getNamaApk());
                    statement.setString(5, selectedId.getId());
                    statement.setString(6, selectedId.getPassword());
                    statement.executeUpdate();
                    
                    IdList.set(selectedIndex, new Id(newID, newPassword, newNamaAPK));
                    
                    NamaAPK.clear();
                    ID.clear();
                    PasswordId.clear();
                    
                    TombolSimpanEdit.setDisable(true);
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Berhasil");
                            alert.setHeaderText(null);
                            alert.setContentText("ID berhasil diperbarui!");
                            alert.showAndWait();
                        } catch (SQLException ex) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Database Error");
                            alert.setHeaderText(null);
                            alert.setContentText("Gagal memperbarui ID di database: " + ex.getMessage());
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
                alert.setContentText("Pilih ID yang ingin diubah!");
                alert.showAndWait();
            }
        }); 

        // Button to delete selected ID
        Button TombolHapus = new Button("Hapus ID");
        TombolHapus.setOnAction(e -> {
            Id selectedId = IdListView.getSelectionModel().getSelectedItem();
            if (selectedId != null) {
                try (Connection connection = database.connect()) {
                    String sql = "DELETE FROM id WHERE id = ? AND password = ? AND nama_aplikasi = ?";
                    var statement = connection.prepareStatement(sql);
                    statement.setString(1, selectedId.getId());
                    statement.setString(2, selectedId.getPassword());
                    statement.setString(3, selectedId.getNamaApk());
                    statement.executeUpdate();
                    
                    IdList.remove(selectedId);
                } catch (SQLException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Database Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Gagal menghapus ID dari database: " + ex.getMessage());
                    alert.showAndWait();
                }
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

        VBox layout = new VBox(10, IdListView, NamaAPK, ID, PasswordId, TombolTambah, TombolEdit, TombolSimpanEdit, TombolHapus, TombolKembali);
        layout.setPadding(new Insets(10));
        Scene scene = new Scene(layout, 600, 400);

        TampilanID.setTitle("ID");
        TampilanID.setScene(scene);
        TampilanID.show();
    }
}
