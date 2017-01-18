package Entity;


public class User {
    private int userId;
    private boolean isAdmin;

    public User(){
    }

    public User(int userId, boolean isAdmin) {
        this.userId = userId;
        this.isAdmin = isAdmin;
    }

    public boolean userIsAdmin(boolean admin){
        return this.isAdmin == admin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public boolean getisAdmin() {
        return isAdmin;
    }
}
