/**
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

package org.apache.amber.openidconnect.dynamicreg.client;

import org.apache.amber.oauth2.client.HttpClient;
import org.apache.amber.oauth2.client.OAuthClient;
import org.apache.amber.oauth2.client.request.OAuthClientRequest;
import org.apache.amber.oauth2.common.OAuth;
import org.apache.amber.oauth2.common.exception.OAuthProblemException;
import org.apache.amber.oauth2.common.exception.OAuthSystemException;
import org.apache.amber.openidconnect.dynamicreg.client.response.OpenIDConnectClientRegistrationResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OpenIDConnectRegistrationClient extends OAuthClient {

    public OpenIDConnectRegistrationClient(HttpClient oauthClient) {
        super(oauthClient);
    }

    public OpenIDConnectClientRegistrationResponse registerClient(
            OAuthClientRequest request)
            throws IOException, OAuthSystemException, OAuthProblemException {
        String method = OAuth.HttpMethod.POST;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(OAuth.HeaderType.CONTENT_TYPE, OAuth.ContentType.URL_ENCODED);

        return httpClient.execute(request, headers, method, OpenIDConnectClientRegistrationResponse.class);
    }
}
