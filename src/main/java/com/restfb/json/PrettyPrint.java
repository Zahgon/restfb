/**
 * ****************************************************************************
 *  Copyright (c) 2015 EclipseSource.
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
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enables human readable JSON output by inserting whitespace between values.after commas and colons. Example:
 *
 * <pre>
 * jsonValue.writeTo(writer, PrettyPrint.singleLine());
 * </pre>
 */
public class PrettyPrint implements WriterConfig {

    private final char[] indentChars;

    protected PrettyPrint(char[] indentChars) {
        this.indentChars = indentChars;
    }

    public static PrettyPrint singleLine() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static PrettyPrint indentWithSpaces(int number) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static PrettyPrint indentWithTabs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public JsonWriter createWriter(Writer writer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static class PrettyPrintWriter extends JsonWriter {

        private final char[] indentChars;

        private int indent;

        private PrettyPrintWriter(Writer writer, char[] indentChars) {
            super(writer);
            this.indentChars = indentChars;
        }

        @Override
        protected void writeArrayOpen() throws IOException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected void writeArrayClose() throws IOException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected void writeArraySeparator() throws IOException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected void writeObjectOpen() throws IOException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected void writeObjectClose() throws IOException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected void writeMemberSeparator() throws IOException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected void writeObjectSeparator() throws IOException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected void writeCommaSeparator() throws IOException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private boolean writeNewLine() throws IOException {
            if (indentChars == null) {
                return false;
            }
            writer.write('\n');
            writer.write(Stream.generate(() -> String.valueOf(indentChars)).limit(indent).collect(Collectors.joining()));
            return true;
        }
    }
}
