import java.util.ArrayList;
/**
 * Class MailCoach takes Shipment objects and uses them with Class Project5
 *
 * @author Collins Ramsey
 * @version 10/25/22 @ 4:56 pm
 */
public class MailCoach
{
    // instance variables
    private String destination;
    private double weight;
    private int volume;
    private ArrayList<Shipment> cargo;

    /**
     * Standard Constructor for objects of class MailCoach
     */
    public MailCoach()
    {
        this.destination = "";
        this.weight = 0.0;
        this.volume = 0;
        this.cargo = new ArrayList<Shipment>();
    }
    
    /**
     * 2nd Constructor for objects of class MailCoach that takes one parameter
     * 
     * @param String
     */
    public MailCoach(String destination) {
        this.destination = destination;
        this.weight = 0.0;
        this.volume = 0;
        this.cargo = new ArrayList<Shipment>();
    }
    
    /**
     * Method getDestination returns instance variable String destination
     *
     * @param  none
     * @return String
     */
    public String getDestination()
    {
        return this.destination;
    }
    
    /**
     * Method getVolume returns instance variable of int volume
     *
     * @param  none
     * @return int
     */
    public int getVolume() {
        return this.volume;
    }
    
    /**
     * Method getWeight returns instance variable of double weight
     *
     * @param  none
     * @return double
     */
    public double getWeight() {
        return this.weight;
    }
    
    /**
     * Method getCargo returns instance variable of Array List cargo
     *
     * @param  none
     * @return Array List
     */
    public ArrayList<Shipment> getCargo() {
        return this.cargo;
    }
    
    /**
     * Method addShipment appends volume and weight with their respective Shipment object values
     * and appends cargo with the whole Shipment object
     *
     * @param  Object
     * @return nothing
     */
    public void addShipment(Shipment s) {
        this.volume += s.getVolume();
        this.weight += s.getWeight();
        this.cargo.add(s);
    }
}