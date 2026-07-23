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
import java.util.*;
import java.util.stream.Stream;

/**
 * Represents a JSON array, an ordered collection of JSON values.
 * <p>
 * Elements can be added using the <code>add(...)</code> methods which accept instances of {@link JsonValue}, strings,
 * primitive numbers, and boolean values. To replace an element of an array, use the <code>set(int, ...)</code> methods.
 * </p>
 * <p>
 * Elements can be accessed by their index using {@link #get(int)}. This class also supports iterating over the elements
 * in document order using an {@link #iterator()} or an enhanced for loop:
 * </p>
 *
 * <pre>
 * for (JsonValue value : jsonArray) {
 *   ...
 * }
 * </pre>
 * <p>
 * An equivalent {@link List} can be obtained from the method {@link #values()}.
 * </p>
 * <p>
 * Note that this class is <strong>not thread-safe</strong>. If multiple threads access a <code>JsonArray</code>
 * instance concurrently, while at least one of these threads modifies the contents of this array, access to the
 * instance must be synchronized externally. Failure to do so may lead to an inconsistent state.
 * </p>
 * <p>
 * This class is <strong>not supposed to be extended</strong> by clients.
 * </p>
 */
// use default serial UID
@SuppressWarnings("serial")
public class JsonArray extends JsonValue implements Iterable<JsonValue> {

    private final List<JsonValue> values;

    /**
     * Creates a new empty JsonArray.
     */
    public JsonArray() {
        values = new ArrayList<>();
    }

    /**
     * Creates a new JsonArray with the contents of the specified JSON array.
     *
     * @param array
     *          the JsonArray to get the initial contents from, must not be <code>null</code>
     */
    public JsonArray(JsonArray array) {
        this(array, false);
    }

    private JsonArray(JsonArray array, boolean unmodifiable) {
        Objects.requireNonNull(array, ARRAY_IS_NULL);
        if (unmodifiable) {
            values = Collections.unmodifiableList(array.values);
        } else {
            values = new ArrayList<>(array.values);
        }
    }

    public static JsonArray unmodifiableArray(JsonArray array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonArray add(int value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonArray add(long value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonArray add(float value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonArray add(double value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonArray add(boolean value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonArray add(String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonArray add(JsonValue value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonArray set(int index, int value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonArray set(int index, long value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonArray set(int index, float value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonArray set(int index, double value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonArray set(int index, boolean value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonArray set(int index, String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonArray set(int index, JsonValue value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonArray remove(int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonValue get(int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<JsonValue> values() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Stream<JsonValue> valueStream() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Iterator<JsonValue> iterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void write(JsonWriter writer) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public JsonArray asArray() {
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
