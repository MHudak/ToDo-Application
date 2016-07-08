package nice.models;

/**
 * Created by dan on 7/6/16.
 */
import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    public enum Status {
        NOT_STARTED("Not Started"),
        IN_PROGRESS("In Progress"),
        COMPLETE("Complete");
        Status(String status) {
            this.status = status;
        }
        private final String status;
        public String status() {
            return status;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String taskName;
    private String taskDesc;
    private Status taskStatus = Status.NOT_STARTED;

    @ManyToOne
    @JoinColumn(name = "assignedUserId")
    private User assignedUser;

    protected Task() {}

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public Task(String taskName, Status taskStatus) {
        this.taskName = taskName;
        this.taskStatus = taskStatus;
    }

    public Task(String taskName, User assignedUser) {
        this.taskName = taskName;
        this.assignedUser = assignedUser;
    }

    public Task(String taskName, User assignedUser, Status taskStatus) {
        this.taskName = taskName;
        this.assignedUser = assignedUser;
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return String.format(
                "Task[id=%d, taskName='%s', taskStatus='%s']",
                id, taskName, taskStatus);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Status getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Status taskStatus) {
        this.taskStatus = taskStatus;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        return !(taskName != null ? !taskName.equals(task.taskName) : task.taskName != null);

    }
}
