package App;

import java.io.IOException;
import java.util.Map;

public class Admin extends User {
    User userTemp;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
        Admin.id_counter++;
        this.id = id_counter;
    }

    public void addUser(String username, String password) {
        Data.setUsers(username, password);
    }

    public User retrieveUser(String username) throws IOException {
        try {
            if (username == null || username.equals("")) {
                    WarningMessage.show("EmptyField", "Please enter a name");
                    return null;    
            }
            for (Map.Entry<Integer, User> set : Data.DeletedUsers.entrySet()) {
                if (set.getValue().GetUsername().equals(username)) {
                    Data.DeletedUsers.remove(set.getKey());
                    Data.Users.put(set.getKey(), set.getValue());
                    return set.getValue();
                }
            }
            WarningMessage.show("UserNotFound", "User is not deleted or not found");
        } catch (IOException e) {
            WarningMessage.show("Error", "Error while retrieving user");
        }
        return null;
    }

    public void deleteUser(String username) throws IOException {
        userTemp = Data.isInUsers(username);
        if (userTemp != null) {
            Data.DeletedUsers.put(userTemp.getId(), userTemp);
            Data.Users.remove(userTemp.getId());
        } else {
            WarningMessage.show("UserNotFound", "User is not found");
        }
    }
}
