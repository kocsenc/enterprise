package controller;

//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import dao.UserService;
import domain.User;

@RestController
public class UserController {

    UserService userService = new UserService();

    @RequestMapping("/test")
    public List<User> getAllUsers() {
      List<User> users=userService.getAllUsers();
      return users;
    }
}