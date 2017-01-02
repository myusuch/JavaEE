// User.java
// =================
// JavaBean class (Model) to define user information.
//
// AUTHOR: Myungsuk Choi (myusuch@gmail.com, 991 388 329)
// CREATED: 2016-07-29
// UPDATED: 2016-08-02

package javaclub;

public class User {

    private String id;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    // No Arguments ctors
    public User() {
        id = null;
        password = null;
        firstName = null;
        lastName= null;
        email = null;
    }

    // ctors with arguments
    public User(String id, String password, String firstName, String lastName, String email) {
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
