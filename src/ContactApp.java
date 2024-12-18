import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// Class ConatactApp
public class ContactApp extends Application {
    private ObservableList<Contact> contactList = FXCollections.observableArrayList();
    private ListView<Contact> contactListView = new ListView<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        contactListView.setItems(contactList);

        // Form input
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");
        Button addButton = new Button("Add Contact");

        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String phone = phoneField.getText();
            if (!name.isEmpty() && !phone.isEmpty()) {
                contactList.add(new Contact(name, phone));
                nameField.clear();
                phoneField.clear();
            }
        });

        // Edit and delete button
        Button editButton = new Button("Edit Contact");
        Button deleteButton = new Button("Delete Contact");

        editButton.setOnAction(e -> {
            Contact selectedContact = contactListView.getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                selectedContact.setNama(nameField.getText());
                selectedContact.setNoHP(phoneField.getText());
                contactListView.refresh();
            }
        });

        deleteButton.setOnAction(e -> {
            Contact selectedContact = contactListView.getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                contactList.remove(selectedContact);
            }
        });

        // Messaging feature
        TextField messageField = new TextField();
        messageField.setPromptText("Type your message here");
        Button sendMessageButton = new Button("Send Message");

        sendMessageButton.setOnAction(e -> {
            Contact selectedContact = contactListView.getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                selectedContact.setPesan(messageField.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message Sent");
                alert.setHeaderText("Message to " + selectedContact.getNama());
                alert.setContentText("Message: " + messageField.getText());
                alert.showAndWait();
                messageField.clear();
            }
        });

        // Layouts
        VBox formLayout = new VBox(10, nameField, phoneField, addButton, backButton);
        VBox actionLayout = new VBox(10, editButton, deleteButton, messageField, sendMessageButton);
        HBox mainLayout = new HBox(10, contactListView, formLayout, actionLayout);
        mainLayout.setPadding(new Insets(10));
        

        Scene scene = new Scene(mainLayout, 600, 400);
        primaryStage.setTitle("Contact App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
