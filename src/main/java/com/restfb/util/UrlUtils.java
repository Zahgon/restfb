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

import static java.lang.String.format;
import static java.net.URLDecoder.decode;
import static java.net.URLEncoder.encode;
import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toList;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author <a href="http://restfb.com">Mark Allen</a>
 * @since 1.6.10
 */
public final class UrlUtils {

    /**
     * Prevents instantiation.
     */
    private UrlUtils() {
        throw new IllegalStateException("UrlUtils must not be instantiated");
    }

    public static String urlEncode(String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String urlDecode(String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, List<String>> extractParametersFromQueryString(String queryString) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, List<String>> extractParametersFromUrl(String url) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String replaceOrAddQueryParameter(String url, String key, String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String removeQueryParameter(String url, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
