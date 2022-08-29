package mantil3.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mantil3.model.Inventory;
import mantil3.model.Part;
import mantil3.model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for main-form-view
 * FUTURE ENHANCEMENT: Add functionality to open a new view
 * with info on a selected part or product
 */
public class MainFormController implements Initializable {

    @FXML private TableView<Part> PartTableView;
    @FXML private TableColumn<Part, Integer> partIDCol;
    @FXML private TableColumn<Part, String> partNameCol;
    @FXML private TableColumn<Part, Integer> partInvCol;
    @FXML private TableColumn<Part, Double> partCostCol;

    @FXML private TableView<Product> ProdTableView;
    @FXML private TableColumn<Product, Integer> prodIDCol;
    @FXML private TableColumn<Product, String> prodNameCol;
    @FXML private TableColumn<Product, Integer> prodInvCol;
    @FXML private TableColumn<Product, Double> prodCostCol;

    @FXML private TextField partSearchText;
    @FXML private TextField prodSearchText;
    @FXML private Label partMessage;
    @FXML private Label prodMessage;

    /**
     * Called when the Add Part button is clicked<br>
     * Navigates to the Add Part Screen<br>
     * @param actionEvent   the button click
     * @throws IOException  thrown when navigating screens
     * @see Utils#navigateScreens(String, ActionEvent, String)
     */
    @FXML private void toAddPart(ActionEvent actionEvent) throws IOException {
        Utils.navigateScreens("/mantil3/view/add-part-form-view.fxml", actionEvent, "Add Part");
    }

    /**
     * Called when the Modify Part button is clicked<br>
     * Navigates to the Modify Part Screen<br>
     * @param actionEvent   the button click
     * @throws IOException  thrown when navigating screens
     * @see Utils#navigateScreens(String, ActionEvent, String)
     */
    @FXML private void toModifyPart(ActionEvent actionEvent) throws IOException {
        Utils.navigateScreens("/mantil3/view/modify-part-form-view.fxml", actionEvent, "Modify Part");
    }

    /**
     * Called when the Add Product button is clicked<br>
     * Navigates to the Add Product Screen<br>
     * @param actionEvent   the button click
     * @throws IOException  thrown when navigating screens
     * @see Utils#navigateScreens(String, ActionEvent, String)
     */
    @FXML private void toAddProduct(ActionEvent actionEvent) throws IOException {
        Utils.navigateScreens("/mantil3/view/add-product-form-view.fxml", actionEvent, "Add Product");
    }

    /**
     * Called when the Modify Product button is clicked<br>
     * Navigates to the Modify Part Screen<br>
     * @param actionEvent   the button click
     * @throws IOException  thrown when navigating screens
     * @see Utils#navigateScreens(String, ActionEvent, String)
     */
    @FXML private void toModifyProduct(ActionEvent actionEvent) throws IOException {
        Utils.navigateScreens("/mantil3/view/modify-product-form-view.fxml", actionEvent, "Modify Product");
    }

    /**
     * Closes Application<br>
     * Called when the Exit button is clicked<br>
     */
    @FXML private void exitApp() {
        System.exit(0);
    }

    /**
     * Called when the Delete Part button is clicked<br>
     * Deletes the Selected Part from the allParts list<br>
     * Updates the Part Tableview<br>
     * @see Inventory#SelectedPart
     * @see Inventory#deletePart(Part)
     * @see Utils#populatePartsTable(TableView, TableColumn, TableColumn, TableColumn, TableColumn)
     */
    @FXML private void deletePart() {
        String name = Inventory.SelectedPart.getName();
        boolean success = Inventory.deletePart(Inventory.SelectedPart);
        if(success){
            partMessage.setText("Part \"" + name + "\" deleted.");
        }
        Utils.populatePartsTable(PartTableView, partIDCol, partNameCol, partInvCol, partCostCol);
    }

    /**
     * Called when the Delete Product button is clicked<br>
     * Deletes the Selected Product from the allProducts list<br>
     * Updates the Product Tableview<br>
     * @see Inventory#SelectedProduct
     * @see Inventory#deleteProduct(Product)
     * @see Utils#populateProdsTable(TableView, TableColumn, TableColumn, TableColumn, TableColumn)
     */
    @FXML private void deleteProd() {
        if(Inventory.SelectedProduct.getAssociatedParts().isEmpty()){
            String name = Inventory.SelectedProduct.getName();
            boolean success = Inventory.deleteProduct(Inventory.SelectedProduct);
            if(success){
                prodMessage.setText("Product \"" + name + "\" deleted.");
            }
            Utils.populateProdsTable(ProdTableView, prodIDCol, prodNameCol, prodInvCol, prodCostCol);

        }
        else{
            prodMessage.setText("Has assoc. parts. Cannot delete.");
        }
    }

    /**
     * Called anytime a key is input into the search parts field<br>
     * Updates the filteredParts list with matches<br>
     * Matches include names or ID<br>
     * Updates the Parts Tableview<br>
     * @see Inventory#clearFilteredParts()
     * @see Inventory#getFilteredParts(String)
     */
    @FXML private void searchParts() {
        partMessage.setText("");
        Inventory.clearFilteredParts();

        ObservableList<Part> filteredParts = Inventory.getFilteredParts(partSearchText.getText());
        PartTableView.setItems(filteredParts);

        if (filteredParts.isEmpty()){
            //error message
            partMessage.setText("No Match");
        }
    }

    /**
     * Called anytime a key is input into the search products field<br>
     * Updates the filteredProds list with matches<br>
     * Matches include names of ID<br>
     * Updates the Products Tableview<br>
     * @see Inventory#clearFilteredProducts()
     * @see Inventory#getFilteredProducts(String)
     */
    @FXML private void searchProds() {
        prodMessage.setText("");
        Inventory.clearFilteredProducts();

        ObservableList<Product> filteredProds = Inventory.getFilteredProducts(prodSearchText.getText());
        ProdTableView.setItems(filteredProds);

        if (filteredProds.isEmpty()){
            //error message
            prodMessage.setText("No Match");
        }
    }

    /**
     * Called anytime a product is selected in the Product Tableview<br>
     * Sets the SelectedProduct to the product selected<br>
     * @see Inventory#SelectedProduct
     */
    @FXML private void declareSelectedProduct() {
        Inventory.SelectedProduct = ProdTableView.getSelectionModel().getSelectedItem();
    }

    /**
     * Called anytime a part is selected in the Part Tableview<br>
     * Sets the SelectedPart to the part selected<br>
     * @see Inventory#SelectedPart
     */
    @FXML private void declareSelectedPart() {
        Inventory.SelectedPart = PartTableView.getSelectionModel().getSelectedItem();
    }

    /**
     * Initializes MainFormController<br>
     * Populates the Parts and Product Tableviews<br>
     * @param url               The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle    The resources used to localize the root object, or null if the root object was not localized.
     * @see Utils#populatePartsTable(TableView, TableColumn, TableColumn, TableColumn, TableColumn)
     * @see Utils#populateProdsTable(TableView, TableColumn, TableColumn, TableColumn, TableColumn)
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        Utils.populatePartsTable(PartTableView, partIDCol, partNameCol, partInvCol, partCostCol);
        Utils.populateProdsTable(ProdTableView, prodIDCol, prodNameCol, prodInvCol, prodCostCol);
    }

}
