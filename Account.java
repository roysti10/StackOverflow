import javax.swing.*;

public class Account {
    protected String name;
    protected String password;
    protected String email;
    protected enum accStatus {
        ACTIVE,
        INACTIVE
    };
    protected accStatus status;
    protected int phone;
    protected int id;
    public int reputation;

    
    public Account(String name, String password, String email, int phone) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.status = accStatus.ACTIVE;
        this.phone = phone;
        this.reputation = 0;
    }

    public boolean resetPassword(String password) {
        this.password = password;
        return true;
    }
}

