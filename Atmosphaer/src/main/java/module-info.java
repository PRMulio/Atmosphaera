module com.practicas.zephyrosfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.practicas.atmosphaer to javafx.fxml;
    exports com.practicas.atmosphaer;
    requires org.json;
}
