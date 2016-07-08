package nice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String userName;

    //Was getting recursive calls and stack overflow,
    //Ignore assignedTasks when converting to JSON.
    //TODO: Revisit and include assignedTasks in JSON
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assignedUser", cascade = CascadeType.ALL)
    private Set<Task> assignedTasks;

    protected User() {}

    public User(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, userName='%s']",
                id, userName);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(Set<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        return !(userName != null ? !userName.equals(user.userName) : user.userName != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }
}