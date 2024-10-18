package EnergyManagementSystemProject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.nio.file.NoSuchFileException;

public class ChainedExceptionHandlerTest {

	@BeforeEach
    public void beforeEachTest(TestInfo testInfo) {
        System.out.println();
        System.out.println("Starting Test: " + testInfo.getDisplayName());
    }

    @AfterEach
    public void afterEachTest(TestInfo testInfo) {
        System.out.println("Finished Test: " + testInfo.getDisplayName());
    }
    
    @Test
    public void testHandleNoSuchFileException() {
        ChainedExceptionHandler handler = new ChainedExceptionHandler();

        // Simulate NoSuchFileException
        NoSuchFileException noSuchFileException = new NoSuchFileException("testfile.txt");

        // Test that an IllegalArgumentException is thrown when chaining NoSuchFileException
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            handler.handleNoSuchFileException(noSuchFileException);
        });

        // Check if the message contains the expected chain information
        assertTrue(thrown.getMessage().contains("File operation failed, caused by NoSuchFileException"),
                   "Expected message not found in IllegalArgumentException");
        
        // Check that the cause is NoSuchFileException
        Throwable cause = thrown.getCause();
        assertTrue(cause instanceof NoSuchFileException, "Expected cause to be NoSuchFileException");
    }

    @Test
    public void testHandleIllegalArgumentException() {
        ChainedExceptionHandler handler = new ChainedExceptionHandler();

        // Simulate IllegalArgumentException
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Invalid argument");

        // Test that a RuntimeException is thrown when chaining IllegalArgumentException
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            handler.handleIllegalArgumentException(illegalArgumentException);
        });

        // Check if the message contains the expected chain information
        assertTrue(thrown.getMessage().contains("An error occurred due to invalid argument"),
                   "Expected message not found in RuntimeException");

        // Check that the cause is IllegalArgumentException
        Throwable cause = thrown.getCause();
        assertTrue(cause instanceof IllegalArgumentException, "Expected cause to be IllegalArgumentException");
    }

    @Test
    public void testFullExceptionChain() {
        ChainedExceptionHandler handler = new ChainedExceptionHandler();

        // Simulate NoSuchFileException
        NoSuchFileException noSuchFileException = new NoSuchFileException("testfile.txt");

        // Chain NoSuchFileException and catch the final IllegalArgumentException
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            handler.handleNoSuchFileException(noSuchFileException);
        });

        // Ensure that the cause is NoSuchFileException
        Throwable cause = thrown.getCause();
        assertTrue(cause instanceof NoSuchFileException, "Expected cause to be NoSuchFileException");
        assertTrue(cause.getMessage().contains("testfile.txt"), "Expected message not found in NoSuchFileException");

        // Now chain IllegalArgumentException to RuntimeException
        RuntimeException finalThrown = assertThrows(RuntimeException.class, () -> {
            handler.handleIllegalArgumentException(thrown);
        });

        // Ensure that the cause of RuntimeException is IllegalArgumentException
        Throwable finalCause = finalThrown.getCause();
        assertTrue(finalCause instanceof IllegalArgumentException, "Expected cause to be IllegalArgumentException");
    }

    @Test
    public void testRuntimeExceptionMessage() {
        ChainedExceptionHandler handler = new ChainedExceptionHandler();

        // Simulate IllegalArgumentException
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Invalid file path");

        // Chain IllegalArgumentException and catch the RuntimeException
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            handler.handleIllegalArgumentException(illegalArgumentException);
        });

        // Verify that the final RuntimeException message is correct
        assertTrue(thrown.getMessage().contains("An error occurred due to invalid argument"),
                   "Expected message not found in RuntimeException");
    }

    @Test
    public void testCauseOfExceptions() {
        ChainedExceptionHandler handler = new ChainedExceptionHandler();

        // Simulate NoSuchFileException
        NoSuchFileException noSuchFileException = new NoSuchFileException("testfile.txt");

        // Test the full exception chain
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            handler.handleNoSuchFileException(noSuchFileException);
        });

        // Check if the cause is NoSuchFileException
        Throwable cause = thrown.getCause();
        assertTrue(cause instanceof NoSuchFileException, "Expected cause to be NoSuchFileException");
    }
}
