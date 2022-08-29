package mantil3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mantil3.model.Inventory;
import mantil3.model.Part;
import mantil3.model.Product;
import java.io.IOException;

/**
 * The Utils class is a library of functions that are commonly used across the Inventory App
 */
public final class Utils {

    /**
     * The Utils constructor is made private so Utils cannot be instantiated
     */
    private Utils() {
        //empty
    }

    /**
     * navigateScreens is a helper function to that can be called anytime the screen is to be changed.
     * @param path          the screen to navigate to
     * @param actionEvent   the actionEvent passed on from the button click
     * @param stageTitle    the stage title of the screen to navigate to
     * @throws IOException  exception thrown when the screen changes
     */
    public static void navigateScreens (String path, ActionEvent actionEvent, String stageTitle) throws IOException {
        Parent root = FXMLLoader.load(Utils.class.getResource(path));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle(stageTitle);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * populatePartsTable is a helper function called on any screen with a Parts TableView
     * It populates the Tableview with the entire list of parts
     * @param Table     The TableView object
     * @param IDCol     The column for the part ID
     * @param NameCol   The column for the part name
     * @param InvCol    The column for the part inventory
     * @param CostCol   The column for the part cost
     */
    public static void populatePartsTable(
            TableView<Part> Table, TableColumn<Part, Integer> IDCol, TableColumn<Part, String> NameCol, TableColumn<Part, Integer> InvCol, TableColumn<Part, Double> CostCol
    ) {
        Table.setItems(Inventory.getAllParts());
        IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        InvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        CostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * populateProdsTable is a helper function called on any screen with a Product TableView
     * It populates the Tableview with the entire list of products
     * @param Table     The TableView object
     * @param IDCol     The column for the product ID
     * @param NameCol   The column for the product name
     * @param InvCol    The column for the product inventory
     * @param CostCol   The column for the product cost
     */
    public static void populateProdsTable(
            TableView<Product> Table, TableColumn<Product, Integer> IDCol, TableColumn<Product, String> NameCol, TableColumn<Product, Integer> InvCol, TableColumn<Product, Double> CostCol
    ) {
        Table.setItems(Inventory.getAllProducts());
        IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        InvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        CostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * populateAssociatedPartsTable is a helper function called on the Modify Product Screen
     * It populates the associated parts TableView with the list of associated parts
     * @param Table     The Tableview object
     * @param IDCol     the column for the associated part ID
     * @param NameCol   the column for the associated part Name
     * @param InvCol    the column for the associated part inventory
     * @param CostCol   the column for the associated part cost
     * @see ModifyProductFormController
     */
    public static void populateAssociatedPartsTable(
            Product product, TableView<Part> Table, TableColumn<Part, Integer> IDCol, TableColumn<Part, String> NameCol, TableColumn<Part, Integer> InvCol, TableColumn<Part, Double> CostCol
    ){
        Table.setItems(product.getAssociatedParts());
        IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        InvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        CostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
