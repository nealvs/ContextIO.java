package at.tomtasche.contextio.model;

import java.util.List;

/**
 * @author Neal
 */
public class EmailAccount {
    
    public static final String INVALID_CREDENTIALS = "INVALID_CREDENTIALS";
    public static final String CONNECTION_IMPOSSIBLE = "CONNECTION_IMPOSSIBLE";
    public static final String NO_ACCESS_TO_ALL_MAIL = "NO_ACCESS_TO_ALL_MAIL";
    public static final String OK = "OK";
    public static final String TEMP_DISABLED = "TEMP_DISABLED";
    public static final String DISABLED = "DISABLED";
    
    public String id;
    public String username;
    public Long created;
    public Long suspended;
    public List<EmailContact> emailAddresses;
    public String firstName;
    public String lastName;
    public Long passwordExpired;
   
    public List<EmailSource> sources;
    public Long nbMessages;
    public Long nbFiles;
}

/*
{
    "id": stringId of the account,
    "username": stringUsername assigned to the account,
    "created": numberUnix timestamp of account creation time,
    "suspended": numberUnix timestamp of account suspension time 0 means not suspended,
    "email_addresses": arrayArray of email addresses for this account,
    "first_name": stringFirst name of account holder,
    "last_name": stringLast name of account holder,
    "password_expired": numberUnix timestamp of user's password expiration. 0 means still valid,
    "sources": arrayList of email accounts where this account gets data from. See sources for details,
    "nb_messages": numberTotal number of messages in all sources of this account,
    "nb_files": numberTotal number of files in all sources of this account
  }
*/