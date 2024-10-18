package EnergyManagementSystemProject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

    // Method to simulate IOException for testing purposes
    public void simulateIOException(String filePath) throws IOException {
        throw new IOException("Simulated IO Exception for file: " + filePath);
    }

    // Method to simulate SecurityException for testing purposes
    public void simulateSecurityException(String filePath) {
        throw new SecurityException("Access denied to file: " + filePath);
    }
}
