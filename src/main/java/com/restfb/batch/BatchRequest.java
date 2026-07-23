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
package com.restfb.batch;

import static com.restfb.util.UrlUtils.urlEncode;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.restfb.Facebook;
import com.restfb.Parameter;
import com.restfb.util.ReflectionUtils;

/**
 * Encapsulates a discrete part of an entire
 * <a href="https://developers.facebook.com/docs/reference/api/batch/" target="_blank">Facebook Batch API</a> request.
 * <p>
 * Must be constructed by {@link BatchRequestBuilder}.
 *
 * @author <a href="http://restfb.com">Mark Allen</a>
 * @since 1.6.5
 * @see BatchRequestBuilder
 */
public class BatchRequest {

    @Facebook
    private String method;

    @Facebook("relative_url")
    private String relativeUrl;

    @Facebook
    private String body;

    @Facebook("attached_files")
    private String attachedFiles;

    @Facebook("depends_on")
    private String dependsOn;

    @Facebook
    private String name;

    @Facebook("omit_response_on_success")
    private boolean omitResponseOnSuccess;

    @Facebook
    private List<BatchHeader> headers;

    /**
     * Designed to be invoked by instances of <tt>{@link BatchRequestBuilder}</tt> .
     *
     * @param relativeUrl
     *          The endpoint to hit, for example {@code "me/friends"}.
     * @param parameters
     *          Optional list of URL parameters to be added to the value specified in {@code relativeUrl}.
     * @param method
     *          The HTTP method to use, for example {@code "GET"}.
     * @param headers
     *          The list of HTTP headers for the request.
     * @param bodyParameters
     *          The parameters that comprise the request body, for example {@code "message=Test status update"} .
     * @param attachedFiles
     *          Names of any attached files for this call, for example {@code "cat1, cat2"}.
     * @param name
     *          The logical name of this request, for example {@code "get-friends"}.
     * @param dependsOn
     *          If this call depends on the completion of another call in the current batch, for example
     *          {@code "get-friends"}.
     * @param omitResponseOnSuccess
     *          To make sure FB returns JSON in the event that this request completes successfully, set this to
     *          {@code false}.
     * @throws IllegalArgumentException
     *           If {@code relativeUrl} is {@code null}.
     */
    protected BatchRequest(String relativeUrl, List<Parameter> parameters, String method, List<BatchHeader> headers, List<Parameter> bodyParameters, String attachedFiles, String dependsOn, String name, boolean omitResponseOnSuccess) {
        this.relativeUrl = Optional.ofNullable(relativeUrl).orElseThrow(() -> new IllegalArgumentException("The 'relativeUrl' parameter is required."));
        this.method = method;
        this.headers = headers;
        this.attachedFiles = attachedFiles;
        this.dependsOn = dependsOn;
        this.name = name;
        this.omitResponseOnSuccess = omitResponseOnSuccess;
        if (!parameters.isEmpty()) {
            this.relativeUrl = format(!this.relativeUrl.contains("?") ? "%s?%s" : "%s&%s", this.relativeUrl, generateParameterString(parameters));
        }
        this.body = generateParameterString(bodyParameters);
    }

    /**
     * Builder pattern implementation used to construct instances of <tt>{@link BatchRequest}</tt>.
     * <p>
     * See the <a href="https://developers.facebook.com/docs/reference/api/batch/" target="_blank">Facebook Batch API
     * documentation</a> for more details on what a batch request looks like.
     *
     * @author <a href="http://restfb.com">Mark Allen</a>
     * @since 1.6.5
     */
    public static class BatchRequestBuilder {

        private String method = "GET";

        private String relativeUrl;

        private List<Parameter> parameters = new ArrayList<>();

        private List<BatchHeader> headers = new ArrayList<>();

        private List<Parameter> bodyParameters = new ArrayList<>();

        private String attachedFiles;

        private String dependsOn;

        private String name;

        private boolean omitResponseOnSuccess;

        /**
         * Creates a batch request builder using the provided FB endpoint.
         * <p>
         * You can explicitly specify URL parameters here, or use {@link #parameters(Parameter...)} instead if you prefer to
         * have the query string constructed programmatically.
         *
         * @param relativeUrl
         *          The endpoint to hit, for example {@code "me/friends"}.
         */
        public BatchRequestBuilder(String relativeUrl) {
            this.relativeUrl = relativeUrl;
        }

        public BatchRequestBuilder method(String method) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public BatchRequestBuilder name(String name) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public BatchRequestBuilder headers(BatchHeader... headers) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public BatchRequestBuilder body(Parameter... parameters) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public BatchRequestBuilder attachedFiles(String attachedFiles) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public BatchRequestBuilder dependsOn(String dependsOn) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public BatchRequestBuilder omitResponseOnSuccess(boolean omitResponseOnSuccess) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public BatchRequestBuilder parameters(Parameter... parameters) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public BatchRequest build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    protected String generateParameterString(List<Parameter> parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(Object that) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getMethod() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getRelativeUrl() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getBody() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getAttachedFiles() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getDependsOn() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isOmitResponseOnSuccess() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<BatchHeader> getHeaders() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
