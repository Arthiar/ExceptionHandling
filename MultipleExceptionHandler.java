package EnergyManagementSystemProject;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class MultipleExceptionHandler {

    // Method to handle file operations and display output
    public void handleFileOperations(String filePath) throws FileNotFoundException {
        try {
            // Simulate reading a file
            FileReader file = new FileReader(filePath);
            System.out.println("[MULTIPLE EXCEPTIONS] File successfully opened: " + filePath);

        } catch (FileNotFoundException e) {
            System.out.println("[MULTIPLE EXCEPTIONS] Caught FileNotFoundException for file: " + filePath);
            throw e;  // Rethrow the exception to be handled at a higher level
        }
    }
}
