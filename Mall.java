import java.util.Random;
import java.lang.String;

/**
 * The Mall class represents a shopping mall with its attributes and behaviors.
 */
public class Mall {
    private String id; //randomly generated
    private String mallName, country, city;
    private Integer yearOpened, gla_sqft, gla_sqmt, shops;
    
    /**
     * Constructs a Mall object with the specified attributes.
     *
     * @param id          the unique identifier of the mall
     * @param mallName    the name of the mall
     * @param country     the country where the mall is located
     * @param city        the city where the mall is located
     * @param yearOpened  the year the mall was opened
     * @param gla_sqft    the gross leasable area of the mall in square feet
     * @param gla_sqmt    the gross leasable area of the mall in square meters
     * @param shops       the number of shops in the mall
     */
    public Mall(String id, String mallName, String country, String city, Integer yearOpened, Integer gla_sqft, Integer gla_sqmt, Integer shops) {
        this.id = generateId();
        
        this.mallName = mallName;
        this.country = country;
        this.city = city;
        this.yearOpened = yearOpened;
        this.gla_sqft = gla_sqft;
        this.gla_sqmt = gla_sqmt;
        this.shops = shops; //String vs Integer issue
    }
    
    /**
     * Generates a random ID for the mall.
     *
     * @return a randomly generated ID
     */
    private String generateId() {
        Random random = new Random();
        return Integer.toString(random.nextInt(10000));
    }


    // Getters
    public String getId() {
        return id;
    }
    public String getMallName() {
        return mallName;
    }
    public String getCountry() {
        return country;
    }
    public String getCity() {
        return city;
    }
    public Integer getYearOpened() {
        return yearOpened;
    }
    public Integer getGla_sqft() { // Gets the gross leasable area of the mall in square feet
        return gla_sqft;
    }
    public Integer getGla_sqmt() { // Gets the gross leasable area of the mall in square meters
        return gla_sqmt;
    }
    public Integer getShops() { // Gets the number of shops in the mall
        return shops;
    }
    // No need for Setters in this assignment yet


    /**
     * Parses a mall record from a string representation.
     *
     * @param mallRecord the string representation of the mall record
     * @return the Mall object parsed from the mall record
     * @throws IllegalArgumentException if the mall record is invalid
     */

    public static Mall parseFrom(String mallRecord) throws IllegalArgumentException {
                // final String[] parts = mallRecord.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*(?![^\\\"]*\\\"))");
                // final String id = UUID.randomUUID().toString(); not suitable - Reason: existance of letters instead of numbers and long size
        
        String[] parts = mallRecord.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        if (parts.length != 7) throw new IllegalArgumentException("Invalid mall record: " + mallRecord); 
            // Array starts at idx [0], end will be [7] (starting from 1 it's 8 actually)
        String id = parts[0];
        String mallName = parts[1];
        String country = parts[2];
        String city = parts[3];
        Integer yearOpened; String year_test = parts[4];
        if(year_test.equalsIgnoreCase("Under-Construction")) yearOpened = -1;
        else yearOpened = Integer.parseInt(year_test);
        String gla = parts[5].replaceAll("\"", "");
        Integer shops = Integer.parseInt(parts[6].replaceAll("[^\\d]", ""));
        
        // shops = Integer.parseInt(parts[6]);
        String[] glaParts = gla.split(" ");
        Integer gla_sqft = Integer.parseInt(glaParts[0].replaceAll(",", ""));
        Integer gla_sqmt = Integer.parseInt(glaParts[2].replaceAll("[^\\d]", "")); //[2]

        return new Mall(id, mallName, country, city, yearOpened, gla_sqft, gla_sqmt, shops);
    }

    /**
     * Converts the Mall object to a string representation.
     *
     * @return the string representation of the Mall object
     */
    public String parseTo() {
        return id + "," + mallName + "," + country + "," + city + "," + yearOpened + "," + gla_sqft + ";" + gla_sqmt + "," + shops;
    }

    /**
     * Converts the specified Mall object to a string representation.
     *
     * @param mallInstance the Mall object to convert
     * @return the string representation of the Mall object
     */
    public static String parseTo(Mall mallInstance) {
        return mallInstance.parseTo();
    }
}