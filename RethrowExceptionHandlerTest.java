package EnergyManagementSystemProject;

import org.junit.Test;
import static org.junit.Assert.assertThrows;
import java.io.IOException;

public class RethrowExceptionHandlerTest {

    @Test
    public void testIOExceptionRethrown() {
        RethrowExceptionHandler handler = new RethrowExceptionHandler();
        // Test that IOException is correctly re-thrown
        assertThrows(IOException.class, handler::handleAndRethrow);
    }

    @Test
    public void testIOExceptionMessage() {
        RethrowExceptionHandler handler = new RethrowExceptionHandler();
        try {
            handler.handleAndRethrow();
        } catch (IOException e) {
            // Test that the exception message is correct
            assert(e.getMessage().contains("Simulated IO Exception"));
        }
    }

    @Test
    public void testExceptionPropagation() {
        RethrowExceptionHandler handler = new RethrowExceptionHandler();
        // Ensure that the exception propagates correctly after being re-thrown
        assertThrows(IOException.class, handler::handleAndRethrow);
    }

    @Test
    public void testCatchAndReThrow() {
        RethrowExceptionHandler handler = new RethrowExceptionHandler();
        try {
            handler.handleAndRethrow();
        } catch (IOException e) {
            // Ensure that the re-thrown exception is caught properly
            assert(e.getMessage().equals("Simulated IO Exception"));
        }
    }

    @Test
    public void testReThrowInMainHandling() {
        RethrowExceptionHandler handler = new RethrowExceptionHandler();
        try {
            handler.handleAndRethrow();
        } catch (IOException e) {
            // Ensure the exception can be handled at the main method level
            System.out.println("Caught in test: " + e.getMessage());
        }
    }
}
