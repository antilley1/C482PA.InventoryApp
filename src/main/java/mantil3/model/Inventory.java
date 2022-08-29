package mantil3.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Objects;

/**
 * Class for the entire Inventory of Products and Parts<br>
 * Keeps running lists for allParts and allProducts<br>
 * also has useful common methods for Parts/Products within the Inventory<br>
 * @see Part
 * @see InHouse
 * @see Outsourced
 * @see Product
 */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();
    private static ObservableList<Product> filteredProducts = FXCollections.observableArrayList();

    public static Part SelectedPart;
    public static Product SelectedProduct;

    /**
     * addPart adds a Part object to the allParts observable array list
     * @param newPart   the Part object to be added
     */
    public static void addPart(Part newPart) { allParts.add(newPart); }
    /**
     * addProduct adds a Product object to the allProducts observable array list
     * @param newProduct   the Product object to be added
     */
    public static void addProduct(Product newProduct) { allProducts.add(newProduct); }

    /**
     * returns the Part based on the ID
     * @param partID    the ID of the desired Part
     * @return          the desired Part
     */
    public static Part lookupPart(int partID) {
        for (Part part : allParts) {
            if (part.getId() == partID) {
                return part;
            }
        }
        return null;
    }

    /**
     * returns the Part based on the name
     * @param partName      the Name of the desired Part
     * @return              the desired Part
     */
    public static Part lookupPart(String partName) {
        for (Part part : allParts) {
            if (Objects.equals(part.getName(), partName)) {
                return part;
            }
        }
        return null;
    }

    /**
     * returns the Product based on the ID
     * @param productID     the ID of the desired Product
         * @return              the desired Product
     */
    public static Product lookupProduct(int productID) {
        for (Product product : allProducts) {
            if (product.getId() == productID) {
                return product;
            }
        }
        return null;
    }
    /**
     * returns the Product based on the Name
     * @param productName       the Name of the desired Product
     * @return                  the desired Product
     */
    public static Product lookupProduct(String productName) {
        for (Product product : allProducts) {
            if (Objects.equals(product.getName(), productName)) {
                return product;
            }
        }
        return null;
    }

    /**
     * Updates the Part at the passed index in the allParts list
     * with the attributes of the selectedPart passed<br>
     * @param index         index of Part to be updated in allParts list
     * @param selectedPart  Part object whose attributes will be passed
     */
    public static void updatePart(int index, Part selectedPart) {
        SelectedPart = allParts.get(index);
        SelectedPart.setName(selectedPart.getName());
        SelectedPart.setStock(selectedPart.getStock());
        SelectedPart.setPrice(selectedPart.getPrice());
        SelectedPart.setMin(selectedPart.getMin());
        SelectedPart.setMax(selectedPart.getMax());
    }

    /**
     * Updates the Product at the passed index in the allProducts list
     * with the attributes of the selectedProduct passed<br>
     * @param index             index of Product to be updated in allProducts list
     * @param selectedProduct   Product object whose attributes will be passed
     */
    public static void updateProduct(int index, Product selectedProduct) {
        SelectedProduct = allProducts.get(index);
        SelectedProduct.setName(selectedProduct.getName());
        SelectedProduct.setStock(selectedProduct.getStock());
        SelectedProduct.setPrice(selectedProduct.getPrice());
        SelectedProduct.setMin(selectedProduct.getMin());
        SelectedProduct.setMax(selectedProduct.getMax());
    }

    /**
     * deletes the Part object passed
     * @param selectedPart  the Part to be deleted
     * @return              <code>true</code> if the Part is deleted
     */
    public static boolean deletePart(Part selectedPart) {
        allParts.remove(selectedPart);

        return true;
    }

    /**
     * deletes the Product object passed
     * @param selectedProduct   the Product to be deleted
     * @return                  <code>true</code> if the Product is deleted
     */
    public static boolean deleteProduct(Product selectedProduct) {
        allProducts.remove(selectedProduct);
        return true;
    }

    /**
     * Returns the allParts list
     * @return the Observable list allParts
     */
    public static ObservableList<Part> getAllParts() { return allParts; }

    /**
     * Returns the allProducts list
     * @return the Observable list allProducts
     */
    public static ObservableList<Product> getAllProducts() { return allProducts; }

    /**
     * Returns a filtered allParts based on the String passed<br>
     * Part is passed to filteredParts list based on Name or ID match<br>
     * LOGICAL ERROR: does not return values if case doesn't match. <br>
     * FIXED: added toLowerCase method <br>
     * @param input String typed into field
     * @return      Observable list filteredParts
     */
    public static ObservableList<Part> getFilteredParts(String input) {


        for(Part part: allParts) {
            if(part.getName().toLowerCase().contains(input.toLowerCase()) || String.valueOf(part.getId()).contains(input.toLowerCase())) {
                filteredParts.add(part);
            }
        }
        return filteredParts;
    }
    /**
     * Returns a filtered allProducts based on the String passed <br>
     * Product is passed to filteredProducts list based on Name or ID match
     * @param input String typed into field
     * @return      Observable list filteredProducts
     */
    public static ObservableList<Product> getFilteredProducts(String input) {
        for(Product prod: allProducts) {
            if(prod.getName().toLowerCase().contains(input.toLowerCase()) || String.valueOf(prod.getId()).contains((input.toLowerCase()))) {
                filteredProducts.add(prod);
            }
        }
        return filteredProducts;
    }

    /**
     * Clears the filteredParts list
     */
    public static void clearFilteredParts() {
        filteredParts.clear();
    }
    /**
     * Clears the filteredProducts list
     */
    public static void clearFilteredProducts() {
        filteredProducts.clear();
    }

}

