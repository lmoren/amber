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

package org.apache.amber.oauth2.integration.endpoints;

import org.apache.amber.oauth2.as.issuer.MD5Generator;
import org.apache.amber.oauth2.common.OAuth;
import org.apache.amber.oauth2.common.error.OAuthError;
import org.apache.amber.oauth2.common.exception.OAuthProblemException;
import org.apache.amber.oauth2.common.exception.OAuthSystemException;
import org.apache.amber.oauth2.integration.openidconnect.OpenIDConnectCommon;
import org.apache.amber.opendiconnect.common.response.OpenIDConnectResponse;
import org.apache.amber.opendiconnect.dynamicreg.server.request.OpenIDConnectServerRegistrationRequest;
import org.apache.amber.opendiconnect.dynamicreg.server.response.OpenIDConnectServerRegistrationResponse;
import org.codehaus.jettison.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * @author Maciej Machulak (m.p.machulak@ncl.ac.uk)
 * @author Lukasz Moren (lukasz.moren@ncl.ac.uk)
 * @author Aad van Moorsel (aad.vanmoorsel@ncl.ac.uk)
 */
@Path("/register")
public class OpenIDConnectRegistrationEndpoint {


    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response register(@Context HttpServletRequest request) throws OAuthSystemException {

        try {
            OpenIDConnectServerRegistrationRequest registrationRequest = new OpenIDConnectServerRegistrationRequest(request);

            OpenIDConnectResponse openIDConnectResponse = OpenIDConnectServerRegistrationResponse
                    .status(200)
                    .setClientId(OpenIDConnectCommon.CLIENT_ID)
                    .setClientSecret(OpenIDConnectCommon.CLIENT_SECRET)
                    .setExpiresAt(OpenIDConnectCommon.EXPIRES_AT)
                    .buildJSONMessage();

            return Response.status(200).entity(openIDConnectResponse.getBody()).build();
        } catch (OAuthProblemException e) {
            // Return the OAuth error message
            OpenIDConnectResponse oauthResponse = OpenIDConnectResponse
                    .errorStatus(HttpServletResponse.SC_BAD_REQUEST)
                    .error(e)
                    .buildHeaderMessage();

            return Response.status(oauthResponse.getResponseStatus())
                    .header(OAuth.HeaderType.WWW_AUTHENTICATE,
                            oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE))
                    .build();
        }

    }
}