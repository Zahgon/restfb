/**
 * ****************************************************************************
 *  Copyright (c) 2013, 2017 EclipseSource.
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
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Objects;

/**
 * Represents a JSON value. This can be a JSON <strong>object</strong>, an <strong> array</strong>, a
 * <strong>number</strong>, a <strong>string</strong>, or one of the literals
 * <strong>true</strong>, <strong>false</strong>, and <strong>null</strong>.
 * <p>
 * The literals <strong>true</strong>, <strong>false</strong>, and <strong>null</strong> are
 * represented by the constants {@link Json#TRUE}, {@link Json#FALSE}, and {@link Json#NULL}.
 * </p>
 * <p>
 * JSON <strong>objects</strong> and <strong>arrays</strong> are represented by the subtypes
 * {@link JsonObject} and {@link JsonArray}. Instances of these types can be created using the public constructors of these classes.
 * </p>
 * <p>
 * Instances that represent JSON <strong>numbers</strong>, <strong>strings</strong> and
 * <strong>boolean</strong> values can be created using the static factory methods
 * {@link Json#value(String)}, {@link Json#value(long)}, {@link Json#value(double)}, etc.
 * </p>
 * <p>
 * In order to find out whether an instance of this class is of a certain type, the methods
 * {@link #isObject()}, {@link #isArray()}, {@link #isString()}, {@link #isNumber()} etc. can be used.
 * </p>
 * <p>
 * If the type of a JSON value is known, the methods {@link #asObject()}, {@link #asArray()}, {@link #asString()},
 * {@link #asInt()}, etc. can be used to get this value directly in the appropriate target type.
 * </p>
 * <p>
 * This class is <strong>not supposed to be extended</strong> by clients.
 * </p>
 */
// use default serial UID
@SuppressWarnings("serial")
public abstract class JsonValue implements Serializable {

    // String constants
    private static final String NOT_A_NUMBER = "Not a number: ";

    private static final String NOT_A_STRING = "Not a string: ";

    private static final String NOT_A_BOOLEAN = "Not a boolean: ";

    // String constants
    protected static final String STRING_IS_NULL = "string is null";

    protected static final String OBJECT_IS_NULL = "object is null";

    protected static final String NAME_IS_NULL = "name is null";

    protected static final String VALUE_IS_NULL = "value is null";

    protected static final String ARRAY_IS_NULL = "array is null";

    JsonValue() {
        // prevent subclasses outside of this package
    }

    public boolean isObject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isNumber() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isBoolean() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isTrue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isFalse() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isNull() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonObject asObject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonArray asArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int asInt() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public long asLong() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public float asFloat() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public double asDouble() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String asString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean asBoolean() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void writeTo(Writer writer) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void writeTo(Writer writer, WriterConfig config) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String toString(WriterConfig config) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    abstract void write(JsonWriter writer) throws IOException;
}
