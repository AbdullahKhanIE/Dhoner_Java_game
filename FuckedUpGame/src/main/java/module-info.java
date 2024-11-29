module com.example.gameFucked {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.gameFucked to javafx.fxml;
    exports com.example.gameFucked;
}