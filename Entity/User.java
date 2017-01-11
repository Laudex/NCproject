package Entity;


public class User {
    static private int k = 1;
    private int userId;
    private boolean isAdmin;

    public User(){
        this.userId = k;
        k++;
    }

    public boolean userIsAdmin(){
        return this.isAdmin == true;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


}
