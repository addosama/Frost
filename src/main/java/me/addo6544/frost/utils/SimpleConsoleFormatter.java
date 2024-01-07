package me.addo6544.frost.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * 为 {@link java.util.logging.Logger}实现自定义的日志输出，可以输出IDE(eclipse)自动识别源码位置的日志格式。方便调试
 * @author guyadong
 * @since 2.7.0
 */
public class SimpleConsoleFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        String message = formatMessage(record);
        String throwable = "";
        if (record.getThrown() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            pw.println();
            record.getThrown().printStackTrace(pw);
            pw.close();
            throwable = "\n" + sw.toString();
        }
        Thread currentThread = Thread.currentThread();
        StackTraceElement stackTrace = currentThread.getStackTrace()[8];
        return String.format("[%s] (%s:%d) %s%s\n",
                Thread.currentThread().getName(),
                stackTrace.getFileName(),
                stackTrace.getLineNumber(),
                message,
                throwable);
    }
    /**
     * 将{@link SimpleConsoleFormatter}实例指定为{@link Logger}的输出格式
     * @param logger
     * @return always logger
     */
    public static Logger installFormatter(Logger logger){
        if(null != logger){
            /** 禁用原输出handler,否则会输出两次 */
            logger.setUseParentHandlers(false);
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleConsoleFormatter());
            logger.addHandler(consoleHandler);
        }
        return logger;
    }
}

