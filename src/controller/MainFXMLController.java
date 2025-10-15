/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.student;

/**
 * FXML Controller class
 *
 * @author deshm
 */
public class MainFXMLController implements Initializable {

    @FXML
    private TextField name_textfield;
    @FXML
    private TextField age_textfield;
    @FXML
    private TextField email_textfield;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization if needed
    }

    @FXML
    public void submit() {
        try {
            String name = name_textfield.getText().trim();
            String ageText = age_textfield.getText().trim();
            String email = email_textfield.getText().trim();

            // Validation
            if (name.isEmpty() || ageText.isEmpty() || email.isEmpty()) {
                showErrorAlert("All fields must be filled.");
                return;
            }

            // Validate age (must be a positive number)
            int age;
            try {
                age = Integer.parseInt(ageText);
                if (age <= 0 || age > 120) {
                    showErrorAlert("Please enter a valid age between 1 and 120.");
                    return;
                }
            } catch (NumberFormatException e) {
                showErrorAlert("Age must be a valid number.");
                return;
            }

            // Validate email format
            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                showErrorAlert("Please enter a valid email address.");
                return;
            }

            // Create model object
            student s = new student(name, age, email);

            // Show success alert with details
            String details = "Name: " + s.getName()
                    + "\nAge: " + s.getAge()
                    + "\nEmail: " + s.getEmail();

            ShowSucessAlert(details);

            // Clear fields after submission
            name_textfield.clear();
            age_textfield.clear();
            email_textfield.clear();

        } catch (Exception e) {
            showErrorAlert("An unexpected error occurred: " + e.getMessage());
        }
    }
   
   public void showErrorAlert(String message){
        showAlerts(message, Alert.AlertType.ERROR, "Oops!");
   }
   
   public void ShowSucessAlert(String message){
        showAlerts(message, Alert.AlertType.INFORMATION, "Success");
   }
public void showAlerts(String message, Alert.AlertType alertType,String headerText)
{
    Alert alert = new Alert(alertType);
        alert.setTitle("Message");
        alert.setContentText(message);
        alert.setHeaderText(headerText);
        alert.showAndWait();
}
}

