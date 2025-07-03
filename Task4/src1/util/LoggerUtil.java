package util;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public class LoggerUtil {
	private static Logger logger;

	static {
        try {
            // Create logs folder if not exists
            File logDir = new File("logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
                System.out.println("Logs directory created.");
            } else {
                System.out.println("Logs directory already exists.");
            }

            // Define log file path
            String logFilePath = "logs/studentapp.log";
            System.out.println("Log file path: " + logFilePath);

            // Set up file handler
            FileHandler fileHandler = new FileHandler(logFilePath, true);
            fileHandler.setFormatter(new SimpleFormatter());

            logger = Logger.getLogger(LoggerUtil.class.getName());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);

            // Also log to console
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            logger.addHandler(consoleHandler);

            // Check if log file exists after setup
            File logFile = new File(logFilePath);
            System.out.println("Log file exists? " + logFile.exists());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger() {
        return logger;
    }

}
