module com.example.imtired {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.imtired to javafx.fxml;
    exports com.example.imtired;
    exports com.example.imtired.home;
    opens com.example.imtired.home to javafx.fxml;
}