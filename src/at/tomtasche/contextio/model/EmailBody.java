package at.tomtasche.contextio.model;

/**
 * @author Neal
 */
public class EmailBody {
    public String type;
    public String charset;
    public String content;
    public String bodySection;
}

/*
{
    "type": stringMIME type of message part being fetched,
    "charset": stringencoding of the characters in the part of message,
    "content": stringthe actual content of the message part being pulled,
    "body_section": stringindicating position of the part in the body structure,
  }
*/