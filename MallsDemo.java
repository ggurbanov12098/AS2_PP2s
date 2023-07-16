import java.io.IOException; 
import java.util.List;
import java.util.ArrayList; /*
 * ArrayList is a class that implements the List interface, 
 * which is a sub-interface of the Collection interface. 
 * The Collection interface itself extends the Iterable interface.
 */



/**
 * This class demonstrates the functionality of sorting and filtering malls.
 */
public class MallsDemo {

    /**
     * The main method is the entry point of the program.
     * 
     * @param args The command line arguments.
     * @throws IOException If an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {
        try{
            // Load all the malls from Largest-Malls.csv
            List<Mall> malls = FileManager.loadMalls();
    
            // Sorting
            sort(malls, "country", "asc"); // Sort by country name in ascending order
            FileManager.saveMalls(malls, "sortedByCountry.csv");
            sort(malls, "city", "asc"); // Sort by city name in ascending order
            FileManager.saveMalls(malls, "sortedByCity.csv");
            sort(malls, "shops", "desc"); // Sort by number of shops in descending order
            FileManager.saveMalls(malls, "sortedByNumOfShops.csv");
            
            // Filtration
            List<Mall> chinaMalls = filterByCountry(malls, "China"); // Filter malls in China
            FileManager.saveMalls(chinaMalls, "chinaMalls.csv");
            List<Mall> areaFilteredMalls = filterByAreaSqFt(malls, 4000000, 6000000); // Filter malls within the area range
            FileManager.saveMalls(areaFilteredMalls, "areaFilteredMalls.csv");
        } catch(IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }


    /**
     * Sorts the list of malls based on the given field and order.
     * 
     * @param malls The list of malls to be sorted.
     * @param fieldName The name of the field to sort by.
     * @param order The order of sorting, either "asc" for ascending or "desc" for descending.
     * @throws IllegalArgumentException If the field name is invalid.
     */
    private static void sort(List<Mall> malls, String fieldName, String order) {
        malls.sort((mall1, mall2) -> {
            int result;
            switch (fieldName) {
                case "country":
                    result = mall1.getCountry().compareTo(mall2.getCountry());
                    break;
                case "city":
                    result = mall1.getCity().compareTo(mall2.getCity());
                    break;
                case "shops":
                    result = Integer.compare(mall1.getShops(), mall2.getShops());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field name: " + fieldName);
            }

            return order.equals("asc") ? result : -result;
        });
    }


    /**
     * Filters the malls by country.
     * 
     * @param malls The list of malls to be filtered.
     * @param countryName The name of the country to filter by.
     * @return The filtered list of malls.
     */
    private static List<Mall> filterByCountry(List<Mall> malls, String countryName) {
        List<Mall> filteredMalls = new ArrayList<>();

        for (Mall mall : malls)
            if (mall.getCountry().equals(countryName)) filteredMalls.add(mall);
        return filteredMalls;
    }


    /**
     * Filters the malls by area range.
     * 
     * @param malls The list of malls to be filtered.
     * @param lower The lower bound of the area range.
     * @param upper The upper bound of the area range.
     * @return The filtered list of malls.
     */
    private static List<Mall> filterByAreaSqFt(List<Mall> malls, int lower, int upper) {
        List<Mall> filteredMalls = new ArrayList<>();
        for (Mall mall : malls)
            if (mall.getGla_sqft() >= lower && mall.getGla_sqft() <= upper) filteredMalls.add(mall);
        return filteredMalls;
    }
}
