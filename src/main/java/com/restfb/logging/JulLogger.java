/*
 * Copyright (c) 2010-2026 Mark Allen, Norbert Bartels.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.restfb.logging;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Logger implementation based on {@code java.util.logging}.
 *
 * The JUL configuration should be provided by a external application. The mapping is defined like:
 * <ul>
 * <li>trace maps to java.util.logging.Level.<i>FINER</i></li>
 * <li>debug maps to java.util.logging.Level.<i>FINE</i></li>
 * <li>info maps to java.util.logging.Level.<i>INFO</i></li>
 * <li>warn maps to java.util.logging.Level.<i>WARNING</i></li>
 * <li>error maps to java.util.logging.Level.<i>SEVERE</i></li>
 * <li>fatal maps to java.util.logging.Level.<i>SEVERE</i></li>
 * </ul>
 */
public class JulLogger extends RestFBLogger {

    private final Logger logger;

    public JulLogger(String logName) {
        logger = Logger.getLogger(logName);
    }

    @Override
    public void trace(String msg, Object... args) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void debug(String msg, Object... args) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void info(String msg, Object... args) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void warn(String msg, Object... args) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void error(String msg, Object... args) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void fatal(String msg, Object... args) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isDebugEnabled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isInfoEnabled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isTraceEnabled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void createLogMessage(Level level, String msg, Object[] args) {
        if (logger.isLoggable(level)) {
            JulMessage.MessageTuple tuple = JulMessage.convertMessageString(msg, args);
            LogRecord logRecord = new LogRecord(level, tuple.getMessage());
            Optional.ofNullable(tuple.getThrowable()).ifPresent(logRecord::setThrown);
            logRecord.setSourceClassName(null);
            logRecord.setSourceMethodName(null);
            logRecord.setLoggerName(logger.getName());
            logger.log(logRecord);
        }
    }
}
