package controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import dao.UserService;
import dao.RequestService;
import domain.User;
import domain.Request;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService = new UserService();
    RequestService requestService = new RequestService();

    @RequestMapping("/all")
    public List<User> getAllUsers() {
      List<User> users=userService.getAllUsers();
      return users;
    }

    @RequestMapping("/{id}/requests")
    public List<Request> getRequestsById(@PathVariable int id){
      List<Request> requests = requestService.getRequestsById(id);
      return requests;
    }

    @RequestMapping("/{id}/payments")
    public List<Request> getPaymentsById(@PathVariable int id){
      List<Request> requests = requestService.getPaymentsById(id);
      return requests;
    }
}