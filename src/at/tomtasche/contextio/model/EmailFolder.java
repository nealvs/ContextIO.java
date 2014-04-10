package at.tomtasche.contextio.model;

/**
 * @author Neal
 */
public class EmailFolder {
    public String name;
    public String symbolicName;
}

/*
{
    "name": stringName of an IMAP folder, 
    "symbolic_name": stringSpecial-use attribute of this folder (if and only if the server supports it and applicable to this folder)
  }
*/