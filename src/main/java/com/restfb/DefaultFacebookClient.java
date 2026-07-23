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

import static com.restfb.logging.RestFBLogger.CLIENT_LOGGER;
import static com.restfb.util.EncodingUtils.decodeBase64;
import static com.restfb.util.ObjectUtil.requireNotEmpty;
import static com.restfb.util.ObjectUtil.verifyParameterPresence;
import static com.restfb.util.StringUtils.*;
import static com.restfb.util.UrlUtils.urlEncode;
import static java.lang.String.format;
import static java.net.HttpURLConnection.*;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import com.restfb.WebRequestor.Response;
import com.restfb.batch.BatchRequest;
import com.restfb.batch.BatchResponse;
import com.restfb.exception.*;
import com.restfb.exception.devicetoken.*;
import com.restfb.exception.generator.DefaultFacebookExceptionGenerator;
import com.restfb.exception.generator.FacebookExceptionGenerator;
import com.restfb.json.*;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.DebugTokenInfo;
import com.restfb.types.DeviceCode;
import com.restfb.util.EncodingUtils;
import com.restfb.util.ObjectUtil;
import com.restfb.util.StringUtils;

/**
 * Default implementation of a <a href="http://developers.facebook.com/docs/api">Facebook Graph API</a> client.
 *
 * @author <a href="http://restfb.com">Mark Allen</a>
 */
public class DefaultFacebookClient extends BaseFacebookClient implements FacebookClient {

    public static final String CLIENT_ID = "client_id";

    public static final String APP_ID = "appId";

    public static final String APP_SECRET = "appSecret";

    public static final String SCOPE = "scope";

    public static final String CANNOT_EXTRACT_ACCESS_TOKEN_MESSAGE = "Unable to extract access token from response.";

    public static final String PARAM_CLIENT_SECRET = "client_secret";

    public static final String CONNECTION = "connection";

    public static final String CONNECTION_TYPE = "connectionType";

    public static final String ALGORITHM = "algorithm";

    public static final String PATH_OAUTH_ACCESS_TOKEN = "oauth/access_token";

    public static final String REDIRECT_URI = "redirect_uri";

    public static final String GRANT_TYPE = "grant_type";

    public static final String CODE = "code";

    /**
     * Graph API access token.
     */
    protected String accessToken;

    /**
     * Graph API app secret.
     */
    protected String appSecret;

    /**
     * facebook exception generator to convert Facebook error json into java exceptions
     */
    private FacebookExceptionGenerator graphFacebookExceptionGenerator;

    /**
     * holds the Facebook endpoint urls
     */
    private FacebookEndpoints facebookEndpointUrls = new FacebookEndpoints() {
    };

    /**
     * Reserved "multiple IDs" parameter name.
     */
    protected static final String IDS_PARAM_NAME = "ids";

    /**
     * Version of API endpoint.
     */
    protected Version apiVersion;

    /**
     * By default, this is <code>false</code>, so real http DELETE is used
     */
    protected boolean httpDeleteFallback;

    protected boolean accessTokenInHeader;

    protected DefaultFacebookClient() {
        this(Version.LATEST);
    }

    /**
     * Creates a Facebook Graph API client with the given {@code apiVersion}.
     *
     * @param apiVersion
     *          Version of the api endpoint
     */
    public DefaultFacebookClient(Version apiVersion) {
        this(null, null, new DefaultWebRequestor(), new DefaultJsonMapper(), apiVersion);
    }

    /**
     * Creates a Facebook Graph API client with the given {@code accessToken}.
     *
     * @param accessToken
     *          A Facebook OAuth access token.
     * @param apiVersion
     *          Version of the api endpoint
     * @since 1.6.14
     */
    public DefaultFacebookClient(String accessToken, Version apiVersion) {
        this(accessToken, null, new DefaultWebRequestor(), new DefaultJsonMapper(), apiVersion);
    }

