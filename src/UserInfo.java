/**
 * Stores additional information about a user such as full name,
 * contact details, address, and language preference.
 */
public class UserInfo 
{

    /** The full name of the user. */
    // private String fullName;

    /** The phone number of the user. */
    private String phoneNumber;

    // Constructor
    public UserInfo( String phoneNumber) 
    {
        // this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    // public String getFullName() {
    //     return fullName;
    // }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }

    // Setters
    // public void setFullName(String fullName) {
    //     this.fullName = fullName;
    // }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
}

