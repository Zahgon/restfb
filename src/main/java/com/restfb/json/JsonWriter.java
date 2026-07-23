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
import java.io.Writer;

class JsonWriter {

    private static final int CONTROL_CHARACTERS_END = 0x001f;

    protected static final char[] NO_CHARS = {};

    protected static final char[] QUOT_CHARS = { '\\', '"' };

    protected static final char[] BS_CHARS = { '\\', '\\' };

    protected static final char[] LF_CHARS = { '\\', 'n' };

    protected static final char[] CR_CHARS = { '\\', 'r' };

    protected static final char[] TAB_CHARS = { '\\', 't' };

    protected static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    // In JavaScript, U+2028 and U+2029 characters count as line endings and must be encoded.
    // http://stackoverflow.com/questions/2965293/javascript-parse-error-on-u2028-unicode-character
    private static final char[] UNICODE_2028_CHARS = { '\\', 'u', '2', '0', '2', '8' };

    private static final char[] UNICODE_2029_CHARS = { '\\', 'u', '2', '0', '2', '9' };

    protected final Writer writer;

    JsonWriter(Writer writer) {
        this.writer = writer;
    }

    protected void writeLiteral(String value) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void writeNumber(String string) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void writeString(String string) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void writeArrayOpen() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void writeArrayClose() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void writeArraySeparator() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void writeObjectOpen() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void writeObjectClose() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void writeMemberName(String name) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void writeMemberSeparator() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void writeObjectSeparator() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void writeJsonString(String string) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected char[] getReplacementChars(char ch) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
