//Classe che rappresenta un Account nel database
package mockDB;
import java.util.Date;

public class AccountDB {
    private String username; //PK
    private String salt;
    private String hashpassword;
    private Date lastLogin;
    private boolean isAdministrator;

    public AccountDB(String username, String salt, String hashpassword, Date lastLogin, boolean isAdministrator) {
        this.username = username;
        this.salt = salt;
        this.hashpassword = hashpassword;
        this.lastLogin = lastLogin;
        this.isAdministrator = isAdministrator;
    }

    public String getUsername() {
        return username;
    }

    public String getSalt() {
        return salt;
    }

    public String getHashpassword() {
        return hashpassword;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setHashpassword(String hashpassword) {
        this.hashpassword = hashpassword;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}