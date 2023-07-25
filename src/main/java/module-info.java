module com.example.javagroup {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.javagroup to javafx.fxml;
    exports com.example.javagroup;
}