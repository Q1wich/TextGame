package Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Logger {
    private static Logger instance;
    private final String logFilePath;

    private Logger() {
        this.logFilePath = "C:\\BattleLog\\battle_log.txt";
        createLogDirectory();
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    private void createLogDirectory() {
        File directory = new File("C:\\BattleLog");
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public void logWithPrint(String command) {
        System.out.println(command);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            writer.write(command);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String command) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            writer.write(command);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void logList(List<String> commands) {
        StringBuilder logEntry = new StringBuilder();
        for (String command : commands) {
            logEntry.append(command).append("\n");
        }
        logEntry.append("\n");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            writer.write(logEntry.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

