package Four;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Enum to represent the log levels
enum LogLevel {
    INFO,
    DEBUG,
    ERROR
}

// Command interface for log messages
interface LogCommand {
    void execute();
}

// Concrete Command that holds the log message and severity
class LogMessageCommand implements LogCommand {
    private String message;
    private LogLevel level;

    public LogMessageCommand(String message, LogLevel level) {
        this.message = message;
        this.level = level;
    }

    @Override
    public void execute() {
        System.out.println("[" + level + "] " + message);
    }

    public LogLevel getLogLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }
}

// Abstract Logger for Chain of Responsibility
abstract class Logger {
    protected Logger nextLogger;

    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(LogMessageCommand logMessage) {
        if (canHandle(logMessage.getLogLevel())) {
            write(logMessage.getMessage());
        }
        if (nextLogger != null) {
            nextLogger.logMessage(logMessage);
        }
    }

    protected abstract boolean canHandle(LogLevel level);

    protected abstract void write(String message);
}

// Concrete Logger for INFO level
class InfoLogger extends Logger {

    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.INFO;
    }

    @Override
    protected void write(String message) {
        System.out.println("INFO Logger: " + message);
    }
}

// Concrete Logger for DEBUG level
class DebugLogger extends Logger {

    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.DEBUG;
    }

    @Override
    protected void write(String message) {
        System.out.println("DEBUG Logger: " + message);
    }
}

// Concrete Logger for ERROR level
class ErrorLogger extends Logger {

    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.ERROR;
    }

    @Override
    protected void write(String message) {
        System.out.println("ERROR Logger: " + message);
    }
}

// Iterator class for log messages
class LogIterator implements Iterator<LogMessageCommand> {
    private List<LogMessageCommand> logMessages;
    private int currentPosition = 0;

    public LogIterator(List<LogMessageCommand> logMessages) {
        this.logMessages = logMessages;
    }

    @Override
    public boolean hasNext() {
        return currentPosition < logMessages.size();
    }

    @Override
    public LogMessageCommand next() {
        return logMessages.get(currentPosition++);
    }
}

// Logger System that handles logging and provides an iterator over log messages
class LoggerSystem {
    private Logger loggerChain;
    private List<LogMessageCommand> logMessages;

    public LoggerSystem() {
        // Initialize loggers and set the chain
        Logger errorLogger = new ErrorLogger();
        Logger debugLogger = new DebugLogger();
        Logger infoLogger = new InfoLogger();

        infoLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(errorLogger);

        loggerChain = infoLogger;
        logMessages = new ArrayList<>();
    }

    // Method to log a message at a given log level
    public void log(String message, LogLevel level) {
        LogMessageCommand logMessage = new LogMessageCommand(message, level);
        logMessages.add(logMessage);
        loggerChain.logMessage(logMessage);
    }

    // Method to get an iterator for log messages
    public Iterator<LogMessageCommand> getLogIterator() {
        return new LogIterator(logMessages);
    }
}

// Main class to test the LoggerSystem
public class Log {
    public static void main(String[] args) {
        LoggerSystem loggerSystem = new LoggerSystem();

        // Logging messages of different severity levels
        loggerSystem.log("This is an info message", LogLevel.INFO);
        loggerSystem.log("This is a debug message", LogLevel.DEBUG);
        loggerSystem.log("This is an error message", LogLevel.ERROR);

        // Using the iterator to display all log messages
        Iterator<LogMessageCommand> iterator = loggerSystem.getLogIterator();
        while (iterator.hasNext()) {
            LogMessageCommand logMessage = iterator.next();
            System.out.println("Logged: [" + logMessage.getLogLevel() + "] " + logMessage.getMessage());
        }
    }
}
