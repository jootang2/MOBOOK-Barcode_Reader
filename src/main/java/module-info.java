module com.launcher.mobookjfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.fasterxml.jackson.databind;

    opens com.launcher.mobookjfx to javafx.fxml;
    exports com.launcher.mobookjfx;
}