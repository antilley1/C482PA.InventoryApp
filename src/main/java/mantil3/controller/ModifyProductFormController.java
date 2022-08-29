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
 * Controller for modify-product-form-view
 * @see MainFormController
 * @see AddProductFormController
 */
public class ModifyProductFormController implements Initializable {

    @FXML private Label message;
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

    @FXML private TextField modifyStock;
    @FXML private TextField modifyCost;
    @FXML private TextField modifyMax;
    @FXML private TextField modifyMin;
    @FXML private TextField modifyName;

    private Product tempProduct;
    private int productIndex;

    /**
     * Called when the cancel button is clicked<br>
     * The SelectedProduct object is set back to original attributes
     * and the temporary Product object is cleared<br>
     * @param actionEvent   the button clicked
     * @throws IOException  thrown when navigating screens
     */
    @FXML private void toMainForm(ActionEvent actionEvent) throws IOException {
        // Revert product back to original state
        revertTempProduct();
        Utils.navigateScreens("/mantil3/view/main-form-view.fxml", actionEvent, "Inventory System");
    }

    /**
     * Sets the temporary product object to null<br>
     */
    private void revertTempProduct() {
        tempProduct = null;
    }

    /**
     * Called when the Save button is clicked<br>
     * Reverts the Temporary product object to null<br>
     * Set the attributes of the Selected Product to the values in the text fields<br>
     * Sets the associated parts to the parts in the tableview<br>
     * @param actionEvent   the button clicked
     * @throws IOException  thrown when navigating screens
     */
    @FXML private void saveProduct(ActionEvent actionEvent) throws IOException {
        tempProduct.setName(modifyName.getText());
        tempProduct.setStock(Integer.parseInt(modifyStock.getText()));
        tempProduct.setPrice(Double.parseDouble(modifyCost.getText()));
        tempProduct.setMin(Integer.parseInt(modifyMin.getText()));
        tempProduct.setMax(Integer.parseInt(modifyMax.getText()));
        Inventory.updateProduct(productIndex, tempProduct);
        Inventory.SelectedProduct.getAssociatedParts().clear();
        for(int i = 0; i < tempProduct.getAssociatedParts().size(); i++){
            Inventory.SelectedProduct.addAssociatedPart(tempProduct.getAssociatedParts().get(i));
        }
        revertTempProduct();
        Utils.navigateScreens("/mantil3/view/main-form-view.fxml", actionEvent, "Inventory System");
    }

    /**
     * Called when the Add button is clicked<br>
     * Takes the selected item from the AllParts Tableview and adds it to the
     * other Tableview<br>
     * Updates the SelectedProduct associated parts attribute<br>
     */
    @FXML private void addAssociatedPart() {
        Part associatedPart = PartTableView.getSelectionModel().getSelectedItem();
        message.setText("Part \"" + associatedPart.getName() + "\" added.");
        tempProduct.addAssociatedPart(associatedPart);
        Utils.populateAssociatedPartsTable(tempProduct, ProdPartTableView, prodPartIDCol, prodPartNameCol, prodPartInvCol, prodPartCostCol);
    }

    /**
     * Called when the remove part button is clicked<br>
     * Removes the selected part from the associated parts tableview
     * and from the SelectedProduct associated parts list<br>
     */
    @FXML private void removeAssociatedPart() {
        Part partToRemove = ProdPartTableView.getSelectionModel().getSelectedItem();
        String name = partToRemove.getName();
        boolean success = tempProduct.removeAssociatedPart(partToRemove);
        if(success) {
            message.setText("Part \"" + name + "\" removed.");
        }
        Utils.populateAssociatedPartsTable(tempProduct, ProdPartTableView, prodPartIDCol, prodPartNameCol, prodPartInvCol, prodPartCostCol);
        Utils.populatePartsTable(PartTableView, partIDCol, partNameCol, partInvCol, partCostCol);
    }

    /**
     * Initializes the controller
     * Populates the Parts table with all parts<br>
     * Populates the Associated Parts with the selected products associated parts<br>
     * Creates a temporary product to hold initial state of selected product<br>
     * Populates input fields with initial selected product attributes<br>
     * @param url               The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle    The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        Utils.populatePartsTable(PartTableView, partIDCol, partNameCol, partInvCol, partCostCol);
        tempProduct = new Product(Inventory.SelectedProduct.getId(), Inventory.SelectedProduct.getName(), Inventory.SelectedProduct.getPrice(), Inventory.SelectedProduct.getStock(), Inventory.SelectedProduct.getMin(), Inventory.SelectedProduct.getMax());
        for (int i = 0; i < Inventory.SelectedProduct.getAssociatedParts().size(); i++) {
            tempProduct.addAssociatedPart(Inventory.SelectedProduct.getAssociatedParts().get(i));
        }

        modifyName.setText(tempProduct.getName());
        modifyStock.setText(String.valueOf(tempProduct.getStock()));
        modifyCost.setText(String.valueOf(tempProduct.getPrice()));
        modifyMin.setText(String.valueOf(tempProduct.getMin()));
        modifyMax.setText(String.valueOf(tempProduct.getMax()));
        productIndex = Inventory.getAllProducts().indexOf(Inventory.SelectedProduct);
        Utils.populateAssociatedPartsTable(tempProduct, ProdPartTableView, prodPartIDCol, prodPartNameCol, prodPartInvCol, prodPartCostCol);

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
}
