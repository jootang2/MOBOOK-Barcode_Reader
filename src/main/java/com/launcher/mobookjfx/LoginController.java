package com.launcher.mobookjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField idField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void login(ActionEvent event) {
        String id = idField.getText();
        String password = passwordField.getText();

        if (id.equals("admin") && password.equals("mobility42")) {
            showAlert(Alert.AlertType.INFORMATION, "로그인 성공", "환영합니다!");
        } else {
            showAlert(Alert.AlertType.ERROR, "로그인 실패", "아이디 또는 비밀번호가 올바르지 않습니다.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}