/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.amber.openidconnect.dynamicreg.client.response;

import org.apache.amber.oauth2.client.response.OAuthClientResponse;
import org.apache.amber.oauth2.common.OAuth;
import org.apache.amber.oauth2.common.error.OAuthError;
import org.apache.amber.oauth2.common.exception.OAuthProblemException;
import org.apache.amber.oauth2.common.utils.JSONUtils;
import org.apache.amber.oauth2.common.utils.OAuthUtils;
import org.apache.amber.opendiconnect.common.OpenIDConnect;
import org.apache.amber.opendiconnect.common.dynamicreg.Credentials;
import org.apache.amber.openidconnect.dynamicreg.client.validator.DynamicClientRegistrationValidator;
import org.codehaus.jettison.json.JSONException;

import java.util.List;
import java.util.Map;


public class OpenIDConnectClientRegistrationResponse extends OAuthClientResponse {

    private Credentials credentials;

    public OpenIDConnectClientRegistrationResponse() {
    }

    @Override
    protected void init(String body, String contentType, int responseCode, Map<String, List<String>> headers) throws OAuthProblemException {
        initParameters(body, responseCode, headers);
        validator = new DynamicClientRegistrationValidator();
        super.init(body, contentType, responseCode, headers);
    }

    private void initParameters(String body, int responseCode, Map<String, List<String>> headers) throws OAuthProblemException {
        try {

            if (responseCode == 400) {
                List<String> headerValues = headers.get(OAuth.HeaderType.WWW_AUTHENTICATE);
                if (headerValues != null && !headerValues.isEmpty()) {
                    String authenticateHeader = headerValues.get(0);

                    parameters = OAuthUtils.decodeOAuthHeader(authenticateHeader);
                }
            } else {
                parameters = JSONUtils.parseJSON(body);

                credentials = new Credentials(getClientId(), getClientSecret(), getExpiresAt());

            }
        } catch (JSONException e) {
            throw OAuthProblemException.error(OAuthError.CodeResponse.UNSUPPORTED_RESPONSE_TYPE,
                    "Invalid response! Response body is not application/json encoded");
        }
    }

    @Override
    public String getParam(String param) {
        return parameters.get(param);
    }

    protected void setBody(String body) throws OAuthProblemException {
        this.body = body;
    }

    protected void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    protected void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    protected void setResponseHeaders(Map<String, List<String>> headers) {
        this.responseHeaders = headers;
    }

    private String getClientId() {
        return parameters.get(OAuth.OAUTH_CLIENT_ID);
    }

    private String getClientSecret() {
        return parameters.get(OAuth.OAUTH_CLIENT_SECRET);
    }

    private String getExpiresAt() {
        return parameters.get(OpenIDConnect.DynamicClientRegistration.Response.EXPIRES_AT);
    }

    public Credentials getCredentials() {
        return credentials;
    }
}
