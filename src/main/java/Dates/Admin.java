package Dates;

public class Admin {
    private int id;
    private String user;
    private String password;

    public Admin(int id, String user, String password) {
        this.id = id;
        this.user = user;
        this.password = password;
    }
    
    public int getId() {
        return id;
    }


    public String getUser() {
        return user;
    }


    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Id: \t\t" + getId()
                + "\nUser: \t\t" + getUser()
                + "\nPassword: \t" + getPassword() + "\n";
    }
}
