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
import static java.util.Collections.unmodifiableList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import com.restfb.exception.FacebookJsonMappingException;
import com.restfb.json.Json;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.json.ParseException;
import com.restfb.util.ReflectionUtils;

/**
 * Represents a <a href="http://developers.facebook.com/docs/api">Graph API Connection type</a>.
 *
 * @param <T>
 *          The Facebook type
 * @author <a href="http://restfb.com">Mark Allen</a>
 */
public class Connection<T> implements Iterable<List<T>> {

    private FacebookClient facebookClient;

    private Class<T> connectionType;

    private List<T> data;

    private String previousPageUrl;

    private String nextPageUrl;

    private Long totalCount;

    private String beforeCursor;

    private String afterCursor;

    private String order;

    private String json;

    private T typedSummary;

    private ResponseMetadata responseMetadata;

    @Override
    public ConnectionIterator<T> iterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Iterator over connection pages.
     *
     * @author <a href="http://restfb.com">Mark Allen</a>
     * @since 1.6.7
     */
    protected static class Itr<T> implements ConnectionIterator<T> {

        private Connection<T> connection;

        private boolean initialPage = true;

        /**
         * Creates a new iterator over the given {@code connection}.
         *
         * @param connection
         *          The connection over which to iterate.
         */
        protected Itr(Connection<T> connection) {
            this.connection = connection;
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public List<T> next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Connection<T> snapshot() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Creates a connection with the given {@code jsonObject}.
     *
     * @param facebookClient
     *          The {@code FacebookClient} used to fetch additional pages and map data to JSON objects.
     * @param json
     *          Raw JSON which must include a {@code data} field that holds a JSON array and optionally a {@code paging}
     *          field that holds a JSON object with next/previous page URLs.
     * @param connectionType
     *          Connection type token.
     * @throws FacebookJsonMappingException
     *           If the provided {@code json} is invalid.
     * @since 1.6.7
     */
    @SuppressWarnings("unchecked")
    public Connection(FacebookClient facebookClient, String json, Class<T> connectionType) {
        JsonObject jsonObject;
        this.json = json;
        try {
            jsonObject = Optional.ofNullable(json).map(j -> Json.parse(j).asObject()).orElseThrow(() -> new FacebookJsonMappingException("You must supply non-null connection JSON."));
        } catch (ParseException e) {
            throw new FacebookJsonMappingException("The connection JSON you provided was invalid: " + json, e);
        }
        // Pull out data
        if (!jsonObject.contains("data")) {
            throw new FacebookJsonMappingException("The connection JSON does not contain a data field, maybe it is no connection");
        }
        JsonArray jsonData = jsonObject.get("data").asArray();
        List<T> dataItem = jsonData.valueStream().map(jsonValue -> connectionType.equals(JsonObject.class) ? (T) jsonValue : facebookClient.getJsonMapper().toJavaObject(jsonValue.toString(), connectionType)).collect(Collectors.toList());
        // Pull out paging info, if present
        if (jsonObject.contains("paging")) {
            JsonObject jsonPaging = jsonObject.get("paging").asObject();
            previousPageUrl = fixProtocol(jsonPaging.getString("previous", null));
            nextPageUrl = fixProtocol(jsonPaging.getString("next", null));
            // handle cursors
            if (jsonPaging.contains("cursors")) {
                JsonObject jsonCursors = jsonPaging.get("cursors").asObject();
                beforeCursor = jsonCursors.getString("before", null);
                afterCursor = jsonCursors.getString("after", null);
            }
        } else {
            previousPageUrl = null;
            nextPageUrl = null;
        }
        if (jsonObject.contains("summary")) {
            JsonObject jsonSummary = jsonObject.get("summary").asObject();
            totalCount = jsonSummary.contains("total_count") ? jsonSummary.getLong("total_count", 0L) : null;
            order = jsonSummary.getString("order", "");
            // special handling to fill the typed summary (used by ad insights for example)
            try {
                typedSummary = facebookClient.getJsonMapper().toJavaObject(jsonSummary.toString(), connectionType);
            } catch (FacebookJsonMappingException jme) {
                // ignore mapping exception here
            }
        } else {
            totalCount = null;
            order = null;
        }
        this.data = unmodifiableList(dataItem);
        this.facebookClient = facebookClient;
        this.connectionType = connectionType;
    }

    protected Connection<T> fetchNextPage() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> getData() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getPreviousPageUrl() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getNextPageUrl() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasPrevious() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasNext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Long getTotalCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getOrder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getBeforeCursor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getAfterCursor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T getTypedSummary() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private String fixProtocol(String pageUrl) {
        return Optional.ofNullable(pageUrl).filter(s -> s.startsWith("http://")).map(s -> s.replaceFirst("http://", "https://")).orElse(pageUrl);
    }

    public void replaceFacebookClient(FacebookClient facebookClient) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getJson() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ResponseMetadata getResponseMetadata() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setResponseMetadata(ResponseMetadata responseMetadata) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setNextPageUrl(String nextPageUrl) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setPreviousPageUrl(String previousPageUrl) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public FacebookClient getFacebookClient() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
