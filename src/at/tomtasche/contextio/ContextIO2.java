package at.tomtasche.contextio;

import at.tomtasche.contextio.model.EmailAccount;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Neal
 */
public class ContextIO2 extends ContextIOBase {

    JsonParser parser = new JsonParser();
    Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
    
    public ContextIO2(String key, String secret) {
        this.key = key;
        this.secret = secret;
        this.ssl = true;
        this.saveHeaders = false;
        this.apiVersion = "2.0";
    }

    /*
    email	string	Only return account associated to this email address
    status	string	Only return accounts with sources whose status is of a specific value. If an account has many sources, only those matching the given value will be included in the response. Possible statuses are: INVALID_CREDENTIALS, CONNECTION_IMPOSSIBLE, NO_ACCESS_TO_ALL_MAIL, OK, TEMP_DISABLED and DISABLED
    status_ok	integer	Set to 0 to get all accounts with sources that are not working correctly. Set to 1 for the opposite. As for the status filter above, only sources matching the specific value are included in the response.
    limit	integer	The maximum number of results to return.
    offset      integer	Start the list at this offset (zero-based).
    */
    public List<EmailAccount> accounts(Map<String, String> params) throws Exception {
        List<EmailAccount> accounts = new ArrayList<EmailAccount>();
        params = filterParams(params, new String[]{"email", "status", "status_ok", "limit", "offset"});
        String account = null;
        ContextIOResponse response = get(account, "accounts", params);
        response.decodeResponse();
        
        if(!response.hasError) {
            JsonArray array = parser.parse(response.rawResponse.getBody()).getAsJsonArray();
            for(JsonElement item : array) {
                EmailAccount accnt = prettyGson.fromJson(item, EmailAccount.class);
                accounts.add(accnt);
            }
        } else {
            throw new Exception(response.rawResponse.getBody());
        }
        
        return accounts;
    }
    

    

}
