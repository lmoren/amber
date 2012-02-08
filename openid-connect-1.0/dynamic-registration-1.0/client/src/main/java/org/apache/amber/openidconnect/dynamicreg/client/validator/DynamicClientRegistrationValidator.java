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

package org.apache.amber.openidconnect.dynamicreg.client.validator;

import org.apache.amber.oauth2.client.response.OAuthClientResponse;
import org.apache.amber.oauth2.client.validator.OAuthClientValidator;
import org.apache.amber.oauth2.common.OAuth;
import org.apache.amber.oauth2.common.exception.OAuthProblemException;
import org.apache.amber.opendiconnect.common.OpenIDConnect;

import java.util.List;
import java.util.Map;

public class DynamicClientRegistrationValidator extends OAuthClientValidator {

    public DynamicClientRegistrationValidator() {
        requiredParams.put(OAuth.OAUTH_CLIENT_ID, new String[]{});
        requiredParams.put(OAuth.OAUTH_CLIENT_SECRET, new String[]{});
        requiredParams.put(OpenIDConnect.DynamicClientRegistration.Response.EXPIRES_AT, new String[]{});
    }

    @Override
    public void validateParameters(OAuthClientResponse response) throws OAuthProblemException {
        super.validateParameters(response);
    }
}
