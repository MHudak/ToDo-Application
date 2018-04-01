package nice.controllers;

import nice.models.user.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import nice.models.user.User;
import nice.services.UserService;

@Controller
@EnableAutoConfiguration
public class UsersController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity exception() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an error processing the request.");
    }

    @RequestMapping(value="/users", method=RequestMethod.GET)
    @ResponseBody
    Iterable<User> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = "/users/{id}", method=RequestMethod.GET)
    @ResponseBody
    User getUserByIdOrUserName(@PathVariable("id") String idOrUserName) {
        return userService.findByIdOrUserName(idOrUserName);
    }

    @RequestMapping(value="/users", method=RequestMethod.POST)
    @ResponseBody
    User createUser(@RequestBody UserRequest request) {
        return userService.createUser(request.getUserName());
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.PUT)
    @ResponseBody
    User updateUser(@PathVariable("id") Long id, @RequestBody UserRequest request) {
        return userService.updateUser(id, request.getUserName());
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "User " + id + " was deleted successfully";
    }
}