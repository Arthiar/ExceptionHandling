package EnergyManagementSystemProject;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class LogFileManager {

    // Method to create log files in the "logs/" directory
    public void createLog(String station, String source) throws IOException {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String logFileName = "logs/" + station + "_" + source + "_log_" + date + ".txt";

        File logFile = new File(logFileName);

        // Ensure the "logs/" directory exists
        if (!logFile.getParentFile().exists()) {
            logFile.getParentFile().mkdirs();  // Create the "logs/" directory if it doesn't exist
        }

        // Create or append to the log file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write("Log entry for " + station + " (" + source + ") at " + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "\n");
        }

        System.out.println("[CREATE LOG] " + logFileName);
    }

    // Method to move log files to the "archived_logs" directory
    public void moveLog(String logFileName) throws IOException {
        String sourcePath = "logs/" + logFileName;
        String destinationPath = "archived_logs/" + logFileName;

        Path source = Paths.get(sourcePath);
        Path destination = Paths.get(destinationPath);

        // Check if the source file exists before attempting to move
        if (!Files.exists(source)) {
            System.out.printf("[ERROR] Source log file not found: %s\n", sourcePath);
            return;
        }

        // Ensure the "archived_logs/" directory exists
        Path destinationDir = destination.getParent();
        if (destinationDir != null && !Files.exists(destinationDir)) {
            Files.createDirectories(destinationDir);  // Create the "archived_logs/" directory if it doesn't exist
        }

        // Move the file to the archive directory
        Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
        System.out.printf("[MOVE LOG] %-40s | Moved to: archived_logs/ | Time: %s\n", logFileName, new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }

    // Method to archive log files into a .zip file in "archived_logs/"
    public void archiveLog(String logFileName) throws IOException {
        String archiveName = "archived_logs/" + logFileName + ".zip";
        File logFile = new File("archived_logs/" + logFileName);

        // Check if log file exists before archiving
        if (!logFile.exists()) {
            System.out.printf("[ERROR] Log file not found for archiving: %s\n", logFile.getAbsolutePath());
            return;
        }

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(archiveName))) {
            zos.putNextEntry(new ZipEntry(logFile.getName()));
            Files.copy(logFile.toPath(), zos);
            zos.closeEntry();
        }

        System.out.printf("[ARCHIVE LOG] %s archived.\n", logFileName);
    }

    // Method to delete log files from the file system
    public void deleteLog(String logFileName) {
        File logFile = new File(logFileName);
        if (logFile.exists() && logFile.delete()) {
            System.out.printf("[DELETE LOG] %s deleted.\n", logFileName);
        } else {
            System.out.printf("[ERROR] Failed to delete log: %s\n", logFileName);
        }
    }
    
    // Create daily logs for each station and source (for Part 1 of the new functionality)
    public void createDailyLogs(String[] stations, String[] sources) throws IOException {

        // Create logs for each station and source
        for (String station : stations) {
            for (String source : sources) {
                createLog(station, source);  // Use the existing createLog method to create the log file
            }
        }

        // Update the system-wide log for the entire system
        createLog("WholeSystem", "AllSources");  // Log for system as a whole
    }
    
    // Search and open log files based on equipment name or date (for Part 2 of the new functionality)
    public void openLogFile(String equipmentName, String date) {
        // Directory where log files are stored
        File logDir = new File("logs/");
        File[] files = logDir.listFiles();
        
        if (files == null) {
            System.out.println("[ERROR] No log files found.");
            return;
        }

       
    }

}
