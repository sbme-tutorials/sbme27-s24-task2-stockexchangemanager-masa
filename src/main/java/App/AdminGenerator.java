package App;

/**
 * A class that allows an admin to add a new admin
 *
 * @author Mazen Atef
 * @version 1.0
 * @scince 2024-05-12
 */

 public class AdminGenerator {
    public static AdminGenerator adminGenerator = null;

    private AdminGenerator(){}

    public static AdminGenerator getAdminGenerator() {
        if (adminGenerator == null)
            return new AdminGenerator();

        return adminGenerator;
    }

    /**
     * This method is used to add a new admin to our data
     * @param admin
     */
    public void add_admin(User admin) {
        if (admin == null)
            System.out.println("null passed in add_admin");
        else
            Data.Admins.put(admin.getId(), admin);
    }

    /**
     * This method is used to remove an admin from our data
     * @param admin
     */
    public void remove_admin(User admin) {
        Data.Admins.remove(admin.getId());
    }
}
