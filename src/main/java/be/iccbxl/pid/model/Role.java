package be.iccbxl.pid.model;

import lombok.Data;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String role;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();


    public List<User> getUsers() {
        return users;
    }

    public Role addUser(User user) {
        if(!this.users.contains(user)) {
            this.users.add(user);
            user.addRole(this);
        }

        return this;
    }

    public Role removeUser(User user) {
        if(this.users.contains(user)) {
            this.users.remove(user);
            user.getRoles().remove(this);
        }

        return this;
    }

    @Override
    public String toString() {
        return "Role [id=" + id + ", role=" + role + "]";
    }

}



