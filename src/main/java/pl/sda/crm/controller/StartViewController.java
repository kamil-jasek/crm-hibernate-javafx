package pl.sda.crm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pl.sda.crm.service.PersonCustomerRegistration;
import pl.sda.crm.service.RegisterPersonForm;
import pl.sda.crm.util.HibernateUtil;

public class StartViewController {

    @FXML
    private Text customerId;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField pesel;

    @FXML
    private Text errorMessage;

    private final PersonCustomerRegistration registration;

    public StartViewController() {
        this.registration = new PersonCustomerRegistration(HibernateUtil.getSessionFactory());
    }

    @FXML
    public void onPersonSave(ActionEvent event) {
        // 0. Wyczyść poprzedni customerId
        customerId.setText("");

        // 1. wyciągniecie danych z kontrolek
        final var firstNameText = firstName.getText();
        final var lastNameText = lastName.getText();
        final var peselText = pesel.getText();

        // 2. zbudowanie obiektu RegisterPersonForm
        final var form = new RegisterPersonForm(firstNameText, lastNameText, peselText);

        // 3. wywołanie registerPerson
        try {
            final var registeredCustomerId = registration.registerPerson(form);
            // 4. wyświetlenie ID zarejestrowanego klienta
            customerId.setText(registeredCustomerId.getId().toString());
            errorMessage.setText("");
        } catch (Exception ex) {
            errorMessage.setText(ex.getMessage());
        }
    }
}
