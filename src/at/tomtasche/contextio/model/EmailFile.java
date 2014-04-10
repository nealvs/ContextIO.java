package at.tomtasche.contextio.model;

import java.util.List;

/**
 * @author Neal
 */
public class EmailFile {

    public String fileId;
    public String contentId;
    public String fileName;
    public Long size;
    public String type;
    public String disposition;
    public boolean supportsPreview;
    public boolean isTNEFPart;
    public FileNameStructure fileNameStructure;
    public String bodySection;
    public boolean isEmbedded;
    public String contentDisposition;
    
}

/*
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
      }
*/