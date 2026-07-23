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
package com.restfb.batch;

import com.restfb.Facebook;
import com.restfb.util.ReflectionUtils;

/**
 * Represents an HTTP header name/value pair used by {@link BatchRequest} and {@link BatchResponse}.
 *
 * @author <a href="http://restfb.com">Mark Allen</a>
 * @since 1.6.5
 */
public class BatchHeader {

    @Facebook
    private String name;

    @Facebook
    private String value;

    /**
     * "Magic" no-argument constructor so we can reflectively make instances of this class with DefaultJsonMapper, but
     * normal client code cannot.
     */
    protected BatchHeader() {
        // nothing here
    }

    /**
     * Creates a {@code BatchHeader} with the given name/value pair.
     *
     * @param name
     *          The name of the header.
     * @param value
     *          The value of the header.
     */
    public BatchHeader(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(Object that) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
