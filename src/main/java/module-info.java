module com.roadest.roadproredact {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.ooxml;


    opens com.roadest.roadproredact to javafx.fxml;
    exports com.roadest.roadproredact;
    exports com.roadest.roadproredact.borders;
    opens com.roadest.roadproredact.borders to javafx.fxml;
}