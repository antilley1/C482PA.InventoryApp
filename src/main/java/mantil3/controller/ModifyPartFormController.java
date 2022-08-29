package mantil3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import mantil3.model.InHouse;
import mantil3.model.Inventory;
import mantil3.model.Outsourced;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for modify-part-form-view
 * @see AddPartFormController
 * @see MainFormController
 */
public class ModifyPartFormController implements Initializable {

    @FXML private Label errorMessage;
    @FXML private TextField partName;
    @FXML private TextField partStock;
    @FXML private TextField partCost;
    @FXML private TextField partMax;
    @FXML private TextField partMin;
    @FXML private TextField partMachineID;
    @FXML private Label radioLabel;
    @FXML private RadioButton inHouseRadio;
    @FXML private RadioButton outSourcedRadio;

    private int inventoryIndex;

    /**
     * Called when cancel button is clicked<br>
     * Returns to main screen<br>
     * @param actionEvent   the button click
     * @throws IOException  thrown when navigating screens
     * @see Utils#navigateScreens(String, ActionEvent, String)
     */
    @FXML private void toMainForm(ActionEvent actionEvent) throws IOException {
        Utils.navigateScreens("/mantil3/view/main-form-view.fxml", actionEvent, "Inventory System");
    }

    /**
     * Changes fields for In House Parts<br>
     * Sets appropriate radio to true
     */
    @FXML private void inHouseSetup() {
        radioLabel.setText("Machine ID");
        inHouseRadio.setSelected(true);
        outSourcedRadio.setSelected(false);
    }

    /**
     * Changes fields for Outsourced Parts<br>
     * Sets appropriate radio to true
     */
    @FXML private void outSourcedSetup() {
        radioLabel.setText("Company Name");
        outSourcedRadio.setSelected(true);
        inHouseRadio.setSelected(false);
    }

    /**
     * Called when the save button is clicked<br>
     * Sets the Part attributes to the values in the associated fields<br>
     * Returns to main screen
     * @param actionEvent   the button click
     * @throws IOException  thrown when navigating screens
     */
    @FXML private void savePart(ActionEvent actionEvent) throws IOException {
        boolean stockError = false;
        int id = Inventory.SelectedPart.getId();
        String name = partName.getText();
        int stock = Integer.parseInt(partStock.getText());
        double price = Double.parseDouble(partCost.getText());
        int min = Integer.parseInt(partMin.getText());
        int max = Integer.parseInt(partMax.getText());
        if(stock > max || stock < min){
            stockError = true;
        }
        if (!stockError){
            if (inHouseRadio.isSelected()) {
                int machineID = Integer.parseInt(partMachineID.getText());
                InHouse updatedPart = new InHouse(id, name, price, stock, min, max, machineID);
                Inventory.updatePart(inventoryIndex, updatedPart);
            }
            else if (outSourcedRadio.isSelected()) {
                String company = partMachineID.getText();
                Outsourced updatedPart = new Outsourced(id, name, price, stock, min, max, company);
                Inventory.updatePart(inventoryIndex, updatedPart);
            }
            toMainForm(actionEvent);
        }
        else {
            errorMessage.setText("Check inv, max, and min.");
        }
    }

    /**
     * Initializes ModifyPartFormController<br>
     * Populates attribute fields with appropriate values<br>
     * Sets InHouse or Outsourced based on the SelectedPart
     * @param url               The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle    The resources used to localize the root object, or null if the root object was not localized.
     * @see Inventory#SelectedPart
     * @see ModifyPartFormController#inHouseSetup()
     * @see ModifyPartFormController#outSourcedSetup()
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        partName.setText(Inventory.SelectedPart.getName());
        partStock.setText(String.valueOf(Inventory.SelectedPart.getStock()));
        partCost.setText(String.valueOf(Inventory.SelectedPart.getPrice()));
        partMax.setText(String.valueOf(Inventory.SelectedPart.getMax()));
        partMin.setText(String.valueOf(Inventory.SelectedPart.getMin()));
        if(Inventory.SelectedPart instanceof InHouse) {
            partMachineID.setText(String.valueOf(((InHouse) Inventory.SelectedPart).getMachineID()));
            inHouseSetup();
        }
        else if(Inventory.SelectedPart instanceof Outsourced) {
            partMachineID.setText(((Outsourced) Inventory.SelectedPart).getCompanyName());
            outSourcedSetup();
        }
        inventoryIndex = Inventory.getAllParts().indexOf(Inventory.SelectedPart);
    }
}
