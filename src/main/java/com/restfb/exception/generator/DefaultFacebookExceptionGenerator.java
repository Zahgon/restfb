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
package com.restfb.exception.generator;

import static com.restfb.util.StringUtils.toInteger;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.restfb.exception.*;
import com.restfb.json.Json;
import com.restfb.json.JsonObject;
import com.restfb.json.ParseException;

public class DefaultFacebookExceptionGenerator implements FacebookExceptionGenerator {

    /**
     * Knows how to map Graph API exceptions to formal Java exception types.
     */
    protected FacebookExceptionMapper graphFacebookExceptionMapper;

    private static final Pattern ERROR_PATTERN = Pattern.compile("\"error[_a-z]*\"\\s*:");

    public DefaultFacebookExceptionGenerator() {
        super();
        graphFacebookExceptionMapper = createGraphFacebookExceptionMapper();
    }

    @Override
    public void throwFacebookResponseStatusExceptionIfNecessary(String json, Integer httpStatusCode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void throwLoginOauthExceptionIfNecessary(String json, Integer httpStatusCode) {
        JsonObject errorObject = silentlyCreateObjectFromString(json);
        if (errorObject == null || errorObject.contains(BATCH_ERROR_ATTRIBUTE_NAME)) {
            return;
        }
        errorObject = getErrorObjectIfData(errorObject);
        String errorType = errorObject.getString("error_type", null);
        Integer errorCode = errorObject.getInt("code", 0);
        String errorMessage = errorObject.getString("error_message", null);
        if (errorMessage == null && errorObject.contains("message")) {
            errorMessage = errorObject.getString("message", null);
        }
        ExceptionInformation container = new ExceptionInformation(errorCode, null, httpStatusCode, errorType, errorMessage, null, null, false, errorObject);
        throw graphFacebookExceptionMapper.exceptionForTypeAndMessage(container);
    }

    private JsonObject getErrorObjectIfData(JsonObject errorObject) {
        if (errorObject.contains("data") && errorObject.get("data").isObject()) {
            JsonObject data = errorObject.get("data").asObject();
            if (data.contains("error") && data.get("error").isObject()) {
                return data.get("error").asObject();
            }
        }
        return errorObject;
    }

    protected ExceptionInformation createFacebookResponseTypeAndMessageContainer(JsonObject errorObject, Integer httpStatusCode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void throwBatchFacebookResponseStatusExceptionIfNecessary(String json, Integer httpStatusCode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected FacebookExceptionMapper createGraphFacebookExceptionMapper() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void skipResponseStatusExceptionParsing(String json) throws ResponseErrorJsonParsingException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected JsonObject silentlyCreateObjectFromString(String json) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * A canned implementation of {@link FacebookExceptionMapper} that maps Graph API exceptions.
     * <p>
     * Thanks to BatchFB's Jeff Schnitzer for doing some of the legwork to find these exception type names.
     *
     * @author <a href="http://restfb.com">Mark Allen</a>
     * @since 1.6.3
     */
    protected static class DefaultGraphFacebookExceptionMapper implements FacebookExceptionMapper {

        @Override
        public FacebookException exceptionForTypeAndMessage(ExceptionInformation container) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
