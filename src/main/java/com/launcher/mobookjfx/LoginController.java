package com.launcher.mobookjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField idField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void login(ActionEvent event) throws IOException {
        String id = idField.getText();
        String password = passwordField.getText();

        if (id.equals("admin") && password.equals("mobility42")) {
            showAlert(Alert.AlertType.INFORMATION, "로그인 성공", "환영합니다!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root = loader.load();

            // 메인 컨트롤러에 데이터 전달 (선택 사항)
            MainController mainController = loader.getController();
            mainController.initData(idField.getText());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
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