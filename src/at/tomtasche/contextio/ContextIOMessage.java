package at.tomtasche.contextio;

/**
 * @author Neal
 */
public class ContextIOMessage {
    public String type;
    public Integer code;
    public String value;
    
    @Override
    public String toString() {
        return type + "(" + code + "): " + value;
    }
}

/*
    {"messages" : [{"type":"error","code":105,"value":"Expired timestamp, yours 1396937476, ours 1396933874"}]}
*/