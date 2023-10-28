module com.launcher.mobookjfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.launcher.mobookjfx to javafx.fxml;
    exports com.launcher.mobookjfx;
}