package EnergyManagementSystemProject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MultipleExceptionHandlerTest {

    @Test
    public void testFileNotFoundException() {
        MultipleExceptionHandler handler = new MultipleExceptionHandler();
        assertThrows(FileNotFoundException.class, () -> {
            handler.handleFileOperations("logs/non_existent_file.txt");
        });
    }

    @Test
    public void testExceptionPropagation() {
        MultipleExceptionHandler handler = new MultipleExceptionHandler();
        assertThrows(FileNotFoundException.class, () -> {
            handler.handleFileOperations("logs/another_missing_file.txt");
        });
    }

    @Test
    public void testSimulatedFileAccessError() {
        MultipleExceptionHandler handler = new MultipleExceptionHandler();
        String filePath = "non_existent_or_restricted_file.txt";
        try {
            handler.handleFileOperations(filePath);
            fail("Expected a FileNotFoundException due to non-existent or restricted file.");
        } catch (FileNotFoundException e) {
            assertTrue(e.getMessage().contains("non_existent_or_restricted_file.txt"));
        }
    }

    @Test
    public void testDifferentFileFormats() {
        MultipleExceptionHandler handler = new MultipleExceptionHandler();
        assertThrows(FileNotFoundException.class, () -> {
            handler.handleFileOperations("logs/invalid_format_file.jpg");
        });
    }

    @Test
    public void testIOExceptionHandling() {
        MultipleExceptionHandler handler = new MultipleExceptionHandler();
        try {
            handler.simulateIOException("logs/simulated_io_exception.txt");
        } catch (IOException e) {
            System.out.println("testIOExceptionHandling caught expected exception.");
            assertTrue(e.getMessage().contains("Simulated IO Exception"));
        }
    }

    @Test
    public void testSecurityExceptionHandling() {
        MultipleExceptionHandler handler = new MultipleExceptionHandler();
        try {
            handler.simulateSecurityException("logs/restricted_access_file.txt");
            fail("Expected SecurityException due to restricted access.");
        } catch (SecurityException e) {
            assertTrue(e.getMessage().contains("Access denied"));
        }
    }
}
