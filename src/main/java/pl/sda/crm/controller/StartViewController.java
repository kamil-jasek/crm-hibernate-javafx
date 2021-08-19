package pl.sda.crm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class StartViewController {

    @FXML
    private Text typedName;

    @FXML
    public void onKeyTyped(KeyEvent event) {
        final var textField = (TextField) event.getSource();
        final var text = textField.getText();
        typedName.setText(text);
    }
}
