package org.apache.amber.opendiconnect.common.response;

import org.apache.amber.oauth2.common.OAuth;
import org.apache.amber.oauth2.common.error.OAuthError;
import org.apache.amber.oauth2.common.exception.OAuthProblemException;
import org.apache.amber.oauth2.common.exception.OAuthSystemException;
import org.apache.amber.oauth2.common.message.OAuthResponse;
import org.apache.amber.oauth2.common.parameters.BodyURLEncodedParametersApplier;
import org.apache.amber.oauth2.common.parameters.JSONBodyParametersApplier;
import org.apache.amber.oauth2.common.parameters.QueryParameterApplier;
import org.apache.amber.oauth2.common.parameters.WWWAuthHeaderParametersApplier;

/**
 * @author Lukasz Moren <lukasz.moren@cloudidentity.co.uk>
 */
public class OpenIDConnectResponse extends OAuthResponse {
    protected OpenIDConnectResponse(String uri, int responseStatus) {
        super(uri, responseStatus);
    }



    public static OpenIDConnectResponseBuilder status(int code) {
        return new OpenIDConnectResponseBuilder(code);
    }

    public static OpenIDConnectErrorResponseBuilder errorStatus(int code) {
        return new OpenIDConnectErrorResponseBuilder(code);
    }

    public static class OpenIDConnectResponseBuilder extends OAuthResponseBuilder {

        public OpenIDConnectResponseBuilder(int responseCode) {
            super(responseCode);
        }

        public OpenIDConnectResponseBuilder location(String location) {
            this.location = location;
            return this;
        }

        public OpenIDConnectResponseBuilder setScope(String value) {
            this.parameters.put(OAuth.OAUTH_SCOPE, value);
            return this;
        }

        public OpenIDConnectResponseBuilder setParam(String key, String value) {
            this.parameters.put(key, value);
            return this;
        }

        public OpenIDConnectResponse buildQueryMessage() throws OAuthSystemException {
            OpenIDConnectResponse msg = new OpenIDConnectResponse(location, responseCode);
            this.applier = new QueryParameterApplier();
            return (OpenIDConnectResponse) applier.applyOAuthParameters(msg, parameters);
        }

        public OpenIDConnectResponse buildBodyMessage() throws OAuthSystemException {
            OpenIDConnectResponse msg = new OpenIDConnectResponse(location, responseCode);
            this.applier = new BodyURLEncodedParametersApplier();
            return (OpenIDConnectResponse) applier.applyOAuthParameters(msg, parameters);
        }

        public OpenIDConnectResponse buildJSONMessage() throws OAuthSystemException {
            OpenIDConnectResponse msg = new OpenIDConnectResponse(location, responseCode);
            this.applier = new JSONBodyParametersApplier();
            return (OpenIDConnectResponse) applier.applyOAuthParameters(msg, parameters);
        }

        public OpenIDConnectResponse buildHeaderMessage() throws OAuthSystemException {
            OpenIDConnectResponse msg = new OpenIDConnectResponse(location, responseCode);
            this.applier = new WWWAuthHeaderParametersApplier();
            return (OpenIDConnectResponse) applier.applyOAuthParameters(msg, parameters);
        }
    }

    public static class OpenIDConnectErrorResponseBuilder extends OpenIDConnectResponseBuilder {

        public OpenIDConnectErrorResponseBuilder(int responseCode) {
            super(responseCode);
        }

        public OpenIDConnectErrorResponseBuilder error(OAuthProblemException ex) {
            this.parameters.put(OAuthError.OAUTH_ERROR, ex.getError());
            this.parameters.put(OAuthError.OAUTH_ERROR_DESCRIPTION, ex.getDescription());
            this.parameters.put(OAuthError.OAUTH_ERROR_URI, ex.getUri());
            this.parameters.put(OAuth.OAUTH_STATE, ex.getState());
            return this;
        }

        public OpenIDConnectErrorResponseBuilder setError(String error) {
            this.parameters.put(OAuthError.OAUTH_ERROR, error);
            return this;
        }

        public OpenIDConnectErrorResponseBuilder setErrorDescription(String desc) {
            this.parameters.put(OAuthError.OAUTH_ERROR_DESCRIPTION, desc);
            return this;
        }

        public OpenIDConnectErrorResponseBuilder setErrorUri(String state) {
            this.parameters.put(OAuthError.OAUTH_ERROR_URI, state);
            return this;
        }

        public OpenIDConnectErrorResponseBuilder setState(String state) {
            this.parameters.put(OAuth.OAUTH_STATE, state);
            return this;
        }

        public OpenIDConnectErrorResponseBuilder setRealm(String realm) {
            this.parameters.put(OAuth.WWWAuthHeader.REALM, realm);
            return this;
        }

        public OpenIDConnectErrorResponseBuilder location(String location) {
            this.location = location;
            return this;
        }
    }

}
