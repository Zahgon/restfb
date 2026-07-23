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

import static com.restfb.logging.RestFBLogger.HTTP_LOGGER;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.*;
import java.util.Objects;
import com.restfb.request.MultipartFormBodyPublisher;
import com.restfb.request.TempFileBodyPublisher;
import com.restfb.types.FacebookReelAttachment;
import com.restfb.util.StringUtils;
import com.restfb.util.UrlUtils;

/**
 * Default implementation of a service that sends HTTP requests to the Facebook API endpoint.
 *
 * @author <a href="http://restfb.com">Mark Allen</a>
 */
public class DefaultWebRequestor implements WebRequestor {

    private static final String CONTENT_TYPE_JSON = "application/json";

    private static final String CONTENT_TYPE_FORM_URLENCODED = "application/x-www-form-urlencoded";

    /**
     * Arbitrary unique boundary marker for multipart {@code POST}s.
     */
    private static final String MULTIPART_BOUNDARY = "**boundarystringwhichwill**neverbeencounteredinthewild**";

    /**
     * Default buffer size for multipart {@code POST}s.
     */
    private static final int MULTIPART_DEFAULT_BUFFER_SIZE = 8192;

    /**
     * By default, how long should we wait for a response (in ms)?
     */
    private static final int DEFAULT_READ_TIMEOUT_IN_MS = 180000;

    public static final String HEADER_CONTENT_TYPE = "Content-Type";

    /**
     * By default, this is true, to prevent breaking existing usage
     */
    private boolean autocloseBinaryAttachmentStream = true;

    private final HttpClient httpClient;

    public DefaultWebRequestor() {
        this.httpClient = createHttpClientBuilder().build();
    }

    protected DefaultWebRequestor(HttpClient httpClient) {
        this.httpClient = Objects.requireNonNull(httpClient, "httpClient must not be null");
    }

    protected HttpClient getHttpClient() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected enum HttpMethod {

        GET, DELETE, POST
    }

    @Override
    public Response executeGet(Request request) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Response executeReelUpload(Request request) throws IOException {
        Optional<FacebookReelAttachment> reelOpt = request.getReel();
        if (reelOpt.isEmpty()) {
            throw new IllegalArgumentException("Try uploading reel with corrupt request");
        }
        FacebookReelAttachment reel = reelOpt.get();
        logRequestAndAttachmentOnDebug(request, request.getBinaryAttachments());
        TempFileBodyPublisher bodyPublisher = null;
        try {
            HttpRequest.Builder builder = openConnection(new URL(request.getUrl()));
            builder.timeout(Duration.ofMillis(DEFAULT_READ_TIMEOUT_IN_MS));
            initHeaderAccessToken(builder, request);
            fillReelHeader(builder, reel);
            customizeRequest(builder, request, HttpMethod.POST);
            BodyPublisher publisher = BodyPublishers.noBody();
            if (reel.isBinary()) {
                bodyPublisher = new TempFileBodyPublisher();
                try (OutputStream outputStream = bodyPublisher.outputStream()) {
                    write(reel.getData(), outputStream, MULTIPART_DEFAULT_BUFFER_SIZE);
                }
                publisher = bodyPublisher.build();
            }
            HttpRequest httpRequest = builder.POST(publisher).build();
            return sendRequest(httpRequest);
        } finally {
            closeAttachmentsOnAutoClose(request.getBinaryAttachments());
            closeQuietly(bodyPublisher);
        }
    }

    private void fillReelHeader(HttpRequest.Builder builder, FacebookReelAttachment reel) {
        if (reel.isBinary()) {
            builder.header("offset", "0");
            builder.header("file_size", String.valueOf(reel.getFileSizeInBytes()));
        } else {
            builder.header("file_url", reel.getReelUrl());
        }
    }

    @Override
    public Response executePost(Request request) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void setMultipartRequestProperties(HttpRequest.Builder builder) {
        builder.header(HEADER_CONTENT_TYPE, "multipart/form-data;boundary=" + MULTIPART_BOUNDARY);
    }

    private void setJsonRequestProperties(HttpRequest.Builder builder) {
        builder.header(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON);
    }

    private void setFormUrlEncodedRequestProperties(HttpRequest.Builder builder) {
        builder.header(HEADER_CONTENT_TYPE, CONTENT_TYPE_FORM_URLENCODED);
    }

