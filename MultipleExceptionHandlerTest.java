package EnergyManagementSystemProject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThrows;
import java.io.File;
import java.io.FileNotFoundException;

public class MultipleExceptionHandlerTest {

    private File validFile;

    @Before
    public void setUp() throws Exception {
        // Create a temporary valid file for testing
        validFile = new File("valid_file.txt");
        if (!validFile.exists()) {
            validFile.createNewFile();  // Ensure the file exists
        }
    }

    @After
    public void tearDown() throws Exception {
        // Clean up by deleting the temporary valid file after tests
        if (validFile.exists()) {
            validFile.delete();
        }
    }

    @Test
    public void testFileNotFoundException() throws Exception {
        MultipleExceptionHandler handler = new MultipleExceptionHandler();
        assertThrows(FileNotFoundException.class, () -> handler.handleFileOperations("nonexistent_file.txt"));
    }

    @Test
    public void testValidFile() throws Exception {
        MultipleExceptionHandler handler = new MultipleExceptionHandler();
        handler.handleFileOperations(validFile.getPath());
    }

    @Test
    public void testEmptyFilePath() throws Exception {
        MultipleExceptionHandler handler = new MultipleExceptionHandler();
        assertThrows(FileNotFoundException.class, () -> handler.handleFileOperations(""));
    }

    @Test
    public void testFileInDifferentDirectory() throws Exception {
        MultipleExceptionHandler handler = new MultipleExceptionHandler();
        assertThrows(FileNotFoundException.class, () -> handler.handleFileOperations("logs/nonexistent_file.txt"));
    }

    @Test
    public void testNonTextFile() throws Exception {
        MultipleExceptionHandler handler = new MultipleExceptionHandler();
        assertThrows(FileNotFoundException.class, () -> handler.handleFileOperations("image.png"));
    }
}
