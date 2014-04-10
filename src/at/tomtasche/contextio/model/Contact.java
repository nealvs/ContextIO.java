package at.tomtasche.contextio.model;

/**
 * @author Neal
 */
public class Contact {
    public String name;
    public String email;
    public Long count;
    public String thumbnail;
}

/*
 {
      "email": stringemail address of contact,
      "count": numbernumber of messages exchanged with this contact,
      "name": stringName associated to contact,
      "thumbnail": stringURL pointing to Gravatar image associated to contact's email address, if applicable
    }
*/