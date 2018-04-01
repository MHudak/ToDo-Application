package nice.models.task;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
public class TaskRequest {

    @NotBlank(message = "Task name is a required field.")
    public String name;

    public Status status;
    public String description;
    public Long assignedUser;

}
