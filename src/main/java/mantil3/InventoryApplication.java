package mantil3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import mantil3.model.InHouse;
import mantil3.model.Inventory;
import mantil3.model.Outsourced;
import mantil3.model.Product;


import java.io.IOException;

/**
 * Main Class <br>
 * On start loads Main form view
 */
public class InventoryApplication extends Application {
    /**
     * Runs on application start up<br>
     * @param stage         the main stage
     * @throws IOException  thrown when loading stage
     */
    @Override public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("view/main-form-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    // JavaDocs folder located at C482PA/JavaDocs

    /**
     * Main<br>
     * @param args The command line parameters.
     */
    public static void main(String[] args) {

        InHouse wheels = new InHouse(1, "wheels", 3.99, 1, 0, 10, 111);
        Outsourced handlebars = new Outsourced(3, "handlebars", 3.99, 1, 0, 10, "Company");

        Product bike1 = new Product(1, "bike1", 8.99, 1, 0, 10);
        Product bike2 = new Product(2, "bike2", 8.99, 1, 0, 10);
        Product bike3 = new Product(3, "bike3", 8.99, 1, 0, 10);

        Inventory.addPart(wheels);
        Inventory.addPart(handlebars);
        Inventory.addProduct(bike1);
        Inventory.addProduct(bike2);
        Inventory.addProduct(bike3);


        launch();
    }

}