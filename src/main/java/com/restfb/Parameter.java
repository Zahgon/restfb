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
package com.restfb;

import static com.restfb.util.StringUtils.isBlank;
import static com.restfb.util.StringUtils.trimToEmpty;
import static java.lang.String.format;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import com.restfb.exception.FacebookJsonMappingException;

/**
 * Representation of a Facebook API request parameter.
 *
 * @author <a href="http://restfb.com">Mark Allen</a>
 */
public final class Parameter {

    /**
     * Parameter name.
     */
    public final String name;

    /**
     * Parameter value.
     */
    public final String value;

    /**
     * Creates a new parameter with the given {@code name} and {@code value}.
     *
     * @param name
     *          The parameter name.
     * @param value
     *          The parameter value.
     * @param jsonMapper
     *          Mapper for converting the parameter value to JSON.
     * @throws IllegalArgumentException
     *           If {@code name} is {@code null} or a blank string or either {@code value} or {@code jsonMapper} is
     *           {@code null}.
     */
    private Parameter(String name, Object value, JsonMapper jsonMapper) {
        if (isBlank(name) || value == null) {
            throw new IllegalArgumentException(Parameter.class + " instances must have a non-blank name and non-null value." + " Got instead name:" + name + ", value:" + value);
        }
        this.value = Optional.ofNullable(jsonMapper).orElseThrow(() -> new IllegalArgumentException("Provided " + JsonMapper.class + " must not be null.")).toJson(value, true);
        this.name = trimToEmpty(name);
    }

    public static Parameter with(String name, Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Parameter withFields(String fieldList) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Parameter withMetadata() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Parameter withLocale(Locale locale) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Parameter withLimit(int limit) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Parameter withMessage(Object obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Parameter withQuery(String queryString) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Parameter with(String name, Object value, JsonMapper jsonMapper) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
