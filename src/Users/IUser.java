package Users;

public interface IUser {
   String viewProfile();
   void logout();
    String getUserName();
    EStatus getStatus();
    void setStatus(EStatus status);

}
