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

import static java.net.HttpURLConnection.HTTP_NOT_MODIFIED;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import com.restfb.util.SoftHashMap;

/**
 * WebRequestor with ETag-support.
 *
 * <p>
 * The {@link ETagWebRequestor} caches all <tt>GET</tt>-requests with an ETag header field in a {@link SoftHashMap} and
 * uses the ETag on the next request as <code>If-None-Match</code> header field if the same URL is requested.
 * </p>
 *
 * <p>
 * Is the response status code 304 (NOT MODIFIED) the old response from cache is used.
 * </p>
 *
 * <p>
 * <strong>Attention:</strong> even 304 responses count as request at Facebook and so they count against the throttling
 * limits. Facebook suggests to use them for data that change only frequently
 * </p>
 *
 * <p>
 * Further information regarding ETag at Facebook can be found here:
 * <a href="https://developers.facebook.com/blog/post/627/">https://developers.facebook.com/blog/post/627/</a>
 * </p>
 *
 * <p>
 * <strong>Attention 2</strong>: If excessively used with a lot of URLs, the {@link SoftHashMap} can lead to a
 * performance degradation
 * </p>
 */
public class ETagWebRequestor extends DefaultWebRequestor {

    private static Supplier<Map<String, ETagResponse>> mapBuilder = SoftHashMap::new;

    final Map<String, ETagResponse> etagCache = Collections.synchronizedMap(mapBuilder.get());

    private final ThreadLocal<ETagResponse> currentETagRespThreadLocal = new ThreadLocal<>();

    private volatile boolean useCache = true;

    @Override
    protected void customizeRequest(HttpRequest.Builder builder, Request request, HttpMethod httpMethod) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected Response createResponse(HttpResponse<InputStream> httpResponse, Map<String, List<String>> headers) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isUseCache() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setUseCache(boolean useCache) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void setMapSupplier(Supplier<Map<String, ETagResponse>> mapSupplier) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static class ETagResponse {

        public ETagResponse(String etag, String body) {
            this.etag = etag;
            this.body = body;
        }

        private final String etag;

        private final String body;

        public String getEtag() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public String getBody() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
