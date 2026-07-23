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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A collection of string-handling utility methods.
 *
 * @author <a href="http://restfb.com">Mark Allen</a>
 * @since 1.6
 */
public final class StringUtils {

    /**
     * Default charset to use for encoding/decoding strings.
     */
    public static final Charset ENCODING_CHARSET = StandardCharsets.UTF_8;

    /**
     * Prevents instantiation.
     */
    private StringUtils() {
        // Prevents instantiation
    }

    public static boolean isBlank(String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isNotBlank(String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String trimToNull(String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String trimToEmpty(String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static byte[] toBytes(String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String toString(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String fromInputStream(InputStream inputStream) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Integer toInteger(String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