    /**
     * Creates a Facebook Graph API client with the given {@code accessToken}.
     *
     * @param accessToken
     *          A Facebook OAuth access token.
     * @param appSecret
     *          A Facebook application secret.
     * @param apiVersion
     *          Version of the api endpoint
     * @since 1.6.14
     */
    public DefaultFacebookClient(String accessToken, String appSecret, Version apiVersion) {
        this(accessToken, appSecret, new DefaultWebRequestor(), new DefaultJsonMapper(), apiVersion);
    }

    /**
     * Creates a Facebook Graph API client with the given {@code accessToken}.
     *
     * @param accessToken
     *          A Facebook OAuth access token.
     * @param webRequestor
     *          The {@link WebRequestor} implementation to use for sending requests to the API endpoint.
     * @param jsonMapper
     *          The {@link JsonMapper} implementation to use for mapping API response JSON to Java objects.
     * @param apiVersion
     *          Version of the api endpoint
     * @throws NullPointerException
     *           If {@code jsonMapper} or {@code webRequestor} is {@code null}.
     * @since 1.6.14
     */
    public DefaultFacebookClient(String accessToken, WebRequestor webRequestor, JsonMapper jsonMapper, Version apiVersion) {
        this(accessToken, null, webRequestor, jsonMapper, apiVersion);
    }

    /**
     * Creates a Facebook Graph API client with the given {@code accessToken}, {@code webRequestor}, and
     * {@code jsonMapper}.
     *
     * @param accessToken
     *          A Facebook OAuth access token.
     * @param appSecret
     *          A Facebook application secret.
     * @param webRequestor
     *          The {@link WebRequestor} implementation to use for sending requests to the API endpoint.
     * @param jsonMapper
     *          The {@link JsonMapper} implementation to use for mapping API response JSON to Java objects.
     * @param apiVersion
     *          Version of the api endpoint
     * @throws NullPointerException
     *           If {@code jsonMapper} or {@code webRequestor} is {@code null}.
     */
    public DefaultFacebookClient(String accessToken, String appSecret, WebRequestor webRequestor, JsonMapper jsonMapper, Version apiVersion) {
        super();
        verifyParameterPresence("jsonMapper", jsonMapper);
        verifyParameterPresence("webRequestor", webRequestor);
        this.accessToken = trimToNull(accessToken);
        this.appSecret = trimToNull(appSecret);
        this.webRequestor = webRequestor;
        this.jsonMapper = jsonMapper;
        this.jsonMapper.setFacebookClient(this);
        this.apiVersion = Optional.ofNullable(apiVersion).orElse(Version.UNVERSIONED);
        graphFacebookExceptionGenerator = new DefaultFacebookExceptionGenerator();
    }

