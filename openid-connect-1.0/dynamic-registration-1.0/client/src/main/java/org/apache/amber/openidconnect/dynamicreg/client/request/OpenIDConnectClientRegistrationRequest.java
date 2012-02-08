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

package org.apache.amber.openidconnect.dynamicreg.client.request;

import org.apache.amber.oauth2.client.request.OAuthClientRequest;
import org.apache.amber.oauth2.common.exception.OAuthSystemException;
import org.apache.amber.oauth2.common.parameters.BodyURLEncodedParametersApplier;
import org.apache.amber.opendiconnect.common.OpenIDConnect;


public class OpenIDConnectClientRegistrationRequest extends OAuthClientRequest {

    protected OpenIDConnectClientRegistrationRequest(String url) {
        super(url);
    }

    public static OpenIDConnectClientRegistrationRequestBuilder location(String url, String type) {
        return new OpenIDConnectClientRegistrationRequestBuilder(url, type);
    }

    public static class OpenIDConnectClientRegistrationRequestBuilder extends OAuthRequestBuilder {

        public OpenIDConnectClientRegistrationRequestBuilder(String url, String type) {
            super(url);
            this.parameters.put(OpenIDConnect.DynamicClientRegistration.Request.OC_TYPE, type);
        }

        public OpenIDConnectClientRegistrationRequestBuilder setApplicationName(String value) {
            this.parameters.put(OpenIDConnect.DynamicClientRegistration.Request.OC_APPLICATION_NAME, value);
            return this;
        }

        public OpenIDConnectClientRegistrationRequestBuilder setApplicationType(String value) {
            this.parameters.put(OpenIDConnect.DynamicClientRegistration.Request.OC_APPLICATION_TYPE, value);
            return this;
        }

        /**
         * @param redirectUris space-delimited list of redirect URIs
         * @return
         */
        public OpenIDConnectClientRegistrationRequestBuilder setRedirectUris(String redirectUris) {
            this.parameters.put(OpenIDConnect.DynamicClientRegistration.Request.OC_REDIRECT_URIS, redirectUris);
            return this;
        }

        public OpenIDConnectClientRegistrationRequestBuilder setLogoURL(String logoURL) {
            this.parameters.put(OpenIDConnect.DynamicClientRegistration.Request.OC_LOGO_URL, logoURL);
            return this;
        }

        public OAuthClientRequest buildBodyMessage() throws OAuthSystemException {
            OAuthClientRequest request = new OpenIDConnectClientRegistrationRequest(url);
            this.applier = new BodyURLEncodedParametersApplier();
            return (OAuthClientRequest) applier.applyOAuthParameters(request, parameters);
        }

    }
}
