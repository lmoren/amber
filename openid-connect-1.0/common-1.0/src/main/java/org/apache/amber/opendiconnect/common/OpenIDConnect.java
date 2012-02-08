package org.apache.amber.opendiconnect.common;

/**
 * @author Lukasz Moren <lukasz.moren@cloudidentity.co.uk>
 */
public final class OpenIDConnect {

    public static class DynamicClientRegistration {
        public static final class Request {
            public static final String OC_TYPE = "type";
            public static final String OC_CLIENT_ID = "client_id";
            public static final String OC_CLIENT_SECRET = "client_secret";
            public static final String OC_ACCESS_TOKEN = "access_token";
            public static final String OC_CONTACTS = "contacts";
            public static final String OC_APPLICATION_TYPE = "application_type";
            public static final String OC_APPLICATION_NAME = "application_name";
            public static final String OC_LOGO_URL = "logo_url";
            public static final String OC_REDIRECT_URIS = "redirect_uris";
            public static final String OC_TOKEN_ENDPOINT_AUTH_TYPE = "token_endpoint_auth_type";
            public static final String OC_POLICY_URL = "policy_url";
            public static final String OC_JWK_URL = "jwk_url";
            public static final String OC_JWK_ENCRYPTION_URL = "jwk_encryption_url";
            public static final String OC_X509_URL = "x509_url";
            public static final String OC_X509_ENCRYPTION_URL = "x509_encryption_url";
            public static final String OC_SECTOR_IDENTIFIER_URL = "SECTOR_IDENTIFIER_URL";
            public static final String OC_USER_ID_TYPE = "user_id_type";
            public static final String OC_REQUIRE_SIGNED_REQUEST_OBJECT = "require_signed_request_object";
            public static final String OC_USER_INFO_SIGNED_REQUEST_ALGS = "userinfo_signed_response_algs";
            public static final String OC_USER_INFO_ENCRYPTED_RESPONSE_ALGS = "userinfo_encrypted_response_algs";
            public static final String OC_ID_TOKEN_SIGNED_RESPONSE_ALGS = "id_token_signed_response_algs";
            public static final String OC_ID_TOKEN_ENCRYPTED_RESPONSE_ALGS = "id_token_encrypted_response_algs";

            public static final class Type {
                public static final String CLIENT_ASSOCIATE = "client_associate";
                public static final String CLIENT_UPDATE = "client_update";
            }
        }

        public static final class Response {
            public static final String EXPIRES_AT = "expires_at";

            public static final String ERR_INVALID_TYPE = "invalid_type";
            public static final String ERR_INVALID_CLIENT_ID = "invalid_client_id";
            public static final String ERR_INVALID_CLIENT_SECRET = "invalid_client_secret";
            public static final String ERR_INVALID_CONFIGURATION_PARAMETER = "invalid_configuration_parameter";
        }

    }
}
