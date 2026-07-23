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
package com.restfb.util;

import static com.restfb.logging.RestFBLogger.UTILS_LOGGER;
import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

/**
 * A collection of date-handling utility methods.
 *
 * @author <a href="http://restfb.com">Mark Allen</a>
 * @since 1.6
 */
public final class DateUtils {

    /**
     * Facebook "long" date format (IETF RFC 3339). Example: {@code 2010-02-28T16:11:08+0000}
     */
    public static final String FACEBOOK_LONG_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    /**
     * Facebook "long" date format (IETF RFC 3339) without a timezone component. Example: {@code 2010-02-28T16:11:08}
     */
    public static final String FACEBOOK_LONG_DATE_FORMAT_WITHOUT_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * Facebook "long" date format (IETF RFC 3339) without a timezone or seconds component. Example:
     * {@code 2010-02-28T16:11}
     */
    public static final String FACEBOOK_LONG_DATE_FORMAT_WITHOUT_TIMEZONE_OR_SECONDS = "yyyy-MM-dd'T'HH:mm";

    /**
     * Facebook short date format. Example: {@code 04/15/1984}
     */
    public static final String FACEBOOK_SHORT_DATE_FORMAT = "MM/dd/yyyy";

    /**
     * Facebook alternate short date format. Example: {@code 2012-09-15}
     */
    public static final String FACEBOOK_ALTERNATE_SHORT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Facebook month-year only date format. Example: {@code Example: 2007-03}
     */
    public static final String FACEBOOK_MONTH_YEAR_DATE_FORMAT = "yyyy-MM";

    /**
     * DateFormatStrategy (default: SimpleDateFormat).
     */
    private static DateFormatStrategy strategy = new SimpleDateFormatStrategy();

    /**
     * Prevents instantiation.
     */
    private DateUtils() {
        throw new IllegalStateException("DateUtils must not be instantiated");
    }

    public static Date toDateFromLongFormat(String date) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static boolean isNull(Object date) {
        return date == null;
    }

    public static Date toDateFromShortFormat(String date) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Date toDateFromMonthYearFormat(String date) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String toLongFormatFromDate(Date date) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String toShortFormatFromDate(Date date) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a Java representation of a {@code date} string.
     *
     * @param date
     *          Date in string format.
     * @return Java date representation of the given {@code date} string or {@code null} if {@code date} is {@code null}
     *         or invalid.
     */
    private static Date toDateWithFormatString(String date, String format) {
        if (isNull(date)) {
            return null;
        }
        try {
            return strategy.formatFor(format).parse(date);
        } catch (ParseException e) {
            UTILS_LOGGER.trace("Unable to parse date '{}' using format string '{}': {}", date, format, e);
            return null;
        }
    }

    public static DateFormatStrategy getDateFormatStrategy() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void setDateFormatStrategy(DateFormatStrategy dateFormatStrategy) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
