package Users;

import java.util.HashMap;
import java.util.HashSet;

public class Role {

    private String userName;
    private HashSet<EUserType> roleHashSet;

    public Role(String userName) {
        this.userName = userName;
        this.roleHashSet = new HashSet<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRoleArrayList(HashSet<EUserType> roleHashSet) {
        this.roleHashSet = roleHashSet;
    }

    public void addRole(EUserType role){
        this.roleHashSet.add(role);
    }

    public void removeRole(EUserType role){
        this.roleHashSet.remove(role);
    }
}
