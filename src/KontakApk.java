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

    @Override
    public void start(Stage TampilanKontak){
        KontakListView.setItems(KontakList);

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
                KontakList.add(new Kontak(name, phone));
                Nama.clear();
                NoHandphone.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Salah Memasukkan");
                alert.setHeaderText(null);
                alert.setContentText("Silahkan Periksa Kembali!");
                alert.showAndWait();
            }
        });

        // Edit and delete button
        Button TombolEdit = new Button("Edit Kontak");
        TombolEdit.setOnAction(e -> {
            Kontak selectContact = KontakListView.getSelectionModel().getSelectedItem();
            int selectedIndex = KontakListView.getSelectionModel().getSelectedIndex();
            if (selectContact != null && selectedIndex >= 0) {
                Nama.setText(selectContact.getNama());
                NoHandphone.setText(selectContact.getNoHP());
                KontakList.remove(selectedIndex);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Tidak Ada Pilihan");
                alert.setHeaderText(null);
                alert.setContentText("Pilih Yang Ingin Di Edit!");
                alert.showAndWait();
            }
        });

        Button TombolHapus = new Button("Hapus Kontak");
        TombolHapus.setOnAction(e -> {
            Kontak selectedContact = KontakListView.getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                KontakList.remove(selectedContact);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Tidak Ada Pilihan");
                alert.setHeaderText(null);
                alert.setContentText("Pilih Yang Ingin Di Hapus!");
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
        VBox.setVgrow(KontakListView, Priority.ALWAYS); // KontakListView agar bisa mengisi ruang vertikal
        HBox.setHgrow(Layout, Priority.ALWAYS); // Membuat Layout lebih lebar

        // Input berada di sebelah kanan
        VBox Input = new VBox(10, nama, Nama, NoHP, NoHandphone, TombolTambah, TombolEdit, TombolHapus, TombolKembali);
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
