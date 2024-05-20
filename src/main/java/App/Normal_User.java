package App;


public class Normal_User extends User   {

    public Normal_User(String username, String password) {
        this.username = username;
        this.password = password;
        this.id = ++id_counter;
    }
}
