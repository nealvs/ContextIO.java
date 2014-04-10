package at.tomtasche.contextio.model;

import java.util.List;

/**
 * @author Neal
 */
public class EmailMessage {
    
    public String emailMessageId;
    public String messageId;
    public String gmailMessageId;
    public String gmailThreadId;
    
    public String subject;
    
    public List<EmailFolder> folders;
    public List<EmailSource> sources;
    public List<EmailFile> files;
    public Long date;
    public Long dateIndexed;
    
    
}

/*
{
    "email_message_id": stringValue of RFC-822 Message-ID header,
    "addresses": objectEmail addresses and names of sender and recipients (more details),
    "person_info": objectAdditional info about contacts on this message (more details),
    "message_id": stringUnique and persistent id assigned by Context.IO to the message,
    "gmail_message_id": stringMessage id assigned by Gmail (only present if source is a Gmail account),
    "gmail_thread_id": stringThread id assigned by Gmail (only present if source is a Gmail account),
    "files": [
      {
        "size": numberSize of the attachment in bytes,
        "type": stringValue of Content-type header for the attachment,
        "file_name": stringFull name of the file attached,
        "file_name_structure": arrayName of the file broken down in semantic parts (more details),
        "body_section": stringIdentifies MIME body part the attachment can be found in,
        "file_id": stringUnique and persistent id assigned by Context.IO to the file,
        "is_embedded": booleanIndicates whether this file is an object embedded in the message or not,
        "content_disposition": stringValue of the Content-Disposition header of the MIME part containing this file, if specified. Typically 'inline' or 'attachment',
        "content_id": stringIf this file is an embedded object, this is the value of the Content-Id header of the MIME part containing this file
      },
      ...
    ],
    "date": numberUnix timestamp of message date,
    "date_indexed": numberTime this message was first seen by Context.IO (unix timestamp),
    "subject": stringSubject of the message,
    "folders": arrayList of folders (or Gmail labels) this message is found in,
    "sources": [
      {
        "label": stringLabel of the source containing this message,
        "resource_url": stringComplete URL of the source
      },
      ...
    ]
  }
*/