package EnergyManagementSystemProject;

import java.io.FileNotFoundException;
import java.io.IOException;
//import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.io.File;

public class EnergyManagementSystemMain {

    public static void main(String[] args) {
        // Instance creation
        LogFileManager logManager = new LogFileManager();
        MetadataManager metadataManager = new MetadataManager();
        DataExchangeSimulator dataSimulator = new DataExchangeSimulator();

        MultipleExceptionHandler multipleExceptionHandler = new MultipleExceptionHandler();
        RethrowExceptionHandler rethrowExceptionHandler = new RethrowExceptionHandler();
        LogFileReader logFileReader = new LogFileReader();
        ChainedExceptionHandler chainedExceptionHandler = new ChainedExceptionHandler();
        
        try {
        	
            // File management actions
            System.out.println("===== Log File Management =====");

            String[] stations = {"StationA", "StationB"};
            String[] sources = {"Solar", "Wind", "Hydro"};
            
            // Use java.nio.file.Files to trigger NoSuchFileException
            // Path nonExistentFilePath = Paths.get("nonexistent_file.txt");
            // Files.readAllBytes(nonExistentFilePath);  // This triggers NoSuchFileException

            // Create daily logs for all stations and sources
            logManager.createDailyLogs(stations, sources);

            // Handle multiple exceptions in log management
            System.out.println("Handling file exceptions...");
            multipleExceptionHandler.handleFileOperations("logs/StationA_Solar_log_" + getCurrentDate() + ".txt");

            // Ensure the correct archive folder exists
            File archiveFolder = new File("archived_logs");
            if (!archiveFolder.exists()) {
                archiveFolder.mkdir();  // Create the folder if it doesn't exist
            }

            // Archive the log file into the correct folder
            logManager.archiveLog("archived_logs/StationA_Solar_log_" + getCurrentDate() + ".txt");

            System.out.println();

            // Metadata management actions
            System.out.println("===== Metadata Management =====");
            metadataManager.updateMetadata("CREATE", "StationA_Solar_log_" + getCurrentDate() + ".txt");
            metadataManager.updateMetadata("MOVE", "StationA_Solar_log_" + getCurrentDate() + ".txt");
            metadataManager.updateMetadata("ARCHIVE", "archived_logs/StationA_Solar_log_" + getCurrentDate() + ".txt");
            metadataManager.updateMetadata("DELETE", "StationA_Solar_log_" + getCurrentDate() + ".txt");

            System.out.println();

            // Data exchange simulation actions
            System.out.println("===== Data Exchange Simulation =====");
            dataSimulator.simulateByteStream("StationA_Solar_log_" + getCurrentDate() + ".dat");
            dataSimulator.simulateCharacterStream("StationA_Solar_log_" + getCurrentDate() + ".txt");

            System.out.println();

            // Open log file based on equipment name and date
            System.out.println("===== Search Log File =====");
            logManager.openLogFile("StationA", "Hydro", getCurrentDate());
            
            // Reading the log file using LogFileReader
            System.out.println("===== Read Log File Content =====");
            logFileReader.readLogFile("logs/StationA_Solar_log_" + getCurrentDate() + ".txt");

        } 
        
        catch (NoSuchFileException e) {
            // Handle NoSuchFileException using ChainedExceptionHandler (this chains as IllegalArgumentException)
            System.out.println("[DEBUG] Caught NoSuchFileException, passing to ChainedExceptionHandler.");
            chainedExceptionHandler.handleNoSuchFileException(e);
        }
        
        catch (FileNotFoundException e) {
            System.out.println("[ERROR] File not found during file handling: " + e.getMessage());

        }  
        
        catch (IOException e) {
            System.out.println("[ERROR] IOException during data handling: " + e.getMessage());
        }
        
        catch (IllegalArgumentException e) {
            // Handle IllegalArgumentException directly
            System.out.println("[MAIN] Caught IllegalArgumentException: " + e.getMessage());

            // Print the cause of the IllegalArgumentException (if any)
            Throwable cause = e.getCause();
            if (cause != null) {
                System.out.println("Caused by: " + cause.getClass().getName() + ": " + cause.getMessage());
            }
        }
        
        catch (RuntimeException e) {
            // Handle the re-thrown RuntimeException
            System.out.println("[MAIN] Caught rethrown RuntimeException: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("Caused by: " + e.getCause().getClass().getName() + ": " + e.getCause().getMessage());
            }
        }

        // Rethrow Exception Handling
        try {
            System.out.println("Handling and rethrowing exception...");
            rethrowExceptionHandler.handleAndRethrow();

        } catch (IOException e) {
            System.out.println("[ERROR] Rethrown IOException caught in main: " + e.getMessage());
        }
    }

    // Helper method to get the current date in the format "yyyyMMdd"
    public static String getCurrentDate() {
        return new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
    }
}