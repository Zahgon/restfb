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

import static com.restfb.util.StringUtils.isBlank;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class ObjectUtil {

    private ObjectUtil() {
        // prevent instantiation
    }

    public static String requireNotEmpty(String obj, String errorText) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void requireNotEmpty(Collection<?> collection, String errorText) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T extends Exception> void requireNotNull(Object obj, Supplier<T> exceptionSupplier) throws T {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isEmptyCollectionOrMap(Object obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void verifyParameterPresence(String parameterName, String parameter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void verifyParameterPresence(String parameterName, Object parameter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
