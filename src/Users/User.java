package Users;
import java.util.HashMap;

public class User implements IUser {

    //region Fields
    String userName;
    String userID;
    EStatus status;
    HashMap<ERoleType,Role> eRoleTypeHashMap;

    public User(String userName, String userID) {
        this.userName = userName;
        this.userID = userID;
        this.status = EStatus.ONLINE;
        eRoleTypeHashMap = new HashMap<>();
    }
//TODO: decide how to represent and use the fields :
    // String username
    // String password
    // In order to do so, contact ___ who is on charge on authentication and security.
    //endregion

    //region Methods

    @Override
    public void logout() { //useCase 3.1
        this.status = EStatus.OFFLINE;
    }

    @Override
    public String viewProfile() { //useCase 3.6
        return toString();
    }

    //endregion

    public String getUserName() {
        return userName;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public void addRole(ERoleType eRoleType,Role role){
        this.eRoleTypeHashMap.put(eRoleType,role);
    }

    public void removeRole(ERoleType roleType){
        this.eRoleTypeHashMap.remove(roleType);
    }

    public String getUserID() {
        return userID;
    }

    public boolean existRole(ERoleType eRoleType){
        return this.eRoleTypeHashMap.containsKey(eRoleType);
    }

    public Role getRoleByERoleType(ERoleType eRoleType){
        return this.eRoleTypeHashMap.get(eRoleType);
    }
}
