package at.tomtasche.contextio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.scribe.utils.URLUtils;

/**
 * @author Neal
 */
public abstract class ContextIOBase {

    JsonParser parser = new JsonParser();
    Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
    
    static final String ENDPOINT = "api.context.io";

    String key;
    String secret;
    String apiVersion;
    boolean ssl;
    boolean saveHeaders;
    boolean authHeaders;
    ContextIOResponse lastResponse;
    
    
    public boolean isSsl() {
        return ssl;
    }

    /**
     * Specify whether or not API calls should be made over a secure connection.
     * HTTPS is used on all calls by default.
     *
     * @param sslOn Set to false to make calls over HTTP, true to use HTTPS
     */
    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    /**
     * Set the API version. By default, the latest official version will be used
     * for all calls.
     *
     * @param apiVersion Context.IO API version to use
     */
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public boolean isAuthHeaders() {
        return authHeaders;
    }

    /**
     * Specify whether OAuth parameters should be included as URL query
     * parameters or sent as HTTP Authorization headers. The default is URL
     * query parameters.
     *
     * @param authHeadersOn Set to true to use HTTP Authorization headers, false
     * to use URL query params
     */
    public void setAuthHeaders(boolean authHeaders) {
        this.authHeaders = authHeaders;
    }

    /**
     * Returns the ContextIOResponse object for the last API call.
     *
     * @return ContextIOResponse
     */
    public ContextIOResponse getLastResponse() {
        return lastResponse;
    }

    public String build_baseurl() {
        String url = "http";
        if (ssl) {
            url = "https";
        }

        return url + "://" + ENDPOINT + "/" + apiVersion + '/';
    }

    public String build_url(String action) {
        return build_baseurl() + action;
    }

    public boolean isSaveHeaders() {
        return saveHeaders;
    }

    public void setSaveHeaders(boolean saveHeaders) {
        this.saveHeaders = saveHeaders;
    }

    public ContextIOResponse[] get(String[] accounts, String action, Map<String, String> params) {
        ContextIOResponse[] responses = new ContextIOResponse[accounts.length];
        for (int i = 0; i < accounts.length; i++) {
            responses[i] = doCall("GET", accounts[i], action, params);
        }

        return responses;
    }

    public ContextIOResponse get(String account, String action, Map<String, String> params) {
        return doCall("GET", account, action, params);
    }

    public ContextIOResponse post(String account, String action, Map<String, String> params) {
        return doCall("POST", account, action, params);
    }

    public ContextIOResponse doCall(String method, String account, String action, Map<String, String> params) {
		// TODO: differs from original implementiation

        if (account != null && !account.equals("")) {
            if (params == null) {
                params = new HashMap<String, String>();
            }

            params.put("account", account);
        }

        String baseUrl = build_url(action);
        if ("GET".equals(method)) {
            baseUrl = URLUtils.appendParametersToQueryString(baseUrl, params);
        }

        OAuthService service = new ServiceBuilder().provider(ContextIOApi.class).apiKey(this.key).apiSecret(this.secret).build();
        OAuthRequest request = new OAuthRequest(Verb.GET, baseUrl);

        Token nullToken = new Token("", "");
        service.signRequest(nullToken, request);

        Response oauthResponse = request.send();

        lastResponse = new ContextIOResponse(oauthResponse.getCode(), request.getHeaders(), oauthResponse.getHeaders(), oauthResponse);
        if (lastResponse.hasError) {
            return null;
        } else {
            return lastResponse;
        }
    }
   
    /**
     * Examines the response for errors and throws an exception if found
     * @param response
     * @throws Exception 
     */
    public void checkResponse(ContextIOResponse response) throws Exception {
        response.decodeResponse();
        if(response.hasError) {
            String message = "";
            if(response.messages != null) {
                for(ContextIOMessage msg : response.messages) {
                    message = msg.toString() + "\n";
                }
            }
            throw new Exception(message);
        }
    }
    
    public void printResponse(ContextIOResponse response) {
        String pretty = prettyGson.toJson(response.json);
        System.out.println(pretty);
    }
    
    public Map<String, String> filterParams(Map<String, String> givenParams, String[] validParams) {
        Map<String, String> filteredParams = new HashMap<String, String>();

        if(givenParams != null && validParams != null) {
            for (String validKey : validParams) {
                for (String givenKey : givenParams.keySet()) {
                    if (givenKey.equalsIgnoreCase(validKey)) {
                        filteredParams.put(validKey, givenParams.get(givenKey));
                    }
                }
            }
        }

        return filteredParams;
    }
    
}
