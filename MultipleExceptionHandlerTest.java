package EnergyManagementSystemProject;

import org.junit.Test;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MultipleExceptionHandlerTest {

    @Test
    public void testValidFilePath() throws FileNotFoundException {
        MultipleExceptionHandler handler = new MultipleExceptionHandler();
        String filePath = "C:/Users/arthi/git/Java-Group-Projects---Group-2/HA  Basic I_O and Regular expression/src/EnergyManagementSystem/logs/valid_file.txt";
        System.out.println("Running test: testValidFilePath");

        try {
            handler.handleFileOperations(filePath);
            System.out.println("testValidFilePath passed: File was found.");
        } catch (FileNotFoundException e) {
            fail("File should exist, but FileNotFoundException was thrown.");
        }
    }

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
            assert(e.getMessage().contains("non_existent_or_restricted_file.txt"));
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
            assert(e.getMessage().contains("Simulated IO Exception"));
        }
    }

    @Test
    public void testSecurityExceptionHandling() {
        MultipleExceptionHandler handler = new MultipleExceptionHandler();
        try {
            handler.simulateSecurityException("logs/restricted_access_file.txt");
            fail("Expected SecurityException due to restricted access.");
        } catch (SecurityException e) {
            assert(e.getMessage().contains("Access denied"));
        }
    }
}
