/**
 *       Copyright 2010 Newcastle University
 *
 *          http://research.ncl.ac.uk/smart/
 *
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

package org.apache.amber.oauth2.client.response;

import org.apache.amber.oauth2.common.OAuth;
import org.apache.amber.oauth2.common.token.BasicOAuthToken;
import org.apache.amber.oauth2.common.token.OAuthToken;
import org.apache.amber.oauth2.common.utils.OAuthUtils;

import java.util.List;
import java.util.Map;


/**
 * @author Maciej Machulak (m.p.machulak@ncl.ac.uk)
 * @author Lukasz Moren (lukasz.moren@ncl.ac.uk)
 * @author Aad van Moorsel (aad.vanmoorsel@ncl.ac.uk)
 */
public class GitHubTokenResponse extends OAuthAccessTokenResponse {


    public String getAccessToken() {
        return parameters.get(OAuth.OAUTH_ACCESS_TOKEN);
    }

    public String getExpiresIn() {
        return parameters.get(OAuth.OAUTH_EXPIRES_IN);
    }

    public String getRefreshToken() {
        return parameters.get(OAuth.OAUTH_EXPIRES_IN);
    }

    public String getScope() {
        return parameters.get(OAuth.OAUTH_SCOPE);
    }

    public OAuthToken getOAuthToken() {
        return new BasicOAuthToken(getAccessToken(), getExpiresIn(), getRefreshToken(), getScope());
    }

    protected void setBody(String body) {
        this.body = body;
        parameters = OAuthUtils.decodeForm(body);
    }

    protected void setContentType(String contentType) {
        this.contentType = contentType;
    }

    protected void setResponseCode(int code) {
        this.responseCode = code;
    }

    @Override
    protected void setResponseHeaders(Map<String, List<String>> headers) {
        this.responseHeaders = headers;
    }

    public String getParam(String name) {
        return parameters.get(name);
    }



}
