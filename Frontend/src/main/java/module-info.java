module com.harmonia {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires spring.web;

    opens com.harmonia to javafx.fxml;
    //opens com.harmonia.controller to javafx.fxml;
    exports com.harmonia;
}