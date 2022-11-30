package codegym.config.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    public String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",message = "Tối thiểu tám ký tự, ít nhất một chữ cái và một số")
    public String password;
    @ManyToOne
    private Roles roles;

    public Account() {
    }

    public Account(long id, String username, String password, Roles roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return roles;
    }

    public void setRole(Roles role) {
        this.roles = role;
    }
}