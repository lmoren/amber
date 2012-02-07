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

package org.apache.amber.oauth2.ext.dynamicreg.client.response;

import org.apache.amber.oauth2.common.error.OAuthError;
import org.apache.amber.oauth2.common.exception.OAuthProblemException;
import org.apache.amber.oauth2.common.utils.JSONUtils;
import org.apache.amber.oauth2.ext.dynamicreg.client.validators.RegistrationValidator;
import org.apache.amber.oauth2.ext.dynamicreg.common.OAuthRegistration;
import org.codehaus.jettison.json.JSONException;
import org.apache.amber.oauth2.client.response.OAuthClientResponse;

import java.util.List;
import java.util.Map;


/**
 * @author Maciej Machulak (m.p.machulak@ncl.ac.uk)
 * @author Lukasz Moren (lukasz.moren@ncl.ac.uk)
 * @author Aad van Moorsel (aad.vanmoorsel@ncl.ac.uk)
 */
public class OAuthClientRegistrationResponse extends OAuthClientResponse {

    public OAuthClientRegistrationResponse() {
    }

    @Override
    protected void init(String body, String contentType, int responseCode, Map<String, List<String>> responseHeaders) throws OAuthProblemException {
        validator = new RegistrationValidator();
        super.init(body, contentType, responseCode, responseHeaders);
    }

    @Override
    public String getParam(String param) {
        return parameters.get(param);
    }

    protected void setBody(String body) throws OAuthProblemException {
        try {
            this.body = body;
            parameters = JSONUtils.parseJSON(body);
        } catch (JSONException e) {
            throw OAuthProblemException.error(OAuthError.CodeResponse.UNSUPPORTED_RESPONSE_TYPE,
                    "Invalid response! Response body is not application/json encoded");
        }
    }

    protected void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    protected void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getClientId() {
        return parameters.get(OAuthRegistration.Response.CLIENT_ID);
    }

    public String getClientSecret() {
        return parameters.get(OAuthRegistration.Response.CLIENT_SECRET);
    }

    public String getIssuedAt() {
        return parameters.get(OAuthRegistration.Response.ISSUED_AT);
    }

    public String getExpiresIn() {
        return parameters.get(OAuthRegistration.Response.EXPIRES_IN);
    }

    @Override
    protected void setResponseHeaders(Map<String, List<String>> headers) {
        this.responseHeaders = headers;
    }

}
