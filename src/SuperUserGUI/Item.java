/*
 * Class to represent an user's login account of the system
 */
package SuperUserGUI;

/**
 *
 * @author DELL
 */
public class Item {

    private int userId;
    private String name, privilege, login, logout;

    //constructor
    public Item(int userId, String name, String privilege, String login, String logout) {
        this.userId = userId;
        this.name = name;
        this.privilege = privilege;
        this.login = login;
        this.logout = logout;
    }

    //set user Id
    public void setUserId(int userId) {
        this.userId = userId;
    }

    //set user name
    public void setName(String name) {
        this.name = name;
    }

    //set privilege
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    //set login time
    public void setLogin(String login) {
        this.login = login;
    }

    //set logout time
    public void setLogout(String logout) {
        this.logout = logout;
    }

    //get user id
    public int getUserId() {
        return userId;
    }

    //returns username
    public String getName() {
        return name;
    }

    //returns privilege
    public String getPrivilege() {
        return privilege;
    }

    //returns logout time
    public String getLogin() {
        return login;
    }

    //returns login time
    public String getLogout() {
        return logout;
    }
}
