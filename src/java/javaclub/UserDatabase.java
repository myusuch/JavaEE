// User.java
// =================
// to interact with User table in database. (Model)
//
// AUTHOR: Myungsuk Choi (myusuch@gmail.com, 991 388 329)
// CREATED: 2016-07-29
// UPDATED: 2016-08-05
package javaclub;

import ejd.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;

public class UserDatabase {

    // constants for DB access
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ejd";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "Lena2006";

    // member vars
    private final JdbcHelper jdbc;

    // ctor
    public UserDatabase() {
        jdbc = new JdbcHelper();
    }

    ////////////////////////////////////////////////////////////////////////
    // add new user to database
    // It takes 5 params: id, password, firstName, lastName, email.
    // the password must be hashed before adding it to DB.
    // If success return 1. if failed return -1
    ////////////////////////////////////////////////////////////////////////
    public int addUser(String userId, String password, String firstName,
            String lastName, String email) {

        // check null / empty arguments, if so, do nothing
        if (userId == null || userId.isEmpty()
                || password == null || password.isEmpty()
                || firstName == null || firstName.isEmpty()
                || lastName == null || lastName.isEmpty()
                || email == null || email.isEmpty()) {
            return -1;
        }

        // check if the user ID is already in DB. If so, don’t add it
        // HINT: use isUnique()
        if (!isUnique(userId)) {
            return -1;
        }

        // hash password first using jBCrypt
        String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("password = " + hashedPass);

        // sql for prepared statement
        String sql = "INSERT INTO jcUser (id, password, firstName, lastName, email)"
                + "VALUES (?, ?, ?, ?, ?)";

        // build the param list for prepared statement
        ArrayList<Object> params = new ArrayList<>();

        params.add(userId);
        params.add(hashedPass);
        params.add(firstName);
        params.add(lastName);
        params.add(email);

        // execute SQL and return the result
        jdbc.connect(DB_URL, DB_USER, DB_PASS);

        int result = jdbc.update(sql, params);

        jdbc.disconnect();
        return result;
    }

    ////////////////////////////////////////////////////////////////////////
    // find a user using a given userID from DB, and return it
    // if not found one, return null
    ////////////////////////////////////////////////////////////////////////
    public User getUser(String userId) {

        // check null / empty argument
        if (userId == null || userId.isEmpty()) {
            return null;
        }

        // init the return value
        User userFromDB = null;

        System.out.println("Connected " + jdbc.connect(DB_URL, DB_USER, DB_PASS));

        // set sql for prepared statement
        String sql = "SELECT * FROM jcUser WHERE id = ?";

        // set parameter for prepared statement
        ArrayList<Object> params = new ArrayList<>();

        params.add(userId);


        try {
            // execute prepared statement
            ResultSet result = jdbc.query(sql, params);

            // copy user info from DB to JavaBean object
            if (result.first()) {

                String id = result.getString(1);
                String password = result.getString(2);
                String firstName = result.getString(3);
                String lastName = result.getString(4);
                String email = result.getString(5);

                userFromDB = new User(id, password, firstName, lastName, email);
            }

        } catch (SQLException e) {
            System.err.println(e.getSQLState() + ": " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        jdbc.disconnect();

        return userFromDB;
    }

    ///////////////////////////////////////////////////////////////////////
    // check if the given user ID is unique
    // If it does exist not in DB yet, return true. Otherwise return false
    ///////////////////////////////////////////////////////////////////////
    public boolean isUnique(String userId) {

        // check null / empty arg
        if (userId == null || userId.isEmpty()) {
            return false;
        }
        // try to get a user from DB first using getUser()
        // If getUser() returns null, then the user ID is unique
        // If it is not null, then the user ID is already taken

        return getUser(userId) == null;
    }

    ////////////////////////////////////////////////////////////////////////
    // check if both user ID and password are match the one in DB
    // Note that the password in DB are hashed.
    ////////////////////////////////////////////////////////////////////////
    public boolean isValid(String idFromWB, String passwordFromWB) {

        System.out.println("idFromWB == " + idFromWB);
        System.out.println("passwordFromWB == " + passwordFromWB);

        // check null / empty arg
        if (idFromWB == null || idFromWB.isEmpty()) {
            return false;
        }

        if (passwordFromWB == null || passwordFromWB.isEmpty()) {
            return false;
        }

        // try to get a user from DB first using getUser()
        // If getUser() returns null, return false
        User userfromDB = getUser(idFromWB);

        // If it returns not null, check ID and password are matched
        if (userfromDB == null) {
            return false;
        }

        // To compare user ID, use equals()
        if (!idFromWB.equals(userfromDB.getId())) {
            return false;
        }

        String passwordFromDB = userfromDB.getPassword();
        // To compare password, use BCrypt.checkpw()
        // generate hashed password using bcrypt
        return BCrypt.checkpw(passwordFromWB, passwordFromDB);
    }

}
