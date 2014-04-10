package at.tomtasche.contextio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.scribe.model.Response;

/**
 * @author Thomas Taschauer | tomtasche.at
 */
public class ContextIOResponse {

    JsonParser parser = new JsonParser();
    Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
    
    List<ContextIOMessage> messages = null;
    int code;
    Map<String, String> headers;
    Map<String, String> requestHeaders;
    Map<String, String> responseHeaders;
    String contentType;
    Response rawResponse;
    boolean hasError;
    JsonElement json;

    public ContextIOResponse(int code, Map<String, String> requestHeaders, Map<String, String> responseHeaders, Response rawResponse) {
        this.code = code;
        this.requestHeaders = requestHeaders;
        this.responseHeaders = responseHeaders;
        this.rawResponse = rawResponse;
        this.contentType = rawResponse.getHeader("Content-Type");
        // TODO: this.headers = ;
    }

    public void decodeResponse() {
        if (code != 200 || !contentType.equals("application/json")) {
            hasError = true;
        } else {
            json = parser.parse(rawResponse.getBody());
            if(json.isJsonObject() && json.getAsJsonObject().has("messages") 
                    && json.getAsJsonObject().get("messages").isJsonArray()
                    && json.getAsJsonObject().get("messages").getAsJsonArray().size() > 0) {
                hasError = true;
                messages = new ArrayList<ContextIOMessage>();
                for(JsonElement message : json.getAsJsonObject().get("messages").getAsJsonArray()) {
                    ContextIOMessage msg = prettyGson.fromJson(message, ContextIOMessage.class);
                    messages.add(msg);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "ContextIOResponse [code=" + code + ", headers=" + headers
                + ", requestHeaders=" + requestHeaders + ", responseHeaders="
                + responseHeaders + ", contentType=" + contentType
                + ", rawResponse=" + rawResponse + ", hasError=" + hasError
                + ", response=" + rawResponse.getBody() + "]";
    }
}
