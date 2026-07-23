/**
 * ****************************************************************************
 *  Copyright (c) 2016 EclipseSource.
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

/**
 * A handler for parser events. Instances of this class can be given to a {@link JsonParser}. The
 * parser will then call the methods of the given handler while reading the input.
 * <p>
 * The default implementations of these methods do nothing. Subclasses may override only those
 * methods they are interested in. They can use <code>getLocation()</code> to access the current
 * character position of the parser at any point. The <code>start*</code> methods will be called
 * while the location points to the first character of the parsed element. The <code>end*</code>
 * methods will be called while the location points to the character position that directly follows
 * the last character of the parsed element. Example:
 * </p>
 *
 * <pre>
 * ["lorem ipsum"]
 *  ^            ^
 *  startString  endString
 * </pre>
 * <p>
 * Subclasses that build an object representation of the parsed JSON can return arbitrary handler
 * objects for JSON arrays and JSON objects in {@link #startArray()} and {@link #startObject()}.
 * These handler objects will then be provided in all subsequent parser events for this particular
 * array or object. They can be used to keep track the elements of a JSON array or object.
 * </p>
 *
 * @param <A>
 *          The type of handlers used for JSON arrays
 * @param <O>
 *          The type of handlers used for JSON objects
 * @see JsonParser
 */
public abstract class JsonHandler<A, O> {

    JsonParser parser;

    protected Location getLocation() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void startNull() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void endNull() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void startBoolean() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void endBoolean(boolean value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void startString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void endString(String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void startNumber() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void endNumber(String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public A startArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void endArray(A array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void startArrayValue(A array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void endArrayValue(A array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public O startObject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void endObject(O object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void startObjectName(O object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void endObjectName(O object, String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void startObjectValue(O object, String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void endObjectValue(O object, String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
