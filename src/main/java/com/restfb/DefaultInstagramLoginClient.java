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

import com.restfb.exception.FacebookResponseContentException;
import com.restfb.scope.ScopeBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static com.restfb.util.ObjectUtil.verifyParameterPresence;

/**
 * InstagramLoginClient is the default implementation of the
 * <a href="https://developers.facebook.com/docs/instagram-platform/instagram-api-with-instagram-login/business-login">
 * Instagram API with Instagram Login</a>.
 * <p>
 * This client is used to use a instagram Login to get a access token, extended access token and so on for the Instagram
 * API.
 * <p>
 * Don't use the client if you are planning to use the Facebbok Login to work with the Instagram API.
 */
public class DefaultInstagramLoginClient extends DefaultFacebookClient {

    public DefaultInstagramLoginClient(Version version) {
        super(version);
    }

    public DefaultInstagramLoginClient(String accessToken, Version apiVersion) {
        super(accessToken, apiVersion);
    }

    public DefaultInstagramLoginClient(String accessToken, String appSecret, Version apiVersion) {
        super(accessToken, appSecret, apiVersion);
    }

    public DefaultInstagramLoginClient(String accessToken, WebRequestor webRequestor, JsonMapper jsonMapper, Version apiVersion) {
        super(accessToken, webRequestor, jsonMapper, apiVersion);
    }

    public DefaultInstagramLoginClient(String accessToken, String appSecret, WebRequestor webRequestor, JsonMapper jsonMapper, Version apiVersion) {
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
    public FacebookClient createClientWithAccessToken(String accessToken) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public AccessToken obtainRefreshedExtendedAccessToken() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected String createBaseUrlForEndpoint(String apiCall, boolean hasAttachment, boolean hasReel) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private String getInstagramApiEndpointUrl() {
        return getFacebookEndpointUrls().getInstagramApiEndpoint();
    }

    private String getInstagramGraphEndpointUrl() {
        if (apiVersion.isUrlElementRequired()) {
            return getFacebookEndpointUrls().getInstagramEndpoint() + '/' + apiVersion.getUrlElement();
        } else {
            return getFacebookEndpointUrls().getInstagramEndpoint();
        }
    }
}
