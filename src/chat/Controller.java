package chat;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class Controller {


    @FXML
    private BorderPane borderPane;
    @FXML
    private MenuItem exitItem;
    @FXML
    private MenuItem copyItem;
    @FXML
    private MenuItem pasteItem;
    @FXML
    private TextArea messagesArea;
    @FXML
    private TextField newMessageArea;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void initialize() {


        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        copyItem.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
        pasteItem.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));
    }

    @FXML
    public void sendMessage(ActionEvent actionEvent) {
        messagesArea.appendText(newMessageArea.getText() + "\n");
        newMessageArea.clear();
        newMessageArea.requestFocus();
    }

    @FXML
    public void menuExit(ActionEvent actionEvent) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stage.close();
            }
        });
    }

    @FXML
    public void menuCopy(ActionEvent actionEvent) {
        StringSelection stringSelection = new StringSelection(messagesArea.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
    @FXML
    public void menuPaste(ActionEvent actionEvent) {
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable t = c.getContents(this);
        if (t == null)
            return;
        try {
            newMessageArea.setText((String) t.getTransferData(DataFlavor.stringFlavor));
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
