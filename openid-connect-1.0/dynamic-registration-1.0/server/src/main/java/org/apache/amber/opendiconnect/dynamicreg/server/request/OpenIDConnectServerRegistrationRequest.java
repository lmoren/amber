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

package org.apache.amber.opendiconnect.dynamicreg.server.request;

import org.apache.amber.oauth2.as.request.OAuthRequest;
import org.apache.amber.oauth2.common.exception.OAuthProblemException;
import org.apache.amber.oauth2.common.exception.OAuthSystemException;
import org.apache.amber.oauth2.common.utils.OAuthUtils;
import org.apache.amber.oauth2.common.validators.OAuthValidator;
import org.apache.amber.opendiconnect.common.OpenIDConnect;
import org.apache.amber.opendiconnect.common.dynamicreg.ClientRegistrationInfo;
import org.apache.amber.opendiconnect.dynamicreg.server.validator.OpenIDConnectServerRegistrationValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;


/**
 * @author Maciej Machulak (m.p.machulak@ncl.ac.uk)
 * @author Lukasz Moren (lukasz.moren@ncl.ac.uk)
 * @author Aad van Moorsel (aad.vanmoorsel@ncl.ac.uk)
 */
public class OpenIDConnectServerRegistrationRequest extends OAuthRequest {

    private ClientRegistrationInfo clientRegistrationInfo;

    public OpenIDConnectServerRegistrationRequest(HttpServletRequest request)
            throws OAuthSystemException, OAuthProblemException {
        super(request);

        clientRegistrationInfo = new ClientRegistrationInfo();
        clientRegistrationInfo.setApplicationName(getApplicationName());
        clientRegistrationInfo.setApplicationType(getApplicationType());
        clientRegistrationInfo.setType(getType());
        clientRegistrationInfo.setLogoUrl(getLogoUrl());
        clientRegistrationInfo.setRedirectUris(getRedirectURIs());
        clientRegistrationInfo.setClientId(getClientId());
        clientRegistrationInfo.setClientSecret(getClientSecret());
    }

    @Override
    protected OAuthValidator initValidator() throws OAuthProblemException, OAuthSystemException {
        return new OpenIDConnectServerRegistrationValidator();
    }

    private String getType() {
        return getParam(OpenIDConnect.DynamicClientRegistration.Request.OC_TYPE);
    }

    private String getApplicationType() {
        return getParam(OpenIDConnect.DynamicClientRegistration.Request.OC_APPLICATION_TYPE);
    }

    private String getApplicationName() {
        return getParam(OpenIDConnect.DynamicClientRegistration.Request.OC_APPLICATION_NAME);
    }

    private String getLogoUrl() {
        return getParam(OpenIDConnect.DynamicClientRegistration.Request.OC_LOGO_URL);
    }

    private List<String> getRedirectURIs() {
        String uris = getParam(OpenIDConnect.DynamicClientRegistration.Request.OC_REDIRECT_URIS);
        if (!OAuthUtils.isEmpty(uris)) {
            return Arrays.asList(uris.split(" "));
        }

        return null;
    }

    public ClientRegistrationInfo getClientRegistrationInfo() {
        return clientRegistrationInfo;
    }
}
