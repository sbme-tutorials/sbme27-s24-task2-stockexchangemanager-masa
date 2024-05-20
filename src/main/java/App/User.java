
package App;

import java.util.Objects;

public abstract class User {
    protected String username;
    protected String password;
    protected static int id_counter;
    protected int id;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getPassword() {
        return password;
    }

    public String GetUsername() {
        return username;
    }

    public static boolean ChangeUsername(String New_name, String type) {
        if (type.equalsIgnoreCase("normal")) {
            if (New_name != null) {
                for (User user : Data.Users.values()) {
                    if (Objects.equals(user.username, New_name)) {
                        return false;
                    }
                }
                Data.Users.get(Data.TempID).setUsername(New_name);
                return true;
            }

        } else if (type.equalsIgnoreCase("Admin")) {
            if (New_name != null) {
                for (User admin : Data.Admins.values()) {
                    if (Objects.equals(admin.username, New_name)) {
                        return false;
                    }
                }
                Data.Admins.get(Data.TempID).setUsername(New_name);
                return true;
            }

        }
        return false;
    }

    public static boolean ChangePassword(String current_password, String New_password, String type) {
        if (type.equalsIgnoreCase("normal")) {
            if (Objects.equals(Data.Users.get(Data.TempID).getPassword(), current_password)) {
                if (New_password != null) {
                    Data.Users.get(Data.TempID).setPassword(New_password);
                    return true;
                }
            }
        } else if (type.equalsIgnoreCase("Admin")) {
            if (Objects.equals(Data.Admins.get(Data.TempID).password, current_password)) {
                if (New_password != null) {
                    Data.Admins.get(Data.TempID).setPassword(New_password);
                    return true;
                }
            }
        }
        return false;
    }

}
