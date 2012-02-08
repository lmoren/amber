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

package org.apache.amber.opendiconnect.dynamicreg.server.response;

import org.apache.amber.oauth2.ext.dynamicreg.common.OAuthRegistration;
import org.apache.amber.opendiconnect.common.OpenIDConnect;
import org.apache.amber.opendiconnect.common.response.OpenIDConnectResponse;

/**
 * @author Maciej Machulak (m.p.machulak@ncl.ac.uk)
 * @author Lukasz Moren (lukasz.moren@ncl.ac.uk)
 * @author Aad van Moorsel (aad.vanmoorsel@ncl.ac.uk)
 */
public class OpenIDConnectServerRegistrationResponse extends OpenIDConnectResponse {

    protected OpenIDConnectServerRegistrationResponse(String url, int responseStatus) {
        super(url, responseStatus);
    }

    public static OpenIDConnectRegistrationResponseBuilder status(int code) {
        return new OpenIDConnectRegistrationResponseBuilder(code);
    }

    public static class OpenIDConnectRegistrationResponseBuilder extends OpenIDConnectResponseBuilder {

        public OpenIDConnectRegistrationResponseBuilder(int responseCode) {
            super(responseCode);
        }

        public OpenIDConnectRegistrationResponseBuilder setClientId(String value) {
            this.parameters.put(OAuthRegistration.Response.CLIENT_ID, value);
            return this;
        }

        public OpenIDConnectRegistrationResponseBuilder setClientSecret(String value) {
            this.parameters.put(OAuthRegistration.Response.CLIENT_SECRET, value);
            return this;
        }

        public OpenIDConnectRegistrationResponseBuilder setExpiresAt(String value) {
            this.parameters.put(OpenIDConnect.DynamicClientRegistration.Response.EXPIRES_AT, value);
            return this;
        }


        public OpenIDConnectRegistrationResponseBuilder setParam(String key, String value) {
            this.parameters.put(key, value);
            return this;
        }
    }
}
