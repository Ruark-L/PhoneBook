module fruitvendorfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens app to javafx.fxml;
    opens controller to javafx.fxml;
    opens model to javafx.base, javafx.fxml;
//    opens fxml to javafx.fxml;


    exports app;
    exports controller;
    exports model;
}
