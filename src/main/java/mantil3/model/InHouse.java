package mantil3.model;

/**
 * Class for parts made in-house<br>
 * Child of the Part class<br>
 * @see Part
 */
public class InHouse extends Part{

    private int machineID;

    /**
     * Constructor for InHouse Parts<br>
     * Inherits attributes from the Parts class<br>
     * @param id            the Part ID
     * @param name          the Part Name
     * @param price         the Part Price
     * @param stock         the Part Stock
     * @param min           the Part Minimum Stock
     * @param max           the Part Maximum Stock
     * @param machineID     the InHouse Part MachineID
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /**
     * setter for the Machine ID
     * @param machineID the desired Machine ID
     */
    public void setMachineID(Integer machineID) { this.machineID = machineID; }

    /**
     * getter for the Machine ID
     * @return  the InHouse Machine ID
     */
    public int getMachineID() { return machineID; }
}