    private String buildPostUrl(Request request, List<BinaryAttachment> binaryAttachments) {
        return request.getUrl() + ((!binaryAttachments.isEmpty() || request.hasBody()) ? "?" + request.getParameters() : "");
    }

    private static void logRequestAndAttachmentOnDebug(Request request, List<BinaryAttachment> binaryAttachments) {
        if (HTTP_LOGGER.isDebugEnabled()) {
            HTTP_LOGGER.debug("Executing a POST to " + request.getUrl() + " with parameters " + (!binaryAttachments.isEmpty() ? "" : "(sent in request body): ") + UrlUtils.urlDecode(request.getParameters()) + (!binaryAttachments.isEmpty() ? " and " + binaryAttachments.size() + " binary attachment[s]." : ""));
        }
    }

    private void closeAttachmentsOnAutoClose(List<BinaryAttachment> binaryAttachments) {
        if (autocloseBinaryAttachmentStream && !binaryAttachments.isEmpty()) {
            binaryAttachments.stream().filter(BinaryAttachment::hasBinaryData).map(BinaryAttachment::getData).forEach(this::closeQuietly);
        }
    }

    protected void initHeaderAccessToken(HttpRequest.Builder builder, Request request) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected HttpRequest.Builder openConnection(URL url) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected HttpClient.Builder createHttpClientBuilder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void customizeRequest(HttpRequest.Builder builder, Request request, HttpMethod httpMethod) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void closeQuietly(Closeable closeable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void write(InputStream source, OutputStream destination, int bufferSize) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates the form field name for the binary attachment filename by stripping off the file extension - for example,
     * the filename "test.png" would return "test".
     *
     * @param binaryAttachment
     *          The binary attachment for which to create the form field name.
     * @return The form field name for the given binary attachment.
     */
    public boolean isAutocloseBinaryAttachmentStream() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setAutocloseBinaryAttachmentStream(boolean autocloseBinaryAttachmentStream) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Response executeDelete(Request request) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Response execute(HttpMethod httpMethod, Request request) throws IOException {
        HTTP_LOGGER.debug("Making a {} request to {} with parameters {}", httpMethod.name(), request.getUrl(), request.getParameters());
        HttpRequest.Builder builder = openConnection(new URL(request.getFullUrl()));
        builder.timeout(Duration.ofMillis(DEFAULT_READ_TIMEOUT_IN_MS));
        initHeaderAccessToken(builder, request);
        customizeRequest(builder, request, httpMethod);
        switch(httpMethod) {
            case GET:
                builder.GET();
                break;
            case DELETE:
                builder.DELETE();
                break;
            default:
                throw new IllegalArgumentException("Unsupported httpMethod used");
        }
        HttpRequest httpRequest = builder.build();
        return sendRequest(httpRequest);
    }

    private Response sendRequest(HttpRequest httpRequest) throws IOException {
        try {
            HttpResponse<InputStream> httpResponse = getHttpClient().send(httpRequest, BodyHandlers.ofInputStream());
            Map<String, List<String>> headers = Collections.unmodifiableMap(toHeaderMap(httpResponse.headers()));
            HTTP_LOGGER.trace("Response headers: {}", headers);
            return createResponse(httpResponse, headers);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Interrupted while making request", e);
        }
    }

    private byte[] readResponseBody(HttpResponse<InputStream> httpResponse) throws IOException {
        InputStream responseBody = httpResponse.body();
        if (responseBody == null) {
            return new byte[0];
        }
        long expectedLength = httpResponse.headers().firstValueAsLong("Content-Length").orElse(-1L);
        long totalRead = 0;
        byte[] buffer = new byte[8192];
        try (InputStream bodyStream = responseBody;
            ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            int read;
            while ((read = bodyStream.read(buffer)) != -1) {
                output.write(buffer, 0, read);
                totalRead += read;
            }
            if (expectedLength >= 0 && expectedLength != totalRead) {
                throw new IOException("Incomplete response body: expected " + expectedLength + " bytes but read " + totalRead);
            }
            return output.toByteArray();
        } catch (IOException ioe) {
            throw new IOException("Incomplete response body", ioe);
        }
    }

    protected Response createResponse(HttpResponse<InputStream> httpResponse, Map<String, List<String>> headers) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Map<String, List<String>> toHeaderMap(HttpHeaders headers) {
        Map<String, List<String>> result = new LinkedHashMap<>();
        headers.map().forEach((k, v) -> result.put(k, Collections.unmodifiableList(v)));
        return result;
    }
}
