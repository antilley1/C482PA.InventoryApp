package mantil3.model;

/**
 * Class for parts outsourced to other companies<br>
 * Child of the Part class<br>
 * @see Part
 */
public class Outsourced extends Part{

    private String companyName;


    /**
     * Constructor for Outsource Parts<br>
     * Inherits attributes from the Parts class<br>
     * @param id            the Part ID
     * @param name          the Part Name
     * @param price         the Part Price
     * @param stock         the Part Stock
     * @param min           the Part Minimum Stock
     * @param max           the Part Maximum Stock
     * @param companyName   the Name of the Company the part is outsourced to
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * setter for the Company Name
     * @param companyName   the Name of the company who makes the part
     */
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    /**
     * getter for the Company Name
     * @return  the Name of the Company who makes the part
     */
    public String getCompanyName() { return companyName; }
}
