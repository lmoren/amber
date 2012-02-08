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

package org.apache.amber.oauth2.integration.openidconnect;

import org.apache.amber.oauth2.client.URLConnectionClient;
import org.apache.amber.oauth2.client.request.OAuthClientRequest;
import org.apache.amber.oauth2.common.error.OAuthError;
import org.apache.amber.oauth2.common.exception.OAuthProblemException;
import org.apache.amber.oauth2.common.exception.OAuthSystemException;
import org.apache.amber.oauth2.ext.dynamicreg.client.OAuthRegistrationClient;
import org.apache.amber.oauth2.ext.dynamicreg.client.response.OAuthClientRegistrationResponse;
import org.apache.amber.oauth2.integration.ClientServerOAuthTest;
import org.apache.amber.oauth2.integration.CommonExt;
import org.apache.amber.opendiconnect.common.OpenIDConnect;
import org.apache.amber.openidconnect.dynamicreg.client.OpenIDConnectRegistrationClient;
import org.apache.amber.openidconnect.dynamicreg.client.request.OpenIDConnectClientRegistrationRequest;
import org.apache.amber.openidconnect.dynamicreg.client.response.OpenIDConnectClientRegistrationResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Maciej Machulak (m.p.machulak@ncl.ac.uk)
 * @author Lukasz Moren (lukasz.moren@ncl.ac.uk)
 * @author Aad van Moorsel (aad.vanmoorsel@ncl.ac.uk)
 */
public class OpenIDConnectClientRegistrationTest extends ClientServerOAuthTest {

    @Test
    public void testSuccessfulRegistration() throws Exception {

        OAuthClientRequest registrationRequest = OpenIDConnectClientRegistrationRequest.location(OpenIDConnectCommon.OC_DYNAMIC_CLIENT_REG_ENDPOINT,
                OpenIDConnect.DynamicClientRegistration.Request.Type.CLIENT_ASSOCIATE).buildBodyMessage();

        OpenIDConnectRegistrationClient oauthclient = new OpenIDConnectRegistrationClient(new URLConnectionClient());
        OpenIDConnectClientRegistrationResponse response = oauthclient.registerClient(registrationRequest);

        assertEquals(OpenIDConnectCommon.CLIENT_ID, response.getCredentials().getClientId());
        assertEquals(OpenIDConnectCommon.CLIENT_SECRET, response.getCredentials().getClientSecret());
        assertEquals(OpenIDConnectCommon.EXPIRES_AT, response.getCredentials().getExpiresAt());

    }

    @Test
    public void testMissingType() throws OAuthSystemException, IOException {

        OAuthClientRequest registrationRequest = OpenIDConnectClientRegistrationRequest.location(OpenIDConnectCommon.OC_DYNAMIC_CLIENT_REG_ENDPOINT,
                "").buildBodyMessage();

        try {
            OpenIDConnectRegistrationClient oauthclient = new OpenIDConnectRegistrationClient(new URLConnectionClient());
            OpenIDConnectClientRegistrationResponse response = oauthclient.registerClient(registrationRequest);
            fail("Excpected exception");
        }  catch (OAuthProblemException e) {
            Assert.assertEquals(OAuthError.ResourceResponse.INVALID_REQUEST, e.getError());
        }

    }

}
