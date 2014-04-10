package at.tomtasche.contextio.model;

/**
 * @author Neal
 */
public class RelatedFile {

    public String fileId;
    public String fileName;
    
    public Long size;
    public String type;
    public String subject;
    public Long date;
    public Long dateIndexed;
    public EmailAddresses addresses;
    public PersonalInfo personalInfo;
    public Long similarity;
    public FileNameStructure fileNameStructure;
    
    public boolean supportsPreview;
    public boolean supportsDiff;
    public boolean isEmbedded;
    
    public String bodySection;
    public String contentDisposition;
    public String contentId;
    public String messageId;
    public String emailMessageId;
    public String gmailMessageId;
    public String gmailThreadId;
    
}

/*
 {
    "size": numberSize of file in bytes.,
    "type": stringMIME type as specified in message source,
    "subject": stringSubject line of message this file is attached to,
    "date": numberUnix timestamp of the message,
    "date_indexed": numberTime this message was first seen by Context.IO (unix timestamp),
    "addresses": objectEmail addresses and names of sender and recipients (more details),
    "person_info": objectAdditional info about contacts on the message (more details),
    "similarity": numbersimilarity factor of the file's name,
    "file_name": stringName of file,
    "file_name_structure": arrayName of the file broken down in semantic parts (more details),
    "file_id": stringUnique and persistent id for this file,
    "supports_preview": booleanwhether or not the file supports our preview function,
    "is_embedded": booleanIndicates whether this file is an object embedded in the message or not,
    "content_disposition": stringValue of the Content-Disposition header of the MIME part containing this file, if specified. Typically 'inline' or 'attachment',
    "content_id": stringIf this file is an embedded object, this is the value of the Content-Id header of the MIME part containing this file,
    "message_id": stringContext.IO id of the message this file is attached to,
    "email_message_id": stringValue of RFC-822 Message-ID header this file is attached to,
    "gmail_message_id": stringGmail message id the file is attached to (only present if source is a Gmail account),
    "gmail_thread_id": stringGmail thread id the file is attached to (only present if source is a Gmail account)
  }
*/