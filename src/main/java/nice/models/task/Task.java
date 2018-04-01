package nice.models.task;

import com.sun.istack.internal.Nullable;
import lombok.Getter;
import lombok.Setter;
import nice.models.user.User;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String name;
    private String description;

    @OneToOne
    @Nullable
    private User assignedUser;

    @Enumerated(EnumType.STRING)
    private Status status;

}
