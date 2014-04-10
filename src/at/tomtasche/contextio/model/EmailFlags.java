package at.tomtasche.contextio.model;

/**
 * @author Neal
 */
public class EmailFlags {
    public boolean seen;
    public boolean answered;
    public boolean flagged;
    public boolean deleted;
    public boolean draft;
    public boolean nonjunk;
}

/*
{
  "seen": booleanwhether or not a message has been viewed,
  "answered": booleanwhether or not a message has been replied to,
  "flagged": booleanwhether or not a message has been flagged,
  "deleted": booleanwhether or not a message has been deleted,
  "draft": booleanwhether or not a message is in draft mode,
  "nonjunk": booleanwhether or not a message has been flagged as "junk" mail,
}
*/