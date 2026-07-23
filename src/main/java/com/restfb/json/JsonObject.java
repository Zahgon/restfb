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
import java.io.ObjectInputStream;
import java.util.*;
import com.restfb.json.JsonObject.Member;

/**
 * Represents a JSON object, a set of name/value pairs, where the names are strings and the values are JSON values.
 * <p>
 * Members can be added using the <code>add(String, ...)</code> methods which accept instances of {@link JsonValue},
 * strings, primitive numbers, and boolean values. To modify certain values of an object, use the
 * <code>set(String, ...)</code> methods. Please note that the <code>add</code> methods are faster than <code>set</code>
 * as they do not search for existing members. On the other hand, the <code>add</code> methods do not prevent adding
 * multiple members with the same name. Duplicate names are discouraged but not prohibited by JSON.
 * </p>
 * <p>
 * Members can be accessed by their name using {@link #get(String)}. A list of all names can be obtained from the method
 * {@link #names()}. This class also supports iterating over the members in document order using an {@link #iterator()}
 * or an enhanced for loop:
 * </p>
 *
 * <pre>
 * for (Member member : jsonObject) {
 *   String name = member.getName();
 *   JsonValue value = member.getValue();
 *   ...
 * }
 * </pre>
 * <p>
 * Even though JSON objects are unordered by definition, instances of this class preserve the order of members to allow
 * processing in document order and to guarantee a predictable output.
 * </p>
 * <p>
 * Note that this class is <strong>not thread-safe</strong>. If multiple threads access a <code>JsonObject</code>
 * instance concurrently, while at least one of these threads modifies the contents of this object, access to the
 * instance must be synchronized externally. Failure to do so may lead to an inconsistent state.
 * </p>
 * <p>
 * This class is <strong>not supposed to be extended</strong> by clients.
 * </p>
 */
// use default serial UID
@SuppressWarnings("serial")
public class JsonObject extends JsonValue implements Iterable<Member> {

    private final List<String> names;

    private final List<JsonValue> values;

    private transient HashIndexTable table;

    /**
     * Creates a new empty JsonObject.
     */
    public JsonObject() {
        names = new ArrayList<>();
        values = new ArrayList<>();
        table = new HashIndexTable();
    }

    /**
     * Creates a new JsonObject, initialized with the contents of the specified JSON object.
     *
     * @param object
     *          the JSON object to get the initial contents from, must not be <code>null</code>
     */
    public JsonObject(JsonObject object) {
        this(object, false);
    }

    private JsonObject(JsonObject object, boolean unmodifiable) {
        Objects.requireNonNull(object, OBJECT_IS_NULL);
        if (unmodifiable) {
            names = Collections.unmodifiableList(object.names);
            values = Collections.unmodifiableList(object.values);
        } else {
            names = new ArrayList<>(object.names);
            values = new ArrayList<>(object.values);
        }
        table = new HashIndexTable();
        updateHashIndex();
    }

    public static JsonObject unmodifiableObject(JsonObject object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonObject add(String name, int value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonObject add(String name, long value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonObject add(String name, float value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonObject add(String name, double value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonObject add(String name, boolean value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonObject add(String name, String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonObject add(String name, JsonValue value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonObject set(String name, int value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonObject set(String name, long value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonObject set(String name, float value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonObject set(String name, double value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonObject set(String name, boolean value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonObject set(String name, String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonObject set(String name, JsonValue value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonObject remove(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean contains(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonObject merge(JsonObject object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public JsonValue get(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getInt(String name, int defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public long getLong(String name, long defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public float getFloat(String name, float defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public double getDouble(String name, double defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getString(String name, String defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> names() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Iterator<Member> iterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void write(JsonWriter writer) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isObject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public JsonObject asObject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    int indexOf(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private synchronized void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        inputStream.defaultReadObject();
        table = new HashIndexTable();
        updateHashIndex();
    }

    private void updateHashIndex() {
        int size = names.size();
        for (int i = 0; i < size; i++) {
            table.add(names.get(i), i);
        }
    }

    /**
     * Represents a member of a JSON object, a pair of a name and a value.
     */
    public static class Member {

        private final String name;

        private final JsonValue value;

        Member(String name, JsonValue value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public JsonValue getValue() {
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

    static class HashIndexTable {

        // must be a power of two
        private final byte[] hashTable = new byte[32];

        public HashIndexTable() {
            // nothing to do here
        }

        public HashIndexTable(HashIndexTable original) {
            System.arraycopy(original.hashTable, 0, hashTable, 0, hashTable.length);
        }

        void add(String name, int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        void remove(int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        int get(Object name) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private int hashSlotFor(Object element) {
            return element.hashCode() & hashTable.length - 1;
        }
    }
}