    public void setHeaderAuthorization(boolean accessTokenInHttpHeader) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setFacebookExceptionGenerator(FacebookExceptionGenerator exceptionGenerator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public FacebookExceptionGenerator getFacebookExceptionGenerator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean deleteObject(String object, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ApiResult<Boolean> deleteObjectWithResult(String object, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> Connection<T> fetchConnection(String connection, Class<T> connectionType, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> Connection<T> fetchConnectionPage(final String connectionPageUrl, Class<T> connectionType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private RequestExecutionResult fetchConnectionPageResponse(String connectionPageUrl) {
        WebRequestor.Request request;
        if (!isBlank(accessToken) && !isBlank(appSecret)) {
            if (isAppSecretProofWithTime()) {
                long now = System.currentTimeMillis() / 1000;
                request = new WebRequestor.Request(String.format("%s&%s=%s&%s=%s", connectionPageUrl, urlEncode(APP_SECRET_PROOF_TIME_PARAM_NAME), now, urlEncode(APP_SECRET_PROOF_PARAM_NAME), obtainAppSecretProof(accessToken + "|" + now, appSecret)), null);
            } else {
                request = new WebRequestor.Request(String.format("%s&%s=%s", connectionPageUrl, urlEncode(APP_SECRET_PROOF_PARAM_NAME), obtainAppSecretProof(accessToken, appSecret)), null);
            }
        } else {
            request = new WebRequestor.Request(connectionPageUrl, getHeaderAccessToken());
        }
        return executeGetRequestWithInfo(request);
    }

    @Override
    public <T> T fetchObject(String object, Class<T> objectType, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> ApiResult<T> fetchObjectWithResult(String object, Class<T> objectType, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public FacebookClient createClientWithAccessToken(String accessToken) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> T fetchObjects(List<String> ids, Class<T> objectType, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> ApiResult<T> fetchObjectsWithResult(List<String> ids, Class<T> objectType, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void throwIAEonBlankId(String id) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("The list of IDs cannot contain blank strings.");
        }
    }

    private <T> ApiResult<T> toApiResult(T result, RequestExecutionResult executionResult) {
        return ApiResult.withMetadata(result, toResponseMetadata(executionResult));
    }

    private ResponseMetadata toResponseMetadata(RequestExecutionResult executionResult) {
        if (executionResult == null) {
            return null;
        }
        Response response = executionResult.getResponse();
        DebugHeaderInfo debugHeaderInfo = Optional.ofNullable(response).map(Response::getDebugHeaderInfo).orElse(null);
        Map<String, List<String>> headers = Optional.ofNullable(response).map(Response::getHeaders).orElse(null);
        Duration duration = Optional.ofNullable(executionResult).map(RequestExecutionResult::getDuration).orElse(null);
        String httpMethod = Optional.ofNullable(executionResult).map(RequestExecutionResult::getHttpMethod).orElse(null);
        String requestUrl = Optional.ofNullable(executionResult).map(RequestExecutionResult::getRequestUrl).orElse(null);
        return ResponseMetadata.of(debugHeaderInfo, headers, duration, httpMethod, requestUrl);
    }

    @Override
    public <T> T publish(String connection, Class<T> objectType, List<BinaryAttachment> binaryAttachments, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> ApiResult<T> publishWithResult(String connection, Class<T> objectType, List<BinaryAttachment> binaryAttachments, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> T publish(String connection, Class<T> objectType, BinaryAttachment binaryAttachment, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> ApiResult<T> publishWithResult(String connection, Class<T> objectType, BinaryAttachment binaryAttachment, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> T publish(String connection, Class<T> objectType, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> ApiResult<T> publishWithResult(String connection, Class<T> objectType, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> T publish(String connection, Class<T> objectType, Body body, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> ApiResult<T> publishWithResult(String connection, Class<T> objectType, Body body, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getLogoutUrl(String next) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<BatchResponse> executeBatch(BatchRequest... batchRequests) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<BatchResponse> executeBatch(List<BatchRequest> batchRequests) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<BatchResponse> executeBatch(List<BatchRequest> batchRequests, List<BinaryAttachment> binaryAttachments) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<AccessToken> convertSessionKeysToAccessTokens(String appId, String secretKey, String... sessionKeys) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public AccessToken obtainAppAccessToken(String appId, String appSecret) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public DeviceCode fetchDeviceCode(ScopeBuilder scope) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public AccessToken obtainDeviceAccessToken(String code) throws FacebookDeviceTokenCodeExpiredException, FacebookDeviceTokenPendingException, FacebookDeviceTokenDeclinedException, FacebookDeviceTokenSlowdownException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public AccessToken obtainUserAccessToken(String appId, String appSecret, String redirectUri, String verificationCode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public AccessToken obtainExtendedAccessToken(String appId, String appSecret) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public AccessToken obtainRefreshedExtendedAccessToken() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public AccessToken obtainExtendedAccessToken(String appId, String appSecret, String accessToken) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected AccessToken getAccessTokenFromResponse(String response) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T parseSignedRequest(String signedRequest, String appSecret, Class<T> objectType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected String urlDecodeSignedRequestToken(String signedRequestToken) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getLoginDialogUrl(String appId, String redirectUri, ScopeBuilder scope, String state, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getLoginDialogUrl(String appId, String redirectUri, ScopeBuilder scope, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getBusinessLoginDialogUrl(String appId, String redirectUri, String configId, String state, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected String getGenericLoginDialogUrl(String appId, String redirectUri, ScopeBuilder scope, Supplier<String> endpointSupplier, String state, List<Parameter> parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean verifySignedRequest(String appSecret, String algorithm, String encodedPayload, byte[] signature) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public DebugTokenInfo debugToken(String inputToken) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public JsonMapper getJsonMapper() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public WebRequestor getWebRequestor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected String makeRequest(String endpoint, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected String makeRequest(String endpoint, final boolean executeAsPost, final boolean executeAsDelete, final List<BinaryAttachment> binaryAttachments, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected String makeRequest(String endpoint, final boolean executeAsPost, final boolean executeAsDelete, final List<BinaryAttachment> binaryAttachments, Body body, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Response makeRequestForResponse(String endpoint, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Response makeRequestForResponse(String endpoint, final boolean executeAsPost, final boolean executeAsDelete, final List<BinaryAttachment> binaryAttachments, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected RequestExecutionResult makeRequestWithMetadata(String endpoint, final boolean executeAsPost, final boolean executeAsDelete, final List<BinaryAttachment> binaryAttachments, Body body, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Response makeRequestForResponse(String endpoint, final boolean executeAsPost, final boolean executeAsDelete, final List<BinaryAttachment> binaryAttachments, Body body, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected RequestExecutionResult makeRequestWithMetadata(String endpoint, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected RequestExecutionResult makeRequestWithMetadata(String endpoint, final boolean executeAsPost, final boolean executeAsDelete, final List<BinaryAttachment> binaryAttachments, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private RequestExecutionResult executeGetRequest(WebRequestor.Request request) {
        return executeRequestWithMetadata("GET", request.getFullUrl(), () -> webRequestor.executeGet(request));
    }

    private RequestExecutionResult executeGetRequestWithInfo(WebRequestor.Request request) {
        long startTime = System.currentTimeMillis();
        try {
            return executeGetRequest(request);
        } catch (FacebookException facebookException) {
            facebookException.withInfoData("GET", request.getUrl(), request.getParameters(), request.getHeaderAccessToken(), startTime);
            throw facebookException;
        }
    }

    private String getHeaderAccessToken() {
        if (accessTokenInHeader) {
            return this.accessToken;
        }
        return null;
    }

    @Override
    public String obtainAppSecretProof(String accessToken, String appSecret) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isHttpDeleteFallback() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setHttpDeleteFallback(boolean httpDeleteFallback) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected interface Requestor {

        Response makeRequest() throws IOException;
    }

    protected RequestExecutionResult executeRequestWithMetadata(String httpMethod, String requestUrl, Requestor requestor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static class RequestExecutionResult {

        private final Response response;

        private final Duration duration;

        private final String httpMethod;

        private final String requestUrl;

        RequestExecutionResult(Response response, Duration duration, String httpMethod, String requestUrl) {
            this.response = response;
            this.duration = duration;
            this.httpMethod = httpMethod;
            this.requestUrl = requestUrl;
        }

        public Response getResponse() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Duration getDuration() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public String getHttpMethod() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public String getRequestUrl() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    protected String toParameterString(Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected String toParameterString(boolean withJsonParameter, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected String createEndpointForApiCall(String apiCall, boolean hasAttachment, boolean hasReel) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected String createBaseUrlForEndpoint(String apiCall, boolean hasAttachment, boolean hasReel) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected String getFacebookGraphEndpointUrl() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the base endpoint URL for the Graph APIs video upload functionality.
     *
     * @return The base endpoint URL for the Graph APIs video upload functionality.
     * @since 1.6.5
     * @deprecated the Graph Video endpoint is deprecated; video uploads use the Graph API endpoint
     *             instead.
     */
    @Deprecated
    protected String getFacebookGraphVideoEndpointUrl() {
        if (apiVersion.isUrlElementRequired()) {
            return getFacebookEndpointUrls().getGraphVideoEndpoint() + '/' + apiVersion.getUrlElement();
        } else {
            return getFacebookEndpointUrls().getGraphVideoEndpoint();
        }
    }

    protected String getFacebookReelsUploadEndpointUrl() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public FacebookEndpoints getFacebookEndpointUrls() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setFacebookEndpointUrls(FacebookEndpoints facebookEndpointUrls) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
