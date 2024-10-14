package EnergyManagementSystemProject;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class DataExchangeSimulator {

    // Simulate data exchange with byte streams (e.g., binary data)
    public void simulateByteStream(String fileName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName, true)) {
            byte[] data = {1, 2, 3, 4, 5};  // Example binary data
            fos.write(data);
            System.out.println("[BYTE STREAM] Data written to " + fileName);
        }
    }

    // Simulate data exchange with character streams (e.g., text data)
    public void simulateCharacterStream(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String data = "Sample text data for " + fileName + "\n";
            writer.write(data);
            System.out.println("[CHARACTER STREAM] Data written to " + fileName);
        }
    }
}
