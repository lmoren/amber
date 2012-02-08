package org.apache.amber.opendiconnect.common.dynamicreg;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lukasz Moren <lukasz.moren@cloudidentity.co.uk>
 */
public class ClientRegistrationInfo implements Serializable {
    private String type;
    private String clientId;
    private String clientSecret;
    private String applicationType;
    private String applicationName;
    private String logoUrl;
    private List<String> redirectUris;

    private Map<String, String> additionalParams = new HashMap<String, String>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public List<String> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(List<String> redirectUris) {
        this.redirectUris = redirectUris;
    }

    public void addParameter(String key, String value) {
        this.additionalParams.put(key, value);
    }

    public String getParameter(String key) {
       return additionalParams.get(key);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientRegistrationInfo that = (ClientRegistrationInfo) o;

        if (applicationName != null ? !applicationName.equals(that.applicationName) : that.applicationName != null)
            return false;
        if (applicationType != null ? !applicationType.equals(that.applicationType) : that.applicationType != null)
            return false;
        if (logoUrl != null ? !logoUrl.equals(that.logoUrl) : that.logoUrl != null) return false;
        if (redirectUris != null ? !redirectUris.equals(that.redirectUris) : that.redirectUris != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (applicationType != null ? applicationType.hashCode() : 0);
        result = 31 * result + (applicationName != null ? applicationName.hashCode() : 0);
        result = 31 * result + (logoUrl != null ? logoUrl.hashCode() : 0);
        result = 31 * result + (redirectUris != null ? redirectUris.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientRegistrationInfo{" +
                "type='" + type + '\'' +
                '}';
    }
}
