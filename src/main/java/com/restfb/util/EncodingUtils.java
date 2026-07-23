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

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * A collection of data-encoding utility methods.
 *
 * @author Josef Gierbl
 * @author Mikael Grev
 * @author <a href="http://restfb.com">Mark Allen</a>
 * @since 1.6.13
 */
public final class EncodingUtils {

    /**
     * Prevents instantiation.
     */
    private EncodingUtils() {
    }

    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();

    public static byte[] decodeBase64(String base64) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static String padBase64(String base64) {
        String padding = "";
        int remainder = base64.length() % 4;
        if (remainder > 0) {
            padding = IntStream.range(0, 4 - remainder).mapToObj(i -> "=").collect(Collectors.joining());
        }
        return base64 + padding;
    }

    public static byte[] encodeHex(final byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String encodeAppSecretProof(String appSecret, String accessToken) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
