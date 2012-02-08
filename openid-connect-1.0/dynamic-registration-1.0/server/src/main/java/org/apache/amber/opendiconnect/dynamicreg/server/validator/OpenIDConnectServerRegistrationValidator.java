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

package org.apache.amber.opendiconnect.dynamicreg.server.validator;

import org.apache.amber.oauth2.common.OAuth;
import org.apache.amber.oauth2.common.exception.OAuthProblemException;
import org.apache.amber.oauth2.common.utils.OAuthUtils;
import org.apache.amber.oauth2.common.validators.AbstractValidator;
import org.apache.amber.opendiconnect.common.OpenIDConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Maciej Machulak (m.p.machulak@ncl.ac.uk)
 * @author Lukasz Moren (lukasz.moren@ncl.ac.uk)
 * @author Aad van Moorsel (aad.vanmoorsel@ncl.ac.uk)
 */
public class OpenIDConnectServerRegistrationValidator extends AbstractValidator<HttpServletRequest> {

    private Logger log = LoggerFactory.getLogger(OpenIDConnectServerRegistrationValidator.class);

    public OpenIDConnectServerRegistrationValidator() {
        requiredParams.add(OpenIDConnect.DynamicClientRegistration.Request.OC_TYPE);
    }

    public void validateContentType(HttpServletRequest request) throws OAuthProblemException {

        String contentType = request.getContentType();
        final String expectedContentType = OAuth.ContentType.URL_ENCODED;
        if (!OAuthUtils.hasContentType(contentType, expectedContentType)) {
            throw OAuthUtils.handleBadContentTypeException(expectedContentType);
        }
    }

    @Override
    public void validateRequiredParameters(HttpServletRequest request) throws OAuthProblemException {
        super.validateRequiredParameters(request);

        String type = request.getParameter(OpenIDConnect.DynamicClientRegistration.Request.OC_TYPE);
        if (!OpenIDConnect.DynamicClientRegistration.Request.Type.CLIENT_ASSOCIATE.equals(type)
                && !OpenIDConnect.DynamicClientRegistration.Request.Type.CLIENT_UPDATE.equals(type)) {
            throw OAuthProblemException.error(OpenIDConnect.DynamicClientRegistration.Response.ERR_INVALID_TYPE)
                    .description("Provided request type is not valid");
        }
    }
}
