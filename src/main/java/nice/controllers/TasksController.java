package nice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import nice.models.Task;
import nice.services.TaskService;

@Controller
@EnableAutoConfiguration
public class TasksController {

    @Autowired
    private TaskService TaskService;

    @RequestMapping(value="/tasks", method=RequestMethod.GET)
    @ResponseBody
    Iterable<Task> getAllTasks() {
        return TaskService.findAll();
    }

    @RequestMapping(value = "/tasks/{id}", method=RequestMethod.GET)
    @ResponseBody
    Task getTaskByIdOrTaskName(@PathVariable("id") String idOrTaskName) {
        return TaskService.findByIdOrTaskName(idOrTaskName);
    }

    @RequestMapping(value="/tasks", method=RequestMethod.POST)
    @ResponseBody
    Task createTask(@RequestBody CreateTaskRequest request) {
        return TaskService.createTask(request.getTaskName());
    }

    @RequestMapping(value="/tasks/{id}", method=RequestMethod.PUT)
    @ResponseBody
    Task updateTask(@PathVariable("id") Long id, @RequestBody CreateTaskRequest request) {
        return TaskService.updateTask(id, request.getTaskName());
    }
}