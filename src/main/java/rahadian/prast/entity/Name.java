package rahadian.prast.entity;

import javax.persistence.Embeddable;

/**
 * Author ian
 * create 03/01/25 21.37
 */
@Embeddable
public class Name {

    private String title;
    private String firstName;
    private String middleName;
    private String lastName;

    public Name() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
