package EnergyManagementSystemProject;

import java.nio.file.NoSuchFileException;

public class ChainedExceptionHandler {

    // Chain NoSuchFileException to IllegalArgumentException
    public void handleNoSuchFileException(NoSuchFileException e) {
        System.out.println("[DEBUG] Handling NoSuchFileException and chaining as IllegalArgumentException.");
        // Properly chain NoSuchFileException to IllegalArgumentException
        throw new IllegalArgumentException("File operation failed, caused by NoSuchFileException", e);
    }

    // Chain IllegalArgumentException to RuntimeException
    public void handleIllegalArgumentException(IllegalArgumentException e) {
        System.out.println("[DEBUG] Handling IllegalArgumentException and chaining as RuntimeException.");
        // Properly chain IllegalArgumentException to RuntimeException
        throw new RuntimeException("An error occurred due to invalid argument", e);
    }
}
