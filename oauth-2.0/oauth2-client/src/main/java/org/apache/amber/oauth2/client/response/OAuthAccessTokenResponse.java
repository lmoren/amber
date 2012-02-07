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

import org.apache.amber.oauth2.client.validator.TokenValidator;
import org.apache.amber.oauth2.common.exception.OAuthProblemException;
import org.apache.amber.oauth2.common.token.OAuthToken;

import java.util.List;
import java.util.Map;

/**
 * @author Maciej Machulak (m.p.machulak@ncl.ac.uk)
 * @author Lukasz Moren (lukasz.moren@ncl.ac.uk)
 * @author Aad van Moorsel (aad.vanmoorsel@ncl.ac.uk)
 */
public abstract class OAuthAccessTokenResponse extends OAuthClientResponse {

    public abstract String getAccessToken();

    public abstract String getExpiresIn();

    public abstract String getRefreshToken();

    public abstract String getScope();

    public abstract OAuthToken getOAuthToken();

    public String getBody() {
        return body;
    }

    @Override
    protected void init(String body, String contentType, int responseCode, Map<String, List<String>> responseHeaders) throws OAuthProblemException {
        validator = new TokenValidator();
        super.init(body, contentType, responseCode, responseHeaders);
    }
}
