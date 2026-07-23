/**
 * ****************************************************************************
 *  Copyright (c) 2015, 2016 EclipseSource.
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
import java.io.Reader;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class serves as the entry point to the minimal-json API.
 * <p>
 * To <strong>parse</strong> a given JSON input, use the <code>parse()</code> methods like in this example:
 * </p>
 *
 * <pre>
 * JsonObject object = Json.parse(string).asObject();
 * </pre>
 * <p>
 * To <strong>create</strong> a JSON data structure to be serialized, use the methods <code>value()</code>,
 * <code>array()</code>, and <code>object()</code>. For example, the following snippet will produce the JSON string
 * <em>{"foo": 23, "bar": true}</em>:
 * </p>
 *
 * <pre>
 * String string = Json.object().add("foo", 23).add("bar", true).toString();
 * </pre>
 * <p>
 * To create a JSON array from a given Java array, you can use one of the <code>array()</code> methods with varargs
 * parameters:
 * </p>
 *
 * <pre>
 * String[] names = ...
 * JsonArray array = Json.array(names);
 * </pre>
 */
public final class Json {

    // String constants
    private static final String VALUES_IS_NULL = "values is null";

    private static final String STRING_IS_NULL = "string is null";

    private static final String READER_IS_NULL = "reader is null";

    private static final String INFINITE_AND_NAN = "Infinite and NaN values not permitted in JSON";

    private Json() {
        // not meant to be instantiated
    }

    /**
     * Represents the JSON literal <code>null</code>.
     */
    public static final JsonValue NULL = new JsonLiteral("null");

    /**
     * Represents the JSON literal <code>true</code>.
     */
    public static final JsonValue TRUE = new JsonLiteral("true");

    /**
     * Represents the JSON literal <code>false</code>.
     */
    public static final JsonValue FALSE = new JsonLiteral("false");

    public static JsonValue value(int value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static JsonValue value(long value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static JsonValue value(float value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static JsonValue value(double value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static JsonValue value(String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static JsonValue value(boolean value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static JsonArray array() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static JsonArray array(int... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static JsonArray array(long... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static JsonArray array(float... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static JsonArray array(double... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static JsonArray array(boolean... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static JsonArray array(String... strings) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static JsonObject object() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static JsonValue parse(String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static JsonValue parse(Reader reader) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static String cutOffPointZero(String string) {
        if (string.endsWith(".0")) {
            return string.substring(0, string.length() - 2);
        }
        return string;
    }

    static class DefaultHandler extends JsonHandler<JsonArray, JsonObject> {

        protected JsonValue value;

        @Override
        public JsonArray startArray() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public JsonObject startObject() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void endNull() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void endBoolean(boolean bool) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void endString(String string) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void endNumber(String string) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void endArray(JsonArray array) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void endObject(JsonObject object) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void endArrayValue(JsonArray array) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void endObjectValue(JsonObject object, String name) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        JsonValue getValue() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
