package EnergyManagementSystemProject;

import java.io.FileNotFoundException;
import java.io.IOException;

public class EnergyManagementSystemMain {

    public static void main(String[] args) {
        // Instance creation
        LogFileManager logManager = new LogFileManager();  // person 1
        MetadataManager metadataManager = new MetadataManager();  // person 2
        DataExchangeSimulator dataSimulator = new DataExchangeSimulator();  // person 3

        MultipleExceptionHandler multipleExceptionHandler = new MultipleExceptionHandler();  // new
        RethrowExceptionHandler rethrowExceptionHandler = new RethrowExceptionHandler();  // new

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

            // Archive the log file into a .zip
            logManager.archiveLog("StationA_Solar_log_" + getCurrentDate() + ".txt");

            System.out.println();

            // Metadata management actions
            System.out.println("===== Metadata Management =====");
            metadataManager.updateMetadata("CREATE", "StationA_Solar_log_" + getCurrentDate() + ".txt");
            metadataManager.updateMetadata("MOVE", "StationA_Solar_log_" + getCurrentDate() + ".txt");
            metadataManager.updateMetadata("ARCHIVE", "StationA_Solar_log_" + getCurrentDate() + ".txt");
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
