package Users;

public interface IUser {

   void setUserSettings();
   String viewProfile();
   void logout();
   String getUserName();
   void setUserName(String userName);
   String getfName();
   void setfName(String fName);
   String getlName();
   void setlName(String lName);
   EStatus getStatus();
   void setStatus(EStatus status);

}