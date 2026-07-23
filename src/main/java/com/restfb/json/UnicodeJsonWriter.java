package com.restfb.json;

import java.io.Writer;

public class UnicodeJsonWriter extends JsonWriter {

    UnicodeJsonWriter(Writer writer) {
        super(writer);
    }

    @Override
    protected char[] getReplacementChars(char ch) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
