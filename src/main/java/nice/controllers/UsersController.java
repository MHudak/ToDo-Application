package nice.controllers;

import nice.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import nice.models.User;
import nice.services.UserService;

@Controller
@EnableAutoConfiguration
@RequestMapping(value="/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    Iterable<User> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = "{id}", method=RequestMethod.GET)
    @ResponseBody
    User getUserByIdOrUserName(@PathVariable("id") String idOrUserName) {
        return userService.findByIdOrUserName(idOrUserName);
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    User createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request.getUserName());
    }

    @RequestMapping(value="{id}", method=RequestMethod.PUT)
    @ResponseBody
    User updateUser(@PathVariable("id") Long id, @RequestBody CreateUserRequest request) {
        return userService.updateUser(id, request.getUserName());
    }

    @RequestMapping(value="{id}", method=RequestMethod.DELETE)
    void deleteUser(@PathVariable("id") String idOrUserName) {
        userService.deleteUserByIdOrUserName(idOrUserName);
    }

    @RequestMapping(value="{id}/tasks", method=RequestMethod.GET)
    @ResponseBody
    Set<Task> getTasksForUser(@PathVariable("id") String idOrUserName) {
        return userService.getTasksForUserId(userService.findByIdOrUserName(idOrUserName).getId());
    }
}