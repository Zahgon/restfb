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

import static com.restfb.util.ObjectUtil.verifyParameterPresence;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.restfb.exception.FacebookResponseContentException;
import com.restfb.scope.ScopeBuilder;

/**
 * A class representing a client for interacting with Facebook's Threads API. This class extends the
 * DefaultFacebookClient and provides additional methods for accessing Threads-specific functionalities.
 */
public class DefaultThreadsClient extends DefaultFacebookClient {

    public DefaultThreadsClient(Version version) {
        super(version);
    }

    public DefaultThreadsClient(String accessToken, Version apiVersion) {
        super(accessToken, apiVersion);
    }

    public DefaultThreadsClient(String accessToken, String appSecret, Version apiVersion) {
        super(accessToken, appSecret, apiVersion);
    }

    public DefaultThreadsClient(String accessToken, WebRequestor webRequestor, JsonMapper jsonMapper, Version apiVersion) {
        super(accessToken, webRequestor, jsonMapper, apiVersion);
    }

    public DefaultThreadsClient(String accessToken, String appSecret, WebRequestor webRequestor, JsonMapper jsonMapper, Version apiVersion) {
        super(accessToken, appSecret, webRequestor, jsonMapper, apiVersion);
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
    public AccessToken obtainUserAccessToken(String clientId, String clientSecret, String redirectUri, String code) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public AccessToken obtainExtendedAccessToken(String appId, String appSecret, String accessToken) {
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
    public FacebookClient createClientWithAccessToken(String accessToken) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected String createBaseUrlForEndpoint(String apiCall, boolean hasAttachment, boolean hasReel) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private String getThreadsGraphEndpointUrl() {
        if (apiVersion.isUrlElementRequired()) {
            return getFacebookEndpointUrls().getThreadsApiEndpoint() + '/' + apiVersion.getUrlElement();
        } else {
            return getFacebookEndpointUrls().getThreadsApiEndpoint();
        }
    }

    @Override
    public String getBusinessLoginDialogUrl(String appId, String redirectUri, String configId, String state, Parameter... parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
