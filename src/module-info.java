module com.example.lp2tentativa1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.management;
    requires jdk.management;


    exports edu.ufp.inf.project;
    opens edu.ufp.inf.project to javafx.fxml;
}