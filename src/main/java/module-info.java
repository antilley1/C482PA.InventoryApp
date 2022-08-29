module mantil3.c482pa {
    requires javafx.controls;
    requires javafx.fxml;


//    opens mantil3.controller to javafx.fxml;
//    exports mantil3.controller;


    opens mantil3.model to javafx.fxml;
    exports mantil3.model;
    exports mantil3;
    opens mantil3 to javafx.fxml;
    exports mantil3.controller;
    opens mantil3.controller to javafx.fxml;


}