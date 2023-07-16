import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The FileManager class provides methods to load and save mall data from/to a file.
 */
public class FileManager {
    private static final String MALLS = "Largest-Malls.csv"; // Place where to read

    /**
     * Loads mall data from a file.
     *
     * @return A list of Mall objects representing the loaded malls.
     * @throws FileNotFoundException if the file is not found.
     */
    public static List<Mall> loadMalls() throws FileNotFoundException {
        List<Mall> malls = new ArrayList<>();
        File file = new File(MALLS);
        Scanner scanner = new Scanner(file);
        scanner.nextLine(); // Skip the header line
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Mall mall = Mall.parseFrom(line);
            malls.add(mall);
        }
        scanner.close();
        return malls;
    }

    /**
     * Saves mall data to a file.
     *
     * @param malls    The list of Mall objects to be saved.
     * @param fileName The name of the file to save the malls to.
     * @throws IOException if an I/O error occurs while writing to the file.
     */
    public static void saveMalls(List<Mall> malls, String fileName) throws IOException {
        File file = new File("result/" + fileName);
        if (file.exists()) throw new IOException("File already exists: " + fileName);
        FileWriter writer = new FileWriter(file);
        writer.write("Rank,Mall,Country,City (metropolitan area),Year opened,Gross leasable area (GLA),Shops\n");
        for (Mall mall : malls) {
            writer.write(mall.parseTo() + "\n");
        }
        writer.close();
        System.out.println("Malls saved successfully to: " + file.getName()); // (optional) message of succesfull save
    }
}
