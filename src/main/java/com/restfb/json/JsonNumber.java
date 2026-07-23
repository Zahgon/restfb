/**
 * ****************************************************************************
 *  Copyright (c) 2013, 2015 EclipseSource.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 * ****************************************************************************
 */
package com.restfb.json;

import java.io.IOException;
import java.util.Objects;

// use default serial UID
@SuppressWarnings("serial")
class JsonNumber extends JsonValue {

    private final String numberStr;

    JsonNumber(String numberStr) {
        Objects.requireNonNull(numberStr, STRING_IS_NULL);
        this.numberStr = numberStr;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void write(JsonWriter writer) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isNumber() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int asInt() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long asLong() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public float asFloat() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public double asDouble() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
