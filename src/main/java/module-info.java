module com.proyectofinal.atmosphaera {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires eu.hansolo.tilesfx;
    requires org.json;

    opens com.proyectofinal.atmosphaera to javafx.fxml;
    exports com.proyectofinal.atmosphaera;
}