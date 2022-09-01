package mantil3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import mantil3.model.InHouse;
import mantil3.model.Inventory;
import mantil3.model.Outsourced;
import mantil3.model.Part;

/**
 * Controller for add-part-form-view
 * @see ModifyPartFormController
 * @see MainFormController
 */
public class AddPartFormController implements Initializable {

    @FXML private Label radioLabel;
    @FXML private RadioButton inHouseRadio;
    @FXML private RadioButton outSourcedRadio;
    @FXML private TextField partCost;
    @FXML private TextField partMax;
    @FXML private TextField partMin;
    @FXML private TextField partName;
    @FXML private TextField partStock;
    @FXML private TextField partMachineID; // also represents companyName

    @FXML private Label errorMessage;

    /**
     * Changes fields for In House Parts
     */
    @FXML private void inHouseSetup() {
        radioLabel.setText("Machine ID");
        inHouseRadio.setSelected(true);
        outSourcedRadio.setSelected(false);
    }

    /**
     * Changes fields for Outsourced Parts
     */
    @FXML private void outSourcedSetup() {
        radioLabel.setText("Company Name");
        inHouseRadio.setSelected(false);
        outSourcedRadio.setSelected(true);
    }

    /**
     * Called when the save button is clicked<br>
     * Adds a new Part object to the Inventory with the attributes
     * as designated by the fields<br>
     * Returns to main screen<br>
     * LOGICAL ERROR: Previous autogenerated ID could have duplicate IDs. Previously the ID was generated by
     * getting the size of the AllParts array and adding one. If there are 4 parts, the last part ID is 4.
     * If part 3 is deleted, the size of AllParts is now 3. if another part was added, the part ID would be 4, which is already used. <br>
     * FIXED: Now the ID is generated by getting the ID of the part at the last index of AllParts and adding 1.
     * @param event         the button click
     * @throws IOException  thrown when navigating screens
     * @see InHouse
     * @see Outsourced
     * @see Inventory#addPart(Part)
     */
    @FXML private void newPartSave(ActionEvent event) throws IOException {
        boolean stockError = false;
        int ID = 1;
        if (Inventory.getAllParts().size() != 0) {
            ID = Inventory.getAllParts().get(Inventory.getAllParts().size() - 1).getId() + 1; // this will auto-generate the ID as one more than the ID of the part at the last index
        }
        String name = partName.getText();
        double cost = Double.parseDouble(partCost.getText());
        int stock = Integer.parseInt(partStock.getText());
        int min = Integer.parseInt(partMin.getText());
        int max = Integer.parseInt(partMax.getText());

        if(stock > max || stock < min){
            stockError = true;
        }
        if(!stockError) {
            if (inHouseRadio.isSelected()) {
                int machineID = Integer.parseInt(partMachineID.getText());
                InHouse newPart = new InHouse(ID, name, cost, stock, min, max, machineID);
                Inventory.addPart(newPart);
            } else if (outSourcedRadio.isSelected()) {
                String company = partMachineID.getText();
                Outsourced newPart = new Outsourced(ID, name, cost, stock, min, max, company);
                Inventory.addPart(newPart);
            }
            toMainForm(event);
        }
        else {
            errorMessage.setText("Check inv, max, and min.");
        }
    }

    /**
     * Called when the Cancel button is clicked<br>
     * @param event         the button click
     * @throws IOException  thrown when navigating screens
     * @see Utils#navigateScreens(String, ActionEvent, String)
     */
    @FXML private void toMainForm(ActionEvent event) throws IOException{
        Utils.navigateScreens("/mantil3/view/main-form-view.fxml", event, "Inventory System");
    }

    /**
     * Initializes AddPartFromController
     * @param url               The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle    The resources used to localize the root object, or null if the root object was not localized.
     * @see AddPartFormController#inHouseSetup()
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        inHouseSetup();
    }

}

