package Users;


public class Role {

    private String userName;
    private String fName;
    private String lName;
    private ERoleType roleType;

    public Role(String userName,String fName,String lName) {
        this.userName = userName;
        this.fName = fName;
        this .lName = lName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
}
