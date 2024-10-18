package EnergyManagementSystemProject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class EnergyManagementSystemMain {

    public static void main(String[] args) {
        // Instance creation
        LogFileManager logManager = new LogFileManager();
        MetadataManager metadataManager = new MetadataManager();
        DataExchangeSimulator dataSimulator = new DataExchangeSimulator();

        MultipleExceptionHandler multipleExceptionHandler = new MultipleExceptionHandler();
        RethrowExceptionHandler rethrowExceptionHandler = new RethrowExceptionHandler();

        try {
            // File management actions
            System.out.println("===== Log File Management =====");

            String[] stations = {"StationA", "StationB"};
            String[] sources = {"Solar", "Wind", "Hydro"};

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
            logManager.openLogFile("StationA", getCurrentDate());

        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] File not found during file handling: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("[ERROR] IOException during data handling: " + e.getMessage());
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
