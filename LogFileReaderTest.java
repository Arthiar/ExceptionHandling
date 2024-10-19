package EnergyManagementSystemProject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LogFileReaderTest {

    @Test
    public void testReadLogFile_ValidFile() throws IOException {
        String fileName = "testLogFile.txt";
        Path path = Paths.get(fileName);
        
        // Write a sample file for the test
        Files.write(path, "Sample log data".getBytes());
        
        LogFileReader reader = new LogFileReader();
        reader.readLogFile(fileName);
        
        // If no exception, the test passes
        assertTrue(Files.exists(path), "Log file should exist after reading.");
        
        // Clean up
        Files.deleteIfExists(path);
    }

    @Test
    public void testReadLogFile_FileNotFound() {
        LogFileReader reader = new LogFileReader();
        String fileName = "nonExistentFile.txt";
        
        assertDoesNotThrow(() -> reader.readLogFile(fileName), "Exception should not be thrown if file is not found, handled internally.");
    }

    @Test
    public void testReadLogFile_EmptyFile() throws IOException {
        String fileName = "emptyLogFile.txt";
        Path path = Paths.get(fileName);
        
        // Create an empty file for the test
        Files.createFile(path);
        
        LogFileReader reader = new LogFileReader();
        reader.readLogFile(fileName);
        
        assertTrue(Files.exists(path), "Empty log file should exist.");
        
        // Clean up
        Files.deleteIfExists(path);
    }

    @Test
    public void testReadLogFile_MultipleLines() throws IOException {
        String fileName = "multiLineLogFile.txt";
        Path path = Paths.get(fileName);
        
        // Create a file with multiple lines
        Files.write(path, "Line 1\nLine 2\nLine 3".getBytes());
        
        LogFileReader reader = new LogFileReader();
        reader.readLogFile(fileName);
        
        assertTrue(Files.exists(path), "Multi-line log file should exist after reading.");
        
        // Clean up
        Files.deleteIfExists(path);
    }

    @Test
    public void testReadLogFile_SpecialCharacters() throws IOException {
        String fileName = "specialCharLogFile.txt";
        Path path = Paths.get(fileName);
        
        // Create a file with special characters
        Files.write(path, "Log with special characters: @#$%^&*()".getBytes());
        
        LogFileReader reader = new LogFileReader();
        reader.readLogFile(fileName);
        
        assertTrue(Files.exists(path), "Log file with special characters should exist.");
        
        // Clean up
        Files.deleteIfExists(path);
    }
}
