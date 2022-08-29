package mantil3.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mantil3.model.Inventory;
import mantil3.model.Part;
import mantil3.model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller for the AddProductForm View<br>
 * @see MainFormController
 * @see AddPartFormController
 */
public class AddProductFormController implements Initializable {

    @FXML private Label message;
    @FXML private TextField prodStock;
    @FXML private TextField prodPrice;
    @FXML private TextField prodMax;
    @FXML private TextField prodMin;
    @FXML private TextField prodName;
    @FXML private TextField partSearchText;

    @FXML private TableView<Part> PartTableView;
    @FXML private TableColumn<Part, Integer> partIDCol;
    @FXML private TableColumn<Part, String> partNameCol;
    @FXML private TableColumn<Part, Integer> partInvCol;
    @FXML private TableColumn<Part, Double> partCostCol;

    @FXML private TableView<Part> ProdPartTableView;
    @FXML private TableColumn<Part, Integer> prodPartIDCol;
    @FXML private TableColumn<Part, String> prodPartNameCol;
    @FXML private TableColumn<Part, Integer> prodPartInvCol;
    @FXML private TableColumn<Part, Double> prodPartCostCol;

    /**
     * Called when the cancel button is clicked<br>
     * Navigates to Main Form View without saving a new product<br>
     * @param actionEvent   the button click action
     * @throws IOException  exception thrown when navigateScreens is called
     * @see Utils#navigateScreens(String, ActionEvent, String)
     */
    @FXML private void toMainForm(ActionEvent actionEvent) throws IOException {
        Utils.navigateScreens("/mantil3/view/main-form-view.fxml", actionEvent, "Inventory System");
    }

    /**
     * Called when the Save button is clicked<br>
     * Creates a new product with the data from the attribute fields<br>
     * Navigates to the Main Form<br>
     * @param actionEvent   the button click action
     * @throws IOException  exception thrown when navigateScreens is called
     * @see Utils#navigateScreens(String, ActionEvent, String)
     */
    @FXML private void saveProduct(ActionEvent actionEvent) throws IOException {
        int id = Inventory.getAllProducts().get(Inventory.getAllProducts().size() - 1).getId() + 1;
        Inventory.SelectedProduct.setId(id);
        Inventory.SelectedProduct.setName(prodName.getText());
        Inventory.SelectedProduct.setStock(Integer.parseInt(prodStock.getText()));
        Inventory.SelectedProduct.setPrice(Double.parseDouble(prodPrice.getText()));
        Inventory.SelectedProduct.setMin(Integer.parseInt(prodMin.getText()));
        Inventory.SelectedProduct.setMax(Integer.parseInt(prodMax.getText()));
        Inventory.addProduct(Inventory.SelectedProduct);
        Utils.navigateScreens("/mantil3/view/main-form-view.fxml", actionEvent, "Inventory System");
    }

    /**
     * Called when the add part button is clicked<br>
     * It takes the selected item in the parts list and adds it to the associated parts table<br>
     * @see Inventory#SelectedProduct
     * @see Utils#populateAssociatedPartsTable(Product, TableView, TableColumn, TableColumn, TableColumn, TableColumn)
     */
    @FXML private void addPart(){
        Part associatedPart = PartTableView.getSelectionModel().getSelectedItem();
        message.setText("Part \"" + associatedPart.getName() + "\" added.");
        Inventory.SelectedProduct.addAssociatedPart(associatedPart);
        Utils.populateAssociatedPartsTable(Inventory.SelectedProduct, ProdPartTableView, prodPartIDCol, prodPartNameCol, prodPartInvCol, prodPartCostCol);
    }

    /**
     * Called when the remove button is clicked<br>
     * It takes the selected item in the associated parts list and removes it<br>
     * @see Inventory#SelectedProduct
     * @see Utils#populatePartsTable(TableView, TableColumn, TableColumn, TableColumn, TableColumn)
     * @see Utils#populateAssociatedPartsTable(Product, TableView, TableColumn, TableColumn, TableColumn, TableColumn)
     */
    @FXML private void removeAssociatedPart() {
        Part partToRemove = ProdPartTableView.getSelectionModel().getSelectedItem();
        message.setText("Part \"" + partToRemove.getName() + "\" removed.");
        Inventory.SelectedProduct.removeAssociatedPart(partToRemove);
        Utils.populateAssociatedPartsTable(Inventory.SelectedProduct, ProdPartTableView, prodPartIDCol, prodPartNameCol, prodPartInvCol, prodPartCostCol);
        Utils.populatePartsTable(PartTableView, partIDCol, partNameCol, partInvCol, partCostCol);
    }

   @FXML private void searchParts() {
        message.setText("");
        Inventory.clearFilteredParts();

        ObservableList<Part> filteredParts = Inventory.getFilteredParts(partSearchText.getText());
        PartTableView.setItems(filteredParts);

        if (filteredParts.isEmpty()){
            //error message
            message.setText("No Match");
        }
    }

    /**
     * Initializes AddProductFormController<br>
     * Populates TableView with parts list<br>
     * @param url               The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle    The resources used to localize the root object, or null if the root object was not localized.
     * @see Inventory#SelectedProduct
     * @see Utils#populatePartsTable(TableView, TableColumn, TableColumn, TableColumn, TableColumn)
     * @see Utils#populateAssociatedPartsTable(Product, TableView, TableColumn, TableColumn, TableColumn, TableColumn)
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        Utils.populatePartsTable(PartTableView, partIDCol, partNameCol, partInvCol, partCostCol);
        Inventory.SelectedProduct = new Product(0, "name", 0, 0, 0, 0);
        Utils.populateAssociatedPartsTable(Inventory.SelectedProduct, ProdPartTableView, prodPartIDCol, prodPartNameCol, prodPartInvCol, prodPartCostCol);
    }


}
