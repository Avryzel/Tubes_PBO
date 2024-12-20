import java.sql.Connection;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

    

// Class ConatactApp
public class KontakApk extends Application {
    private ObservableList<Kontak> KontakList = FXCollections.observableArrayList();
    private ListView<Kontak> KontakListView = new ListView<>();

    private void ambilKontak() {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM kontak";
            var statement = connection.prepareStatement(sql);
            var resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                String name = resultSet.getString("nama");
                String phone = resultSet.getString("no_hp");
                KontakList.add(new Kontak(name, phone));
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("Gagal memuat kontak dari database: " + ex.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public void start(Stage TampilanKontak){
        KontakListView.setItems(KontakList);
        ambilKontak();

        // Form input
        Label nama = new Label("Nama:");
        nama.setStyle("-fx-font-size: 15;" +
                      "-fx-font-weight: bold;");
        Label NoHP = new Label("No Hp:");
        NoHP.setStyle("-fx-font-size: 15;" +
                      "-fx-font-weight: bold;");
        TextField Nama = new TextField();
        Nama.setPromptText("Nama");
        TextField NoHandphone = new TextField();
        NoHandphone.setPromptText("Nomor Hp");

        Button TombolTambah = new Button("Tambah Kontak");
        TombolTambah.setOnAction(e -> {
            String name = Nama.getText();
            String phone = NoHandphone.getText();
            if (!name.isEmpty() && !phone.isEmpty()) {
                try (Connection connection = database.connect()) {
                    String sql = "INSERT INTO kontak (nama, no_hp, pesan) VALUES (?, ?, ?)";
                    var statement = connection.prepareStatement(sql);
                    statement.setString(1, name);
                    statement.setString(2, phone);
                    statement.setString(3, "");
                    statement.executeUpdate();
                    
                    KontakList.add(new Kontak(name, phone));

                    Nama.clear();
                    NoHandphone.clear();
                } catch (SQLException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Database Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Gagal menambahkan kontak ke database: " + ex.getMessage());
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

        // Edit and delete button
        Button TombolSimpanEdit = new Button("Simpan Perubahan");
        TombolSimpanEdit.setDisable(true);
        
        Button TombolEdit = new Button("Edit Kontak");
        TombolEdit.setOnAction(e -> {
            Kontak selectContact = KontakListView.getSelectionModel().getSelectedItem();
            int selectedIndex = KontakListView.getSelectionModel().getSelectedIndex();
        
            if (selectContact != null && selectedIndex >= 0) {
                Nama.setText(selectContact.getNama());
                NoHandphone.setText(selectContact.getNoHP());
        
                TombolSimpanEdit.setDisable(false);
        
                TombolSimpanEdit.setOnAction(saveEvent -> {
                    String newName = Nama.getText();
                    String newPhone = NoHandphone.getText();
        
                    if (!newName.isEmpty() && !newPhone.isEmpty()) {
                        try (Connection connection = database.connect()) {
                            String sql = "UPDATE kontak SET nama = ?, no_hp = ? WHERE nama = ? AND no_hp = ?";
                            var statement = connection.prepareStatement(sql);
                            statement.setString(1, newName);
                            statement.setString(2, newPhone);
                            statement.setString(3, selectContact.getNama());
                            statement.setString(4, selectContact.getNoHP());
                            statement.executeUpdate();
        
                            KontakList.set(selectedIndex, new Kontak(newName, newPhone));
        
                            Nama.clear();
                            NoHandphone.clear();
        
                            TombolSimpanEdit.setDisable(true);
        
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Berhasil");
                            alert.setHeaderText(null);
                            alert.setContentText("Kontak berhasil diperbarui!");
                            alert.showAndWait();
                        } catch (SQLException ex) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Database Error");
                            alert.setHeaderText(null);
                            alert.setContentText("Gagal memperbarui kontak di database: " + ex.getMessage());
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
                alert.setContentText("Pilih kontak yang ingin diubah!");
                alert.showAndWait();
            }
        });       

        Button TombolHapus = new Button("Hapus Kontak");
        TombolHapus.setOnAction(e -> {
            Kontak selectedContact = KontakListView.getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                try (Connection connection = database.connect()) {
                    String sql = "DELETE FROM kontak WHERE nama = ? AND no_hp = ?";
                    var statement = connection.prepareStatement(sql);
                    statement.setString(1, selectedContact.getNama());
                    statement.setString(2, selectedContact.getNoHP());
                    statement.executeUpdate();
                    
                    KontakList.remove(selectedContact);
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

        // Back button
        Button TombolKembali = new Button("Kembali");
        TombolKembali.setOnAction(e -> {
            Menu menuApp = new Menu();
            try {
                menuApp.start(TampilanKontak);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Layouts
        VBox Layout = new VBox(10, KontakListView);
        VBox.setVgrow(KontakListView, Priority.ALWAYS); 
        HBox.setHgrow(Layout, Priority.ALWAYS); 

        // Input berada di sebelah kanan
        VBox Input = new VBox(10, nama, Nama, NoHP, NoHandphone, TombolTambah, TombolEdit, TombolSimpanEdit, TombolHapus, TombolKembali);
        Input.setPadding(new Insets(10));

        // Tata letak utama
        HBox main = new HBox(10, Layout, Input);
        main.setPadding(new Insets(10));

        Scene scene = new Scene(main, 600, 400);

        TampilanKontak.setTitle("Kontak");
        TampilanKontak.setScene(scene);
        TampilanKontak.show();

        // Messaging feature
        // TextField messageField = new TextField();
        // messageField.setPromptText("Tulisakan Pesan Anda");
        // Button sendMessageButton = new Button("Kirim Pesan");
    
        // sendMessageButton.setOnAction(e -> {
        //     Kontak selectedContact = KontakListView.getSelectionModel().getSelectedItem();
        //     if (selectedContact != null) {
        //         selectedContact.setPesan(messageField.getText());
        //         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //         alert.setTitle("Kirim Pesan");
        //         alert.setHeaderText("Kirim Pesan Ke: " + selectedContact.getNama());
        //         alert.setContentText("Pesan: " + messageField.getText());
        //         alert.showAndWait();
        //         messageField.clear();
        //     }
        // });
    }
}
