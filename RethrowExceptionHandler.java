package EnergyManagementSystemProject;

import java.io.IOException;

public class RethrowExceptionHandler {

    // Method that simulates an operation that throws and rethrows an exception
    public void handleAndRethrow() throws IOException {
        try {
            // Simulate an IOException
            throw new IOException("Simulated IO Exception");

        } catch (IOException e) {
            System.out.println("[RETHROW EXCEPTION] Caught and rethrowing IOException: " + e.getMessage());
            throw e;  // Rethrow the exception
        }
    }
}
