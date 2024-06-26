import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
 * Class Project5 uses Class MailCoach and Shipment to read data and sort it into a report statement
 * Does pretty much all the heavy lifting (that's a hefty boy)
 *
 * @author Collins Ramsey
 * @version 10/25/22 @ 4:56 pm
 */
public class Project5 {
    //DATE AND TIME YOU FIRST START WORKING ON THIS ASSIGNMENT (date AND time): <--------------------
    //ANSWER: 10/25/22 @ 4:56 pm  <--------------------
    
    //DO NOT ALTER THE MAIN METHOD
    public static void main( String[] args ) {
        //test your entity classes, comment out when you passed all tests
        //testShipment();
        //testMailCoach();
        
        //readData reads the input file into an array list
        //it fills the array list with Shipment objects
        ArrayList< Shipment > allMail = readData( "week1.txt" );
        
        //sortData goes through the raw data contained in all mail and populates
        //an array list for the destination with MailCoach objects
        ArrayList< MailCoach > stolat = sortData( "Stolat", allMail );
        ArrayList< MailCoach > quirm = sortData( "Quirm", allMail );
        ArrayList< MailCoach > pseudopolis = sortData( "Pseudopolis", allMail );
        ArrayList< MailCoach > borogravia = sortData( "Borogravia", allMail );
        ArrayList< MailCoach > ueberwald = sortData( "Ueberwald", allMail );
        ArrayList< MailCoach > krull = sortData( "Krull", allMail );
        
        //printReport prints the mail coach data dor each destination in turn
        printReport( stolat );
        printReport( quirm );
        printReport( pseudopolis );
        printReport( borogravia );
        printReport( ueberwald );
        printReport( krull );
    }//DO NOT ALTER THE MAIN METHOD
    public static void testShipment() { //DO NOT ALTER THIS METHOD
        Shipment s1 = new Shipment();
        assert s1.getDestination().equals( "" ) && s1.getVolume() == 0 && 
               s1.getId() == 0 && Math.abs( s1.getWeight() - 0 ) < 0.001 : "Shipment standard constructor not working";
        Shipment s2 = new Shipment( 44, "Stolat", 10.5, 13 );
        assert s2.getDestination().equals( "Stolat" ) : "Shipment second costructor destination not set correctly";
        assert s2.getVolume() == 13 : "Shipment second costructor volume not set correctly";
        assert s2.getId() == 44 : "Shipment second costructor id not set correctly";
        assert Math.abs( s2.getWeight() - 10.5 ) < 0.001 : "Shipment second costructor weight not set correctly";
        System.out.println( "Shipment all tests passed" );
    } //DO NOT ALTER THIS METHOD
    public static void testMailCoach() { //DO NOT ALTER THIS METHOD
        MailCoach mc1 = new MailCoach();
        assert mc1.getDestination().equals( "" ) && mc1.getVolume() == 0 && 
               Math.abs( mc1.getWeight() - 0 ) < 0.001 && mc1.getCargo() != null : "MailCoach standard constructor not working";
        MailCoach mc2 = new MailCoach( "Stolat" );  
        assert mc2.getDestination().equals( "Stolat" ) && mc2.getVolume() == 0 && 
               Math.abs( mc2.getWeight() - 0 ) < 0.001 && mc2.getCargo() != null : "MailCoach second constructor not working";
        Shipment s1 = new Shipment( 12, "Stolat", 106.7, 45 );
        Shipment s2 = new Shipment( 44, "Stolat", 10.5, 13 );
        mc2.addShipment( s1 );
        mc2.addShipment( s2 );
        assert mc2.getVolume() == 58 : "MailCoach addShipment not working";
        assert Math.abs( mc2.getWeight() - 117.2 ) < 0.001 : "MailCoach addShipment not working";
        assert mc2.getCargo().get( 0 ) == s1 : "MailCoach addShipment not working";
        assert mc2.getCargo().get( 1 ) == s2 : "MailCoach addShipment not working";
        System.out.println( "MailCoach all tests passed" );
    }//DO NOT ALTER THIS METHOD
    
    /**
     * Method readData takes in String fileName and returns Array List allMail (type Shipment).
     * Reads through text file, creates Shipment objects and appends them to an Array List
     *
     * @param  String
     * @return Array List
     */
    public static ArrayList< Shipment > readData( String fileName ) {
        Scanner in = null;
        
        // try and read input file
        try {
            File f = new File(fileName);
            
            in = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.print("File was not found.");
        }
        
        int count = 0;
        // counts number of lines in txt file to set up allMail array list
        while (in.hasNext()) {
            ++count;
            in.nextLine();
        }
        
        ArrayList<Shipment> allMail = new ArrayList<Shipment>(count);
        
        // reset Scanner
        try {
            in = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e) {
            System.out.print("File not found.");
        }
        
        double weight = 0.0;
        int volume = 0;
        String tempVolume = "";
        String tempId = "";
        String destination = "";
        int id = 0;
        
        // reads a line and creates a new object every line, then assigns values to the object
        // and assigns the full object to the array list
        while (in.hasNext()) {
            weight = in.nextDouble();
            in.next();
            tempVolume = in.next();
            tempVolume = tempVolume.replace("(", " ");
            tempVolume = tempVolume.replace(")", " ");
            volume = Integer.parseInt(tempVolume.trim());
            in.next();
            destination = in.next();
            in.next();
            in.next();
            tempId = in.next();
            tempId = tempId.replace("#", " ");
            id = Integer.parseInt(tempId.trim());
            
            allMail.add(new Shipment(id, destination, weight, volume));
        }
        
        in.close();
        
        return allMail;
    }
    
    /**
     * Method sortData takes String destination and Array List allMail and returns Array List dest (type MailCoach).
     * Takes existing Shipment objects from Array List allMail and sorts specified Shipment types, using
     * String destination, into MailCoach objects that are appended to Array List MailCoach (cue Inception)
     *
     * @param  String
     * @param Array List
     * @return Array List
     */
    public static ArrayList< MailCoach > sortData( String destination, ArrayList< Shipment > allMail ) {
        ArrayList<MailCoach> dest = new ArrayList<MailCoach>();
        
        // makes new MailCoach object and adds it to MailCoach array list
        dest.add(new MailCoach(destination));
        
        // if a new MailCoach is made, this counter goes up
        int count = 0;
        // counter for the number of matched objects in the array list
        int matches = 0;
        
        // loop for counting matches
        for (int i = 0; i < allMail.size(); ++i) {
            if (destination.equals(allMail.get(i).getDestination())) {
                matches++;
            }
        }
        
        // as long as there are matches, the loop will continue
        while (matches > 0) {
            for (int i = 0; i < allMail.size(); ++i) {
                // looks through allMail and compares the desination to each element of allMail
                if (destination.equals(allMail.get(i).getDestination())) {
                    // checks current MailCoach object at that element count (0, 1, 2...)
                    // adds the values of Shipment object volume and weight to MailCoach object volume and weight
                    // in the condition, then compares to see if the total goes over the limit
                    if ((dest.get(count).getVolume() + allMail.get(i).getVolume() <= 100) 
                    && (dest.get(count).getWeight() + allMail.get(i).getWeight() <= 500)) {
                        // add Shipment object to MailCoach 
                        // replace Shipment object at that element with blank Shipment object
                        // decrement matches by 1
                        dest.get(count).addShipment(allMail.get(i));
                        allMail.set(i, new Shipment());
                        matches--;
                    }
                }
            }
            // if there are left over matches that couldn't fit, a new MailCoach is added 
            if (matches > 0) {
                dest.add(new MailCoach(destination));
                count++;
            }
        }
        
        return dest;
    }
    
    /**
     * Method printReport takes Array List mc and returns nothing.
     * Takes sorted data and prints out a report statement with each destination's MailCoaches,
     * the ids in each MailCoach, and determines if each MailCoach is full enough to be dispatched.
     *
     * @param  Array List
     * @return nothing
     */
    public static void printReport( ArrayList< MailCoach > mc ) {
        System.out.printf("Next week's mail coaches to %s:%n", mc.get(0).getDestination());
        
        String ids = "";
        int i;
        
        // outer loop goes through the size of MailCoach array list
        for (i = 0; i < mc.size(); i++) {
            // inner loop goes through MailCoach object and appends the Shipment id's to a string to use for printf statement
            for (int j = 0; j < mc.get(i).getCargo().size(); j++) {
                ids += "#" + mc.get(i).getCargo().get(j).getId() + " ";
            }
            // check to see if a MailCoach is empty (assumes there are no items with zero volume)
            if (mc.get(i).getVolume() == 0) {
                System.out.println("    NO SHIPMENTS");
                break;
            }
            // outside inner loop, create two if blocks, one for dispatch, one for hold (check if weight or volume is over half)
            if (mc.get(i).getVolume() >= 50 || mc.get(i).getWeight() >= 250) {
                String dispatch = "DISPATCH:";
                System.out.printf("    %-9s coach %d (shipments %s)%n",dispatch, i + 1, ids.trim());
            }
            else {
                String dispatch = "HOLD:";
                System.out.printf("    %-9s coach %d (shipments %s)%n",dispatch, i + 1, ids.trim());
            }
            // reset ids so new ones can get placed properly
            ids = "";
        }
        // after outer loop finishes, print a new line
        System.out.println("");
    }
}