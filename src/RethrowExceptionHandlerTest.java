package EnergyManagementSystemProject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;

public class RethrowExceptionHandlerTest {

    @Test
    public void testIOExceptionRethrown() {
        System.out.println("Running test: testIOExceptionRethrown");
        RethrowExceptionHandler handler = new RethrowExceptionHandler();
        // Test that IOException is correctly re-thrown
        assertThrows(IOException.class, handler::handleAndRethrow);
    }

    @Test
    public void testIOExceptionMessage() {
        System.out.println("Running test: testIOExceptionMessage");
        RethrowExceptionHandler handler = new RethrowExceptionHandler();
        try {
            handler.handleAndRethrow();
        } catch (IOException e) {
            // Test that the exception message is correct
            assertTrue(e.getMessage().contains("Simulated IO Exception"));
        }
    }

    @Test
    public void testExceptionPropagation() {
        System.out.println("Running test: testExceptionPropagation");
        RethrowExceptionHandler handler = new RethrowExceptionHandler();
        // Ensure that the exception propagates correctly after being re-thrown
        assertThrows(IOException.class, handler::handleAndRethrow);
    }

    @Test
    public void testCatchAndReThrow() {
        System.out.println("Running test: testCatchAndReThrow");
        RethrowExceptionHandler handler = new RethrowExceptionHandler();
        try {
            handler.handleAndRethrow();
        } catch (IOException e) {
            // Ensure that the re-thrown exception is caught properly
            assertTrue(e.getMessage().equals("Simulated IO Exception"));
        }
    }

    @Test
    public void testReThrowInMainHandling() {
        System.out.println("Running test: testReThrowInMainHandling");
        RethrowExceptionHandler handler = new RethrowExceptionHandler();
        try {
            handler.handleAndRethrow();
        } catch (IOException e) {
            // Ensure the exception can be handled at the main method level
            System.out.println("Caught in test: " + e.getMessage());
        }
    }
}
