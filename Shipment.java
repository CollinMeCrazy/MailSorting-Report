/**
 * Class Shipment used to store variables in Class MailCoach
 *
 * @author Collins Ramsey
 * @version 10/25/22 @ 4:56 pm
 */
public class Shipment
{
    // instance variables
    private String destination;
    private double weight;
    private int volume;
    private int id;

    /**
     * Standard Constructor for objects of class Shipment
     */
    public Shipment()
    {
        this.destination = "";
        this.weight = 0.0;
        this.volume = 0;
        this.id = 0;
    }
    
    /**
     * 2nd Constructor for objects of class Shipment that takes parameters
     * 
     * @param int
     * @param String
     * @param double
     */
    public Shipment(int id, String destination, double weight, int volume) {
        this.destination = destination;
        this.weight = weight;
        this.volume = volume;
        this.id = id;
    }
    
    /**
     * Method getDestination returns instance variable of String destination
     *
     * @param  none
     * @return String
     */
    public String getDestination()
    {
        return this.destination;
    }
    
    /**
     * Method getWeight returns instance variable of double weight
     *
     * @param  none
     * @return double
     */
    public double getWeight()
    {
        return this.weight;
    }
    
    /**
     * Method getVolume returns instance variable of int volume
     *
     * @param  none
     * @return int
     */
    public int getVolume()
    {
        return this.volume;
    }
    
    /**
     * Method getId returns instance variable of int id
     *
     * @param  none
     * @return int
     */
    public int getId()
    {
        return this.id;
    }
}
