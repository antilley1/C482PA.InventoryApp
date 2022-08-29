package mantil3.model;

/**
 * Abstract class for Parts<br>
 * Parent class for InHouse and Outsourced<br>
 * @see InHouse
 * @see Outsourced
 */
public abstract class Part {

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Constructor for Part class<br>
     * LOGICAL ERROR: For some reason this doesn't like the arguments for all the attributes
     * but the UML diagram shows all fields used as arguments
     * happened once I added newPart method in Inventory Class<br>
     * SOLVED: this constructor is not supposed to be called because this is an abstract class
     * @param id        Part ID
     * @param name      Part Name
     * @param price     Part Price
     * @param stock     Part Stock
     * @param min       Part Minimum Stock
     * @param max       Part Maximum Stock
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }


    /**
     * getter for the ID attribute
     * @return  the Part ID
     */
    public int getId() { return id; }

    /**
     * setter for the ID attribute
     * @param id    the desired ID
     */
    public void setId(int id) { this.id = id; }

    /**
     * getter for the Name attribute
     * @return  the Part Name
     */
    public String getName() { return name; }

    /**
     * setter for the Name attribute
     * @param name  the desired Name
     */
    public void setName(String name) { this.name = name; }

    /**
     * getter for the Price attribute
     * @return  the Part Price
     */
    public double getPrice() { return price; }

    /**
     * setter for the Price attribute
     * @param price the desired Price
     */
    public void setPrice(double price) { this.price = price; }

    /**
     * getter for the Stock attribute
     * @return  the Part Stock
     */
    public int getStock() { return stock; }

    /**
     * setter for the Stock attribute
     * @param stock the desired Stock
     */
    public void setStock(int stock) { this.stock = stock; }

    /**
     * getter for the Min attribute
     * @return the Part Min
     */
    public int getMin() { return min; }

    /**
     * setter for the Min attribute
     * @param min   the desired Min
     */
    public void setMin(int min) { this.min = min; }

    /**
     * getter for the Max attribute
     * @return  the Part Max
     */
    public int getMax() { return max; }

    /**
     * setter for the Max attribute
     * @param max   the desired Max
     */
    public void setMax(int max) { this.max = max; }

}
