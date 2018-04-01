package nice.controllers;

import nice.models.task.Task;
import nice.models.task.TaskRequest;
import nice.models.user.User;
import nice.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class TaskController {

    @Autowired
    TaskService taskService;

    @RequestMapping(value="/tasks", method=RequestMethod.POST)
    @ResponseBody
    Task createTask(@Valid @RequestBody TaskRequest request) {

        Task task = new Task();
        task.setDescription(request.getDescription());
        task.setName(request.name);
        task.setStatus(request.status);

        if(request.getAssignedUser() != null){
            User user = new User();
            user.setId(request.getAssignedUser());
            task.setAssignedUser(user);
        }

        return taskService.createTask(task);
    }

    @RequestMapping(value="/tasks", method=RequestMethod.GET)
    @ResponseBody
    Iterable<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

}

