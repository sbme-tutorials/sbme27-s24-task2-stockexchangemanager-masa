package App;

public class UserFactory {
    public static final int ADMIN = 1;
    public static final int NORMAL = 2;

    public User GetUser(int type, String username, String password){
        if( type == NORMAL){
            return new Normal_User(username, password);
        }

        else if ( type == ADMIN ) {
           return new Admin(username, password);
        }

        return null;
    }
}
