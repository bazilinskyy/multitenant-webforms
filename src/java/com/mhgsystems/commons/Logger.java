/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.commons;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.*;

/**
 *
 * @author Veli-Matti Plosila
 */
public class Logger  {

    public static final String APPLICATION_CODE = "PUBLIC";

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger("mhg");
    private static final Logger INSTANCE = new Logger();

    /**
     * Creates a new instance of Logger
     * DO NOT USE!
     * Use getInstance() instead
     */
    private Logger() {
        this.log("Logger init");
    }

    /**
     * Get instance of MHG Logger
     */
    public static Logger getInstance() {
        return INSTANCE;
    }

    /**
     * Logging (LogLevel is always INFO)
     * <br><br>Can be used for ex. logging code execution order
     * <ul>
     * <li>Application & LogLevel</li>
     * <li>FileName</li>
     * <li>LineNumber</li>
     * <li>ClassName</li>
     * <li>MethodName</li>
     * </ul>
     *
     */
    public void log() {
        writeLogOutput(Level.INFO, null, null, false);
    }

    /**
     * Logging (LogLevel is always INFO)
     * <br><br>Short INFO level message
     * <ul>
     * <li>Application & LogLevel</li>
     * <li>Message</li>
     * </ul>
     * @param message
     */
    public void log(String message) {
        writeLogOutput(Level.INFO, null, message, true);
    }

    /**
     * Logging (LogLevel is always SEVERE)
     * <br><br>Standard logging method for Exception without message
     * <ul>
     * <li>Application & LogLevel</li>
     * <li>FileName</li>
     * <li>LineNumber</li>
     * <li>ClassName</li>
     * <li>MethodName</li>
     * <li>Exception (StackTrace)</li>
     * </ul>

     * @param exception
     */
    public void log(Exception exception) {
        writeLogOutput(Level.SEVERE, exception, null, false);
    }

    /**
     * Logging (LogLevel is always SEVERE)
     * <br><br>Standard logging method for Exception
     * <br>with message for ex. showing parameters
     * <ul>
     * <li>Application & LogLevel</li>
     * <li>FileName</li>
     * <li>LineNumber</li>
     * <li>ClassName</li>
     * <li>MethodName</li>
     * <li>Message</li>
     * <li>Exception (StackTrace)</li>
     * </ul>
     *
     * @param exception
     * @param message
     */
    public void log(Exception exception, String message) {
        writeLogOutput(Level.SEVERE, exception, message, false);
    }

    /**
     * Old standard logger, do not use on new code!
     * <br>
     * <br>If message is not needed, use:
     * <br><code>log(Exception exception)</code>
     * <br>
     * <br>If message for ex. parameters is needed, use:
     * <br><code>log(Exception exception, String message)</code>
     *
     * @param message
     * @param exception
     * @deprecated
     */
    @Deprecated
    public void log(String message, Exception exception) {
        writeLogOutput(Level.SEVERE, exception, message, false);
    }

    /**
     * Logging (with custom LogLevel)
     * <br><br>Can be used to log messages with Different LogLevels
     * <br>For simple INFO level message use method: <code>log(String message)</code>
     * <ul>
     * <li>Application & LogLevel</li>
     * <li>FileName</li>
     * <li>LineNumber</li>
     * <li>ClassName</li>
     * <li>MethodName</li>
     * <li>Message</li>
     * </ul>
     *
     * @param level
     * @param message
     */
    public void log(Level level, String message) {
        writeLogOutput(level, null, message, false);
    }

    /**
     * Logging (with custom LogLevel)
     * <br><br>Can be used for ex. low level exception handling
     * <br>For standard SEVERE level exception logging use method:
     * <br><code>log(Exception exception)</code> or <code>log(Exception exception, String message)</code>
     * <ul>
     * <li>Application & LogLevel</li>
     * <li>FileName</li>
     * <li>LineNumber</li>
     * <li>ClassName</li>
     * <li>MethodName</li>
     * <li>Message</li>
     * <li>Exception (StackTrace)</li>
     * </ul>
     *
     * @param level
     * @param exception
     * @param message
     */
    public void log(Level level, Exception exception, String message) {
        writeLogOutput(level, exception, message, false);
    }

    /**
     * Used for all log writing. Only for INTERNAL use!
     * <br><br>parameter forceShortVersion fill disable following information:
     * <br><code>FileName, LineNumber, ClassName and MethodName</code>
     *
     * @param level
     * @param exception
     * @param message
     * @param forceShortVersion
     */
    private void writeLogOutput(Level level, Exception exception, String message, boolean forceShortVersion) {
        try {
            /*
             * index 1 is this method
             * index 2 is original log-method in stack trace
             * index 3 is where the original log-method is called
             */
            int index = 3;

            Map<String, String> LogParameters = new LinkedHashMap<String, String>();

            // Show application code always
            LogParameters.put("Application", APPLICATION_CODE + ", Level: " + level.getName());

            // Do not add log parameters for info level messages if message exists
            if (!forceShortVersion) {
                LogParameters.put("FileName", Thread.currentThread().getStackTrace()[index].getFileName());
                LogParameters.put("LineNumber", String.valueOf(Thread.currentThread().getStackTrace()[index].getLineNumber()));
                LogParameters.put("ClassName", Thread.currentThread().getStackTrace()[index].getClassName());
                LogParameters.put("MethodName", Thread.currentThread().getStackTrace()[index].getMethodName());
            }

            if (message != null) {
                // Add message if exists
                LogParameters.put("Message", message);
            }

            // Create Log message
            StringBuilder sb = new StringBuilder();

            sb.append("\n");
            sb.append("################################# ");

            for (Entry<String, String> entry : LogParameters.entrySet()) {
                sb.append("\n - ");
                sb.append(entry.getKey());
                sb.append(" : ");
                sb.append(entry.getValue());
            }

            if (exception != null) {
                sb.append("\n - Exception: ");
                LOGGER.log(level, sb.toString(), exception);
            } else {
                LOGGER.log(level, sb.toString());
            }

        } catch (Exception ex) {
            // Logger internal error
            LOGGER.log(Level.SEVERE, "Logger.writeLogOutput(Level level, String message, Exception exception)", ex);
        }
    }
}
