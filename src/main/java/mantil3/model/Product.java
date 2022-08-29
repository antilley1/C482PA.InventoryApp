package mantil3.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class for Products<br>
 * Products can be made up of many, one, or no Parts
 */
public class Product {

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Product Constructor
     * @param id    product id
     * @param name  product name
     * @param price product price
     * @param stock product stock
     * @param min   product minimum stock
     * @param max   product maximum stock
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * getId is the getter for the ID attribute
     * @return ID of this Product
     */
    public int getId() { return id; }
    /**
     * setId is the setter for the ID attribute
     * @param id    the new ID
     */
    public void setId(int id) { this.id = id; }

    /**
     * getName is the getter for the Name attribute
     * @return Name of this product
     */
    public String getName() { return name; }
    /**
     * setName is the setter for the Name attribute
     * @param name the new Name
     */
    public void setName(String name) { this.name = name; }

    /**
     * getPrice is the getter for the Price attribute
     * @return Price of this product
     */
    public double getPrice() { return price; }
    /**
     * setPrice is the setter for the Price attribute
     * @param price the new Price
     */
    public void setPrice(double price) { this.price = price; }

    /**
     * getStock is the getter for the Stock attribute
     * @return Stock of this product
     */
    public int getStock() { return stock; }
    /**
     * setStock is the setter for the Stock attribute
     * @param stock the new Stock
     */
    public void setStock(int stock) { this.stock = stock; }

    /**
     * getMin is the getter for the Minimum attribute
     * @return Minimum stock of this product
     */
    public int getMin() { return min; }
    /**
     * setName is the setter for the Minimum attribute
     * @param min the new Minimum stock
     */
    public void setMin(int min) { this.min = min; }

    /**
     * getMax is the getter for the Maximum attribute
     * @return Maximum stock of this product
     */
    public int getMax() { return max; }
    /**
     * setMax is the setter for the Maximum attribute
     * @param max the new Maximum stock
     */
    public void setMax(int max) { this.max = max; }

    /**
     * addAssociatedPart adds a Part object to this Product's
     * associatedParts list
     * @param part the Part object to be added
     */
    public void addAssociatedPart(Part part) { this.associatedParts.add(part); }
    /**
     * removeAssociatedPart removes a Part object from this
     * Product's associatedParts list
     * @param part the Part object to be removed
     * @return <code>true</code> if the Part object was removed
     */
    public boolean removeAssociatedPart(Part part) {
        if (this.associatedParts.contains(part)){
            this.associatedParts.remove(part);
            return true;
        }
        return false;
    }
    /**
     * getAssociatedParts is the getter for the
     * associatedParts list attribute
     * @return the associatedPartsList
     */
    public ObservableList<Part> getAssociatedParts () { return this.associatedParts; }

}
