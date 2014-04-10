package at.tomtasche.contextio;

import at.tomtasche.contextio.model.EmailAccount;
import at.tomtasche.contextio.model.EmailMessage;
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
        checkResponse(response);
        
        JsonArray array = parser.parse(response.rawResponse.getBody()).getAsJsonArray();
        for(JsonElement item : array) {
            EmailAccount accnt = prettyGson.fromJson(item, EmailAccount.class);
            accounts.add(accnt);
        }
        
        return accounts;
    }
    
    /*
    subject	string	Get messages whose subject matches this search string. To use regular expressions instead of simple string matching, make sure the string starts and ends with /.
    email	string	Email address of the contact for whom you want the latest messages exchanged with. By "exchanged with contact X" we mean any email received from contact X, sent to contact X or sent by anyone to both contact X and the source owner.
    to	string	Email address of a contact messages have been sent to.
    from	string	Email address of a contact messages have been received from.
    cc	string	Email address of a contact CC'ed on the messages.
    bcc	string	Email address of a contact BCC'ed on the messages.
    folder	string	Filter messages by the folder (or Gmail label). This parameter can be the complete folder name with the appropriate hierarchy delimiter for the mail server being queried (eg. Inbox/My folder) or the "symbolic name" of the folder (eg. \Starred). The symbolic name refers to attributes used to refer to special use folders in a language-independant way. See http://code.google.com/apis/gmail/imap/#xlist (Gmail specific) and RFC-6154.
    source	string	Filter messages by the account source label.
    file_name	string	Search for files based on their name. You can filter names using typical shell wildcards such as *, ? and [] or regular expressions by enclosing the search expression in a leading / and trailing /. For example, *.pdf would give you all PDF files while /\.jpe?g$/ would return all files whose name ends with .jpg or .jpeg
    date_before	integer 
    (unix time)	Only include messages before a given timestamp. The value this filter is applied to is the Date: header of the message which refers to the time the message is sent from the origin.
    date_after	integer 
    (unix time)	Only include messages after a given timestamp. The value this filter is applied to is the Date: header of the message which refers to the time the message is sent from the origin.
    indexed_before	integer 
    (unix time)	Only include messages indexed before a given timestamp. This is not the same as the date of the email, it is the time Context.IO indexed this message.
    indexed_after	integer 
    (unix time)	Only include messages indexed after a given timestamp. This is not the same as the date of the email, it is the time Context.IO indexed this message.
    include_thread_size	integer	Set to 1 to include thread size in the result.
    include_body	integer	Set to 1 to include message bodies in the result. Since message bodies must be retrieved from the IMAP server, expect a performance hit when setting this parameter.
    include_headers	mixed	Can be set to 0 (default), 1 or raw. If set to 1, complete message headers, parsed into an array, are included in the results. If set to raw, the headers are also included but as a raw unparsed string. Since full original headers bodies must be retrieved from the IMAP server, expect a performance hit when setting this parameter.
    include_flags	integer	Set to 1 to include IMAP flags of messages in the result. Since message flags must be retrieved from the IMAP server, expect a performance hit when setting this parameter.
    body_type	string	Used when include_body is set to get only body parts of a given MIME-type (for example text/html)
    include_source	integer	Set to 1 to include message sources in the result. Since message sources must be retrieved from the IMAP server, expect a performance hit when setting this parameter.
    sort_order	string	The sort order of the returned results. Possible values are asc and desc
    limit	integer	The maximum number of results to return.
    offset	integer	Start the list at this offset (zero-based).
    */
    public List<EmailMessage> messages(String account, Map<String, String> params) throws Exception {
        List<EmailMessage> messages = new ArrayList<EmailMessage>();
        params = filterParams(params, new String[]{"subject", "email", "to", "from", "cc", "bcc", "folder", "source", "file_name", "date_before", "date_after", 
                                                   "indexed_before", "indexed_after", "include_thread_size", "included_body", "include_headers", "include_flags", "body_type",
                                                   "include_source", "sort_order", "limit", "offset"});
        
        ContextIOResponse response = get(account, "accounts/messages", params);
        checkResponse(response);
        
        JsonArray array = parser.parse(response.rawResponse.getBody()).getAsJsonArray();
        for(JsonElement item : array) {
            EmailMessage accnt = prettyGson.fromJson(item, EmailMessage.class);
            messages.add(accnt);
        }
        
        return messages;
    }
    
    

}
