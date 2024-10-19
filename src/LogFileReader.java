package EnergyManagementSystemProject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LogFileReader {

    // Method to read the contents of a file using a more modern, compact API
    public void readLogFile(String filePath) {
        Path path = Paths.get(filePath);

        try {
            // Using Files.newBufferedReader() which is more modern and efficient
            try (var reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("[LOG READER] Error reading file: " + e.getMessage());
        }
    }
}