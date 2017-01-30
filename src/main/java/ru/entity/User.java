package ru.entity;


public class User {
    private int userId;
    private String name;
    private boolean isAdmin;

    public User(){
    }

    public User(int userId, String name, boolean isAdmin) {
        this.userId = userId;
        this.name = name;
        this.isAdmin = isAdmin;
    }

    public boolean userIsAdmin(boolean admin){
        return this.isAdmin == admin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }
}
