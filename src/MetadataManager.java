package EnergyManagementSystemProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MetadataManager {

    // Method to update metadata after an action on a log file
    public void updateMetadata(String action, String fileName) throws IOException {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String metadataEntry = String.format("File Name: %s\nAction: %s\nTimestamp: %s\n\n", fileName, action, timestamp);

        // Append metadata to a metadata file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("metadata.txt", true))) {
            writer.write(metadataEntry);
        }

        System.out.println("[METADATA UPDATED] " + fileName + " | Action: " + action + " | Time: " + timestamp);
    }
    
    // Method to read metadata (optional, for future use)
    public void readMetadata() throws IOException {
        // Code to read metadata can be added here if needed later
    }
}
